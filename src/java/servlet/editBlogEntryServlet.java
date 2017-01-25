/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import DAO.BlogEntryDAO;
import DAO.DBConnection;
import domain.BlogEntry;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author yeerick
 */
@WebServlet(urlPatterns = {"/editBlogEntryServlet"})
public class editBlogEntryServlet extends HttpServlet {

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
        int beid = Integer.parseInt(request.getParameter("id"));
        BlogEntryDAO BEDAO = new BlogEntryDAO();
        try (Connection con = DBConnection.getCon()) {
            BlogEntry be = BEDAO.findbyId(con, beid);
            request.setAttribute("entry", be);
            RequestDispatcher dispatch = request.getRequestDispatcher("/editBlogEntry.jsp");
            dispatch.forward(request, response);
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(editBlogEntryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String title = request.getParameter("title");
        String detail = request.getParameter("detail");
        int id = Integer.parseInt(request.getParameter("id"));
        BlogEntryDAO BEDAO = new BlogEntryDAO();

        try (Connection con = DBConnection.getCon()) {
            BEDAO.editBlogEntry(con, id, title, detail);
            response.sendRedirect("/SeniorProject/BlogEntryList/view?id="+id);
            
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(editBlogEntryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}