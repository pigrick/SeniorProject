/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import DAO.BlogEntryDAO;
import DAO.CommentDAO;
import DAO.DBConnection;
import domain.BlogEntry;
import domain.Comment;
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
public class viewBlogEntryServlet extends HttpServlet {

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
        User us = (User) request.getSession().getAttribute("user");

        BlogEntryDAO BEDAO = new BlogEntryDAO();
        CommentDAO CDAO = new CommentDAO();
        try (Connection con = DBConnection.getCon()) {
            BlogEntry be;
             ArrayList<Comment> comments;
            if (us != null) {               
                comments = CDAO.findbyBlogEntryId(con, beid, us.getId());
                be = BEDAO.findbyId(con, beid, us.getId());
                if(be.getUserid() == us.getId()){
                    CDAO.read(con, comments);
                }
            } else {
                comments = CDAO.findbyBlogEntryId(con, beid);
                be = BEDAO.findbyId(con, beid);
            }
            request.setAttribute("entry", be);
            request.setAttribute("comments", comments);
            RequestDispatcher dispatch = request.getRequestDispatcher("/blogEntry.jsp");
            dispatch.forward(request, response);
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(editBlogEntryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String comment = request.getParameter("comment");
        int beid = Integer.parseInt(request.getParameter("id"));
        User us = (User) request.getSession().getAttribute("user");
        int usid = us.getId();

        Comment cm = new Comment(comment, beid, usid);
        CommentDAO CDAO = new CommentDAO();

        try (Connection con = DBConnection.getCon()) {
            CDAO.addComment(con, cm);
            response.sendRedirect("view?id=" + beid);
            //RequestDispatcher dispatch = request.getRequestDispatcher("/blogEntry.jsp");
            //dispatch.forward(request, response);
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(editBlogEntryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
