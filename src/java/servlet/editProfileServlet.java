/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import DAO.DBConnection;
import DAO.PasswordAuthentication;
import DAO.UserDAO;
import domain.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author yeerick
 */
public class editProfileServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getSession().getAttribute("user") == null) {
            RequestDispatcher dispatch = request.getRequestDispatcher("login.jsp");
            dispatch.forward(request, response);
        } else {
            User current = (User) request.getSession().getAttribute("user");
            request.setAttribute("username", current.getUsername());
            request.setAttribute("email", current.getEmail());
            RequestDispatcher dispatch = request.getRequestDispatcher("editprofile.jsp");
            dispatch.forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        char[] oldpassword = request.getParameter("oldpassword").toCharArray();
        char[] newpassword = request.getParameter("newpassword").toCharArray();
        String email = request.getParameter("email");
        User current = (User) request.getSession().getAttribute("user");
        
        UserDAO UDAO = new UserDAO();
        try (Connection con = DBConnection.getCon()) {
            String token = UDAO.getToken(con, current.getUsername());
            PasswordAuthentication pa = new PasswordAuthentication();
            

            if (token != null && pa.authenticate(oldpassword, token)) {
                User us = UDAO.findUserForEdit(con, username, email, current.getId());
                
                
                if(us == null){
                    token = pa.hash(newpassword);
                    UDAO.updateUser(con, username, email, token, current.getId());
                    request.getSession().setAttribute("user", UDAO.findByUsername(con, username));
                    RequestDispatcher dispatch = request.getRequestDispatcher("MainPage.jsp");
                    dispatch.forward(request, response);      
                    
                    //clear password
                    for (int i = 0; i < newpassword.length; i++) {
                        newpassword[i] = 0;
                    }
                } else if (us.getUsername().equals(username)) {
                    request.setAttribute("email", email);
                    request.setAttribute("username", current.getUsername());
                    request.setAttribute("validability", "Username already in used");
                    RequestDispatcher dispatch = request.getRequestDispatcher("editprofile.jsp");
                    dispatch.forward(request, response);
                } else if (us.getEmail().equals(email)) {
                    request.setAttribute("username", username);
                    request.setAttribute("email", current.getEmail());
                    request.setAttribute("validability", "Email already in used");
                    RequestDispatcher dispatch = request.getRequestDispatcher("editprofile.jsp");
                    dispatch.forward(request, response);
                }

            } else {
                request.setAttribute("email", email);
                request.setAttribute("username", username);
                request.setAttribute("validability", "Old password is incorrect!");
                RequestDispatcher dispatch = request.getRequestDispatcher("editprofile.jsp");
                dispatch.forward(request, response);
            }

            //clean password
            for (int i = 0; i < oldpassword.length; i++) {
                oldpassword[i] = 0;
            }
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(BlogEntryListServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
