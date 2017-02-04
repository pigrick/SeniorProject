/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import DAO.DBConnection;
import DAO.UserDAO;
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
public class friendlistServlet extends HttpServlet {

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
        UserDAO UDAO = new UserDAO();
        if (us != null) {
            try (Connection con = DBConnection.getCon()) {
                ArrayList<User> users1 = UDAO.findfriends(con, us.getId());
                ArrayList<User> users2 = UDAO.findRequesting(con, us.getId());
                ArrayList<User> users3 = UDAO.findAccepting(con, us.getId());
                request.setAttribute("friends", users1);
                request.setAttribute("requesters", users2);
                request.setAttribute("accepters", users3);
                RequestDispatcher dispatch = request.getRequestDispatcher("friendlist.jsp");
                dispatch.forward(request, response);

            } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
                Logger.getLogger(BlogEntryListServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            response.sendRedirect("/SeniorProject/login");
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String status = request.getParameter("status");
        String currentname = request.getParameter("currentname");
        int currentid = Integer.parseInt(request.getParameter("currentid"));
        User us = (User)request.getSession().getAttribute("user");
        UserDAO UDAO = new UserDAO();
        try (Connection con = DBConnection.getCon()) {
           if (status.equals("UNFRIEND")) {
                UDAO.deleteFriend(con, us.getId(), currentid);
            } else if (status.equals("REQUEST")) {
                UDAO.addRequest(con, currentid, us.getId());
            } else if (status.equals("RESPONSE")) {
                UDAO.addFriend(con, us.getId(), currentid);
                UDAO.deleteRequest(con, us.getId(), currentid);
            } else if (status.equals("WAITING")) {
                UDAO.deleteRequest(con, currentid, us.getId());
            }
            response.sendRedirect("/SeniorProject/friendlist");
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(BlogEntryListServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
