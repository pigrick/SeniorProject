/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import domain.BlogEntry;
import domain.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author yeerick
 */
public class UserDAO {

    public User findByUsername(Connection cn, String username) throws SQLException {
        String query = "SELECT * FROM user WHERE username = ?";
        ResultSet rs = null;

        PreparedStatement st = cn.prepareStatement(query);
        st.setString(1, username);
        rs = st.executeQuery();

        while (rs.next()) {
            User us = new User();
            us.setId(rs.getInt("id"));
            us.setUsername(rs.getString("username"));
            us.setEmail(rs.getString("email"));
            return us;
        }
        return null;
    }

    public User findByEmail(Connection cn, String email) throws SQLException {
        String query = "SELECT * FROM user WHERE email = ?";
        ResultSet rs = null;

        PreparedStatement st = cn.prepareStatement(query);
        st.setString(1, email);
        rs = st.executeQuery();

        while (rs.next()) {
            User us = new User();
            us.setId(rs.getInt("id"));
            us.setUsername(rs.getString("username"));
            us.setEmail(rs.getString("email"));
            return us;
        }
        return null;
    }

    public String getToken(Connection con, String username) throws SQLException {
        String sql = "SELECT password FROM user WHERE username = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("password");
                }
            }
        }
        return null;
    }

    public void addUser(Connection cn, String username, String email, String token) throws SQLException {
        String query = "INSERT INTO user (username, email, password) VALUES (?, ?, ?)";

        PreparedStatement st = cn.prepareStatement(query);
        st.setString(1, username);
        st.setString(2, email);
        st.setString(3, token);
        st.execute();
    }

    public void updateUser(Connection cn, String username, String email, String token, int id) throws SQLException {
        String query = "UPDATE user SET username = ?, email = ?, password = ? WHERE id =?";
        PreparedStatement st = cn.prepareStatement(query);
        st.setString(1, username);
        st.setString(2, email);
        st.setString(3, token);
        st.setInt(4, id);
        st.execute();
    }

    public User findUserForEdit(Connection cn, String username, String email, int id) throws SQLException {
        String query = "SELECT * FROM user WHERE (username = ? OR email = ?) AND NOT id = ?";
        ResultSet rs = null;
        PreparedStatement st = cn.prepareStatement(query);
        st.setString(1, username);
        st.setString(2, email);
        st.setInt(3, id);
        rs = st.executeQuery();
        while (rs.next()) {
            User us = new User();
            us.setId(rs.getInt("id"));
            us.setUsername(rs.getString("username"));
            us.setEmail(rs.getString("email"));
            return us;
        }
        return null;

    }
}
