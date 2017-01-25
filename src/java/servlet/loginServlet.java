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
import javax.servlet.http.HttpSession;

/**
 *
 * @author yeerick
 */
public class loginServlet extends HttpServlet {

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
            response.sendRedirect("BlogEntryList");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        char[] password = request.getParameter("password").toCharArray();

        UserDAO UDAO = new UserDAO();
        try (Connection con = DBConnection.getCon()) {
            String token = UDAO.getToken(con, username);
            PasswordAuthentication pa = new PasswordAuthentication();
            
            if (token != null && pa.authenticate(password, token)) {
                User us = UDAO.findByUsername(con, username);
                request.getSession().setAttribute("user", us);
                RequestDispatcher dispatch = request.getRequestDispatcher("MainPage.jsp");
                dispatch.forward(request, response);;

            } else {
                request.setAttribute("validability", "Username or password is incorrect!");
                RequestDispatcher dispatch = request.getRequestDispatcher("login.jsp");
                dispatch.forward(request, response);
            }

            //clean password
            for (int i = 0; i < password.length; i++) {
                password[i] = 0;
            }
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(BlogEntryListServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
