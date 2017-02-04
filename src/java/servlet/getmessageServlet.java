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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author yeerick
 */
public class getmessageServlet extends HttpServlet {

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
        User us = (User) request.getSession().getAttribute("user");
        String targetname = request.getParameter("targetname");
        int lastid = Integer.parseInt(request.getParameter("lastid"));
        MessageDAO MDAO = new MessageDAO();
        UserDAO UDAO = new UserDAO();

        if (us != null) {
            try (Connection con = DBConnection.getCon()) {
                int targetid = UDAO.getUserid(con, targetname);
                int datalastid = MDAO.lastIdOfMessage(con, us.getId(), targetid);
                if (lastid == datalastid) {
                    PrintWriter out = response.getWriter();
                    out.println("");

                } else {

                    ArrayList<Message> messages = MDAO.findMessageWithLimit(con, us.getId(), targetid, lastid);
                    for (Message message : messages) {
                        if (us.getId() == message.getReceiveid() && message.getReaded() == false) {
                            MDAO.read(con, message.getId());
                        }
                    }

                    String temp = "";
                    for (int i = 0; i < messages.size(); i++) {
                        temp = temp + "@@@" + messages.get(i).getId() + "@@@" + messages.get(i).getSendname() + "@@@" + messages.get(i).getDetails();
                    }
                    PrintWriter out = response.getWriter();
                    out.println(temp);

                }
            } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
                Logger.getLogger(BlogEntryListServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

}
