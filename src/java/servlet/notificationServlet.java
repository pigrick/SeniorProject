/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import DAO.BlogEntryDAO;
import DAO.CommentDAO;
import DAO.DBConnection;
import DAO.UserDAO;
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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author yeerick
 */
public class notificationServlet extends HttpServlet {

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
        CommentDAO CDAO = new CommentDAO();
        BlogEntryDAO BEDAO = new BlogEntryDAO();
        UserDAO UDAO = new UserDAO();
        User us = (User) request.getSession().getAttribute("user");

        if (us != null) {

            try (Connection con = DBConnection.getCon()) {
                ArrayList<BlogEntry> bes = BEDAO.findByUserid(con, us.getId());
                String temp = "";
                 ArrayList<Comment> comments = new ArrayList<>();
                for(BlogEntry be: bes){
                    comments.addAll(CDAO.getUnreadComment(con, be.getId()));
                }
                
                for (Comment comment : comments) {
                    temp = temp + "@@@" + comment.getBeid() + "@@@" + UDAO.getUsername(con, comment.getUserid());
                }
                PrintWriter out = response.getWriter();
                out.println(temp);

            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                Logger.getLogger(deleteBlogEntryServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
