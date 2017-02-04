/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import DAO.BlogEntryDAO;
import DAO.DBConnection;
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
public class browsefriendServlet extends HttpServlet {

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
        User us = (User) request.getSession().getAttribute("user");
        try (Connection con = DBConnection.getCon()) {
            int page = 0;

            if (request.getParameter("page") == null) {

                ArrayList<BlogEntry> blogentries = BEDAO.findFriendsByPage(con, us.getId(), 1, 5);
                request.setAttribute("entries", blogentries);

            } else {
                page = Integer.parseInt(request.getParameter("page"));

                ArrayList<BlogEntry> blogentries = BEDAO.findFriendsByPage(con, us.getId(), page * 5, 5);
                request.setAttribute("entries", blogentries);

            }

            int entryNo = BEDAO.getFriendsBlogEntryNumber(con, us.getId());
            int pageNo = entryNo / 5;
            if (entryNo % 5 != 0) {
                pageNo += 1;
            }
            ArrayList<Integer> pages = new ArrayList<>();
            for (int i = 0; i < pageNo; i++) {
                pages.add(i);
            }
            request.setAttribute("currentpage", page);
            request.setAttribute("pages", pages);
            RequestDispatcher dispatch = request.getRequestDispatcher("browse.jsp");
            dispatch.forward(request, response);

        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(BlogEntryListServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
