package servlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import DAO.DBConnection;
import DAO.UserDAO;
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
import servlet.BlogEntryListServlet;

/**
 *
 * @author yeerick
 */
public class searchUserServlet extends HttpServlet {

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
        String name = request.getParameter("name");

        UserDAO UDAO = new UserDAO();
        try (Connection con = DBConnection.getCon()) {
            User searchuser = UDAO.findByUsername(con, name);
            if (searchuser != null) {
                response.sendRedirect("/SeniorProject/user?name=" + name);
            } else {
                ArrayList<User> users = UDAO.searchUser(con, name);
                if(users.size() == 0){
                    request.setAttribute("noresult", "No result found. Try Something else.");
                }
                request.setAttribute("users", users);
                request.setAttribute("searchname", name);
                RequestDispatcher dispatch = request.getRequestDispatcher("searchuser.jsp");
                dispatch.forward(request, response);
            }

        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(BlogEntryListServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
