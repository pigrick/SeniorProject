/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import DAO.DBConnection;
import DAO.MessageDAO;
import DAO.UserDAO;
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
public class messagenotificationServlet extends HttpServlet {

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
        MessageDAO MDAO = new MessageDAO();
        UserDAO UDAO = new UserDAO();

        if (us != null) {

            try (Connection con = DBConnection.getCon()) {
                String temp = "";
                ArrayList<String> names = MDAO.getNameFromUnreadMessage(con, us.getId());
                for (String name : names) {
                    temp = temp + "@@@" + name;
                }
                PrintWriter out = response.getWriter();
                out.println(temp);
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                Logger.getLogger(deleteBlogEntryServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
