/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import domain.BlogEntry;
import domain.FriendRequest;
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
            us.setDescription(rs.getString("description"));
            us.setPicurl(rs.getString("picurl"));
            return us;
        }
        return null;
    }

    public String getUsername(Connection cn, int userid) throws SQLException {
        String query = "SELECT user.username FROM user WHERE id = ? ";
        ResultSet rs = null;

        PreparedStatement st = cn.prepareStatement(query);
        st.setInt(1, userid);
        rs = st.executeQuery();
        while(rs.next()){
            return (rs.getString("username"));
        }
        return null;
    }
    
    public int getUserid(Connection cn, String username) throws SQLException {
        String query = "SELECT user.id FROM user WHERE username= ? ";
        ResultSet rs = null;

        PreparedStatement st = cn.prepareStatement(query);
        st.setString(1, username);
        rs = st.executeQuery();
        while(rs.next()){
            return (rs.getInt("id"));
        }
        return 0;
    }

    public User findByUsernameWithFriendStatus(Connection cn, String username, int viewerid) throws SQLException {
        String query = "SELECT * FROM user WHERE username = ? ";
        ResultSet rs = null;

        PreparedStatement st = cn.prepareStatement(query);
        st.setString(1, username);
        rs = st.executeQuery();

        while (rs.next()) {
            User us = new User();
            us.setId(rs.getInt("id"));
            us.setUsername(rs.getString("username"));
            us.setEmail(rs.getString("email"));
            us.setDescription(rs.getString("description"));
            us.setPicurl(rs.getString("picurl"));
            us.setViewerfriend(findFriendStatus(cn, us.getId(), viewerid));
            return us;
        }
        return null;
    }

    public User findByIdWithFriendStatus(Connection cn, int userid, int viewerid) throws SQLException {
        String query = "SELECT * FROM user WHERE id = ? ";
        ResultSet rs = null;

        PreparedStatement st = cn.prepareStatement(query);
        st.setInt(1, userid);
        rs = st.executeQuery();

        while (rs.next()) {
            User us = new User();
            us.setId(rs.getInt("id"));
            us.setUsername(rs.getString("username"));
            us.setEmail(rs.getString("email"));
            us.setDescription(rs.getString("description"));
            us.setPicurl(rs.getString("picurl"));
            us.setViewerfriend(findFriendStatus(cn, us.getId(), viewerid));
            return us;
        }
        return null;
    }

    public FriendRequest findFriendStatus(Connection cn, int askid, int answerid) throws SQLException {
        if (isFriend(cn, askid, answerid)) {
            return FriendRequest.FRIEND;
        } else {
            String query = "SELECT * FROM friendrequest WHERE askid = ? AND answerid = ? ";
            ResultSet rs = null;
            PreparedStatement st = cn.prepareStatement(query);
            st.setInt(1, askid);
            st.setInt(2, answerid);
            rs = st.executeQuery();
            while (rs.next()) {
                return FriendRequest.REQUESTING;
            }

            st.setInt(1, answerid);
            st.setInt(2, askid);
            rs = st.executeQuery();
            while (rs.next()) {
                return FriendRequest.ACCEPTING;
            }
            return FriendRequest.NOTFRIEND;
        }
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
            us.setDescription(rs.getString("description"));
            us.setPicurl(rs.getString("picurl"));
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

    public void addUser(Connection cn, String username, String email, String picurl, String description, String token) throws SQLException {
        String query = "INSERT INTO user (username, email, picurl, description, password) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement st = cn.prepareStatement(query);
        st.setString(1, username);
        st.setString(2, email);
        st.setString(3, picurl);
        st.setString(4, description);
        st.setString(5, token);
        st.execute();
    }

    public void updateUser(Connection cn, String username, String email, String picurl, String description, String token, int id) throws SQLException {
        String query = "UPDATE user SET username = ?, email = ?, picurl = ?, description = ?, password = ? WHERE id =?";
        PreparedStatement st = cn.prepareStatement(query);
        st.setString(1, username);
        st.setString(2, email);
        st.setString(3, picurl);
        st.setString(4, description);
        st.setString(5, token);
        st.setInt(5, id);
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
            us.setDescription(rs.getString("description"));
            us.setPicurl(rs.getString("picurl"));
            return us;
        }
        return null;

    }

    public ArrayList<User> searchUser(Connection cn, String username) throws SQLException {
        String query = "SELECT * FROM user WHERE username LIKE ? ORDER BY username ASC";
        String searchname = "%" + username + "%";
        ResultSet rs = null;
        ArrayList<User> users = new ArrayList<>();
        PreparedStatement st = cn.prepareStatement(query);
        st.setString(1, searchname);
        rs = st.executeQuery();
        User us = null;
        while (rs.next()) {
            us = new User();
            us.setId(rs.getInt("id"));
            us.setUsername(rs.getString("username"));
            us.setEmail(rs.getString("email"));
            us.setDescription(rs.getString("description"));
            us.setPicurl(rs.getString("picurl"));
            users.add(us);
        }
        return users;
    }

    public Boolean isFriend(Connection cn, int askid, int answerid) throws SQLException {
        String query = "SELECT * FROM friends WHERE (userid1 = ? AND userid2 = ?)";
        ResultSet rs = null;
        PreparedStatement st = cn.prepareStatement(query);
        if (askid < answerid) {
            st.setInt(1, askid);
            st.setInt(2, answerid);
        } else {
            st.setInt(1, answerid);
            st.setInt(2, askid);
        }
        rs = st.executeQuery();
        while (rs.next()) {
            return true;
        }
        return false;

    }

    public void deleteRequest(Connection cn, int askid, int answerid) throws SQLException {
        String query = "DELETE FROM friendrequest WHERE (askid = ? AND answerid = ?)";
        PreparedStatement ps = cn.prepareStatement(query);
        ps.setInt(1, askid);
        ps.setInt(2, answerid);
        ps.execute();
    }

    public void addRequest(Connection cn, int askid, int answerid) throws SQLException {
        String query = "INSERT INTO friendrequest (askid, answerid) VALUES (?,?)";
        PreparedStatement ps = cn.prepareStatement(query);
        ps.setInt(1, askid);
        ps.setInt(2, answerid);
        ps.execute();
    }

    public void addFriend(Connection cn, int userid1, int userid2) throws SQLException {
        String query = "INSERT INTO friends (userid1, userid2) VALUES (?,?)";
        PreparedStatement ps = cn.prepareStatement(query);
        if (userid1 < userid2) {
            ps.setInt(1, userid1);
            ps.setInt(2, userid2);
        } else {
            ps.setInt(1, userid2);
            ps.setInt(2, userid1);
        }
        ps.execute();
    }

    public void deleteFriend(Connection cn, int userid1, int userid2) throws SQLException {
        String query = "DELETE FROM friends WHERE (userid1 = ? AND userid2 = ?)";
        PreparedStatement ps = cn.prepareStatement(query);
        if (userid1 < userid2) {
            ps.setInt(1, userid1);
            ps.setInt(2, userid2);
        } else {
            ps.setInt(1, userid2);
            ps.setInt(2, userid1);
        }
        ps.execute();
    }

    public ArrayList<User> findfriends(Connection cn, int userid) throws SQLException {
        String query = "SELECT * FROM friends WHERE userid1 = ? OR userid2 = ?";
        ResultSet rs = null;
        ArrayList<User> users = new ArrayList<>();
        PreparedStatement ps = cn.prepareStatement(query);
        ps.setInt(1, userid);
        ps.setInt(2, userid);
        rs = ps.executeQuery();
        User us;
        while (rs.next()) {
            if (userid == rs.getInt("userid2")) {
                us = findByIdWithFriendStatus(cn, rs.getInt("userid1"), userid);
            } else {
                us = findByIdWithFriendStatus(cn, rs.getInt("userid2"), userid);
            }

            users.add(us);
        }
        return users;
    }

    public ArrayList<User> findAccepting(Connection cn, int userid) throws SQLException {
        String query = "SELECT * FROM friendrequest WHERE askid = ?";
        ResultSet rs = null;
        PreparedStatement st = cn.prepareStatement(query);
        st.setInt(1, userid);
        rs = st.executeQuery();
        ArrayList<User> users = new ArrayList<>();
        while (rs.next()) {
            User us = findByIdWithFriendStatus(cn, rs.getInt("answerid"), userid);
            users.add(us);
        }

        return users;
    }

    public ArrayList<User> findRequesting(Connection cn, int userid) throws SQLException {
        String query = "SELECT * FROM friendrequest WHERE answerid = ? ";
        ResultSet rs = null;
        ArrayList<User> users = new ArrayList<>();
        PreparedStatement ps = cn.prepareStatement(query);
        ps.setInt(1, userid);
        rs = ps.executeQuery();
        while (rs.next()) {
            User us = findByIdWithFriendStatus(cn, rs.getInt("askid"), userid);
            users.add(us);
        }
        return users;
    }
}
