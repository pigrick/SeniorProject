/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import DAO.DBConnection;
import DAO.MessageDAO;
import DAO.UserDAO;
import domain.Message;
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
public class messageServlet extends HttpServlet {

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
        User us = (User) request.getSession().getAttribute("user");
        String user = request.getParameter("user");
        UserDAO UDAO = new UserDAO();
        MessageDAO MDAO = new MessageDAO();
        if (us != null) {
            try (Connection con = DBConnection.getCon()) {
                ArrayList<Message> messages = MDAO.findMessage(con, us.getId(), UDAO.getUserid(con, user));
                for(Message message: messages){
                    if(us.getId() == message.getReceiveid() && message.getReaded() == false){
                        MDAO.read(con, message.getId());
                    }
                }
                request.setAttribute("messages", messages);
                request.setAttribute("target", user);
                RequestDispatcher dispatch = request.getRequestDispatcher("message.jsp");
                dispatch.forward(request, response);
            } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
                Logger.getLogger(BlogEntryListServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            response.sendRedirect("login");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User us = (User) request.getSession().getAttribute("user");
        String targetname = request.getParameter("targetname");
        String details = request.getParameter("details");
        UserDAO UDAO = new UserDAO();
        MessageDAO MDAO = new MessageDAO();
        try (Connection con = DBConnection.getCon()) {
            MDAO.addMessage(con, us.getId(), UDAO.getUserid(con, targetname), details);
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(BlogEntryListServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
