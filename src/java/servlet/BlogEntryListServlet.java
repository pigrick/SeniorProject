/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import DAO.BlogEntryDAO;
import domain.BlogEntry;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DAO.DBConnection;
import domain.User;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yeerick
 */
public class BlogEntryListServlet extends HttpServlet {

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

        BlogEntryDAO BEDAO = new BlogEntryDAO();

        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("login");
        } else {

            User us = (User) request.getSession().getAttribute("user");
            int usid = us.getId();

            try (Connection con = DBConnection.getCon()) {

                ArrayList<BlogEntry> blogentries = BEDAO.findByUserid(con, usid);
                request.setAttribute("entries", blogentries);
                RequestDispatcher dispatch = request.getRequestDispatcher("blogentrylist.jsp");
                dispatch.forward(request, response);

            } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
                Logger.getLogger(BlogEntryListServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatch = request.getRequestDispatcher("blogentrylist.jsp");
        dispatch.forward(request, response);
    }
}
