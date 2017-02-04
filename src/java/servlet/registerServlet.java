package servlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import DAO.BlogEntryDAO;
import DAO.DBConnection;
import DAO.PasswordAuthentication;
import DAO.UserDAO;
import domain.BlogEntry;
import domain.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import servlet.BlogEntryListServlet;

/**
 *
 * @author yeerick
 */
public class registerServlet extends HttpServlet {

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
        RequestDispatcher dispatch = request.getRequestDispatcher("register.jsp");
        dispatch.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String picurl = request.getParameter("picurl");
        String description = request.getParameter("description");
        char[] password = request.getParameter("password").toCharArray();

        PasswordAuthentication pa = new PasswordAuthentication();
        String token = pa.hash(password);
        
        //clear password
        for (int i = 0; i < password.length; i++) {
            password[i] = 0;
        }
        


        UserDAO UDAO = new UserDAO();

        try (Connection con = DBConnection.getCon()) {
            if (UDAO.findByUsername(con, username) != null) {
                request.setAttribute("email", email);
                request.setAttribute("picurl", picurl);
                request.setAttribute("description", description);
                request.setAttribute("validability", "Username already in used.");
                RequestDispatcher dispatch = request.getRequestDispatcher("register.jsp");
                dispatch.forward(request, response);
            } else if (UDAO.findByEmail(con, email) != null) {
                request.setAttribute("username", username);
                request.setAttribute("picurl", picurl);
                request.setAttribute("description", description);
                request.setAttribute("validability", "Email already in used.");
                RequestDispatcher dispatch = request.getRequestDispatcher("register.jsp");
                dispatch.forward(request, response);
            } else {
                UDAO.addUser(con, username, email, picurl, description, token);
                response.sendRedirect("login");
            }
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(BlogEntryListServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
