/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import DAO.BlogEntryDAO;
import DAO.DBConnection;
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

/**
 *
 * @author yeerick
 */
public class userServlet extends HttpServlet {

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
        String username = request.getParameter("name");
        User us = (User) request.getSession().getAttribute("user");
        UserDAO UDAO = new UserDAO();
        BlogEntryDAO BEDAO = new BlogEntryDAO();
        try (Connection con = DBConnection.getCon()) {
            if (us != null) {
                User target = UDAO.findByUsernameWithFriendStatus(con, username, us.getId());
                ArrayList<BlogEntry> entries = BEDAO.findByUserid(con, target.getId());
                request.setAttribute("target", target);
                request.setAttribute("entries", entries);
                RequestDispatcher dispatch = request.getRequestDispatcher("userpage.jsp");
                dispatch.forward(request, response);
            } else {
                User target = UDAO.findByUsername(con, username);
                ArrayList<BlogEntry> entries = BEDAO.findByUserid(con, target.getId());
                request.setAttribute("target", target);
                request.setAttribute("entries", entries);
                RequestDispatcher dispatch = request.getRequestDispatcher("userpage.jsp");
                dispatch.forward(request, response);
            }
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(BlogEntryListServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
