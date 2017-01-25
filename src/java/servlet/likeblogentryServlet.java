/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import DAO.BlogEntryDAO;
import DAO.CommentDAO;
import DAO.DBConnection;
import domain.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author yeerick
 */
public class likeblogentryServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int beid = Integer.parseInt(request.getParameter("beid"));
        String likey = request.getParameter("likey");
        User us = (User) request.getSession().getAttribute("user");
        BlogEntryDAO BEDAO = new BlogEntryDAO();
        try (Connection con = DBConnection.getCon()) {
            if (likey.equals("like")) {
                BEDAO.addlike(con, beid, us.getId());
            } else {
               BEDAO.deletelike(con, beid, us.getId());
            }
            response.sendRedirect("view?id="+beid);
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(BlogEntryListServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
