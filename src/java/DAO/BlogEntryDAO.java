package DAO;

import DAO.DBConnection;
import domain.BlogEntry;
import domain.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author yeerick
 */
public class BlogEntryDAO {

    public ArrayList<BlogEntry> findAll(Connection cn) throws SQLException {
        String query = "SELECT blogentry.*, user.username FROM blogentry RIGHT JOIN user ON blogentry.userid=user.id ORDER BY created DESC";
        ResultSet rs = null;
        ArrayList<BlogEntry> blogentries = new ArrayList<>();

        Statement st = cn.createStatement();
        rs = st.executeQuery(query);
        BlogEntry be = null;
        while (rs.next()) {
            be = new BlogEntry();
            be.setId(rs.getInt("id"));
            be.setTitle(rs.getString("title"));
            be.setDetail(rs.getString("detail"));
            be.setCreated(rs.getTimestamp("created"));
            be.setUserid(rs.getInt("userid"));
            be.setUsername(rs.getString("username"));
            be.setLikes(noLike(cn, be.getId()));
            blogentries.add(be);
        }
        return blogentries;
    }

    public ArrayList<BlogEntry> findAll(Connection cn, int viewerid) throws SQLException {
        String query = "SELECT blogentry.*, user.username FROM blogentry RIGHT JOIN user ON blogentry.userid=user.id ORDER BY created DESC";
        ResultSet rs = null;
        ArrayList<BlogEntry> blogentries = new ArrayList<>();

        Statement st = cn.createStatement();
        rs = st.executeQuery(query);
        BlogEntry be = null;
        while (rs.next()) {
            be = new BlogEntry();
            be.setId(rs.getInt("id"));
            be.setTitle(rs.getString("title"));
            be.setDetail(rs.getString("detail"));
            be.setCreated(rs.getTimestamp("created"));
            be.setUserid(rs.getInt("userid"));
            be.setUsername(rs.getString("username"));
            be.setLikes(noLike(cn, be.getId()));
            be.setViewerlikes(checklike(cn, be.getId(), viewerid));
            blogentries.add(be);
        }
        return blogentries;
    }

    public ArrayList<BlogEntry> findbyFriend(Connection cn, int userid) throws SQLException {
        UserDAO UDAO = new UserDAO();
        ArrayList<User> friends = UDAO.findfriends(cn, userid);
        ArrayList<BlogEntry> blogentries = new ArrayList<>();
        for (User friend : friends) {
            String query = "SELECT blogentry.*, user.username FROM blogentry RIGHT JOIN user ON blogentry.userid=user.id WHERE userid = ? ORDER BY created DESC";
            ResultSet rs = null;

            PreparedStatement st = cn.prepareStatement(query);
            st.setInt(1, friend.getId());
            rs = st.executeQuery();

            while (rs.next()) {
                BlogEntry be = new BlogEntry();
                be.setId(rs.getInt("id"));
                be.setTitle(rs.getString("title"));
                be.setDetail(rs.getString("detail"));
                be.setCreated(rs.getTimestamp("created"));
                be.setUserid(rs.getInt("userid"));
                be.setUsername(rs.getString("username"));
                blogentries.add(be);
            }
        }
        return blogentries;
    }

    public ArrayList<BlogEntry> findbyTitle(Connection cn, String title) throws SQLException {
        String query = "SELECT blogentry.*, user.username FROM blogentry RIGHT JOIN user ON blogentry.userid=user.id WHERE title = ? ORDER BY created DESC";
        ResultSet rs = null;

        PreparedStatement st = cn.prepareStatement(query);
        st.setString(1, title);
        rs = st.executeQuery();
        ArrayList<BlogEntry> blogentries = new ArrayList<>();

        while (rs.next()) {
            BlogEntry be = new BlogEntry();
            be.setId(rs.getInt("id"));
            be.setTitle(rs.getString("title"));
            be.setDetail(rs.getString("detail"));
            be.setCreated(rs.getTimestamp("created"));
            be.setUserid(rs.getInt("userid"));
            be.setUsername(rs.getString("username"));
            blogentries.add(be);
        }
        return blogentries;
    }

    public BlogEntry findbyId(Connection cn, int id) throws SQLException {
        String query = "SELECT blogentry.*, user.username FROM blogentry RIGHT JOIN user ON blogentry.userid=user.id WHERE blogentry.id = ? ORDER BY created DESC";
        ResultSet rs = null;

        PreparedStatement st = cn.prepareStatement(query);
        st.setInt(1, id);
        rs = st.executeQuery();

        while (rs.next()) {
            BlogEntry be = new BlogEntry();
            be.setId(rs.getInt("id"));
            be.setTitle(rs.getString("title"));
            be.setDetail(rs.getString("detail"));
            be.setCreated(rs.getTimestamp("created"));
            be.setUserid(rs.getInt("userid"));
            be.setUsername(rs.getString("username"));
            return be;
        }
        return null;
    }

    public BlogEntry findbyId(Connection cn, int id, int viewerid) throws SQLException {
        String query = "SELECT blogentry.*, user.username FROM blogentry RIGHT JOIN user ON blogentry.userid=user.id WHERE blogentry.id = ? ORDER BY created DESC";
        ResultSet rs = null;

        PreparedStatement st = cn.prepareStatement(query);
        st.setInt(1, id);
        rs = st.executeQuery();

        while (rs.next()) {
            BlogEntry be = new BlogEntry();
            be.setId(rs.getInt("id"));
            be.setTitle(rs.getString("title"));
            be.setDetail(rs.getString("detail"));
            be.setCreated(rs.getTimestamp("created"));
            be.setUserid(rs.getInt("userid"));
            be.setUsername(rs.getString("username"));
            be.setLikes(noLike(cn, be.getId()));
            be.setViewerlikes(checklike(cn, be.getId(), viewerid));
            return be;
        }
        return null;
    }

    public ArrayList<BlogEntry> findByUserid(Connection cn, int userid) throws SQLException {
        String query = "SELECT blogentry.*, user.username FROM blogentry RIGHT JOIN user ON blogentry.userid=user.id WHERE userid = ? ORDER BY created DESC";
        ResultSet rs = null;

        PreparedStatement st = cn.prepareStatement(query);
        st.setInt(1, userid);
        rs = st.executeQuery();
        ArrayList<BlogEntry> blogentries = new ArrayList<>();

        while (rs.next()) {
            BlogEntry be = new BlogEntry();
            be.setId(rs.getInt("id"));
            be.setTitle(rs.getString("title"));
            be.setDetail(rs.getString("detail"));
            be.setCreated(rs.getTimestamp("created"));
            be.setUserid(rs.getInt("userid"));
            be.setUsername(rs.getString("username"));
            be.setLikes(noLike(cn, be.getId()));
            blogentries.add(be);
        }
        return blogentries;
    }

    public ArrayList<BlogEntry> findByUserid(Connection cn, int userid, int viewerid) throws SQLException {
        String query = "SELECT blogentry.*, user.username FROM blogentry RIGHT JOIN user ON blogentry.userid=user.id WHERE userid = ? ORDER BY created DESC";
        ResultSet rs = null;

        PreparedStatement st = cn.prepareStatement(query);
        st.setInt(1, userid);
        rs = st.executeQuery();
        ArrayList<BlogEntry> blogentries = new ArrayList<>();

        while (rs.next()) {
            BlogEntry be = new BlogEntry();
            be.setId(rs.getInt("id"));
            be.setTitle(rs.getString("title"));
            be.setDetail(rs.getString("detail"));
            be.setCreated(rs.getTimestamp("created"));
            be.setUserid(rs.getInt("userid"));
            be.setUsername(rs.getString("username"));
            be.setLikes(noLike(cn, be.getId()));
            be.setViewerlikes(checklike(cn, be.getId(), viewerid));
            blogentries.add(be);
        }
        return blogentries;
    }

    public void addBlogEntry(Connection cn, BlogEntry blogEntry) throws SQLException {
        String query = "INSERT INTO blogentry (title, detail, userid) VALUES (?,?,?)";
        PreparedStatement ps = cn.prepareStatement(query);
        ps.setString(1, blogEntry.getTitle());
        ps.setString(2, blogEntry.getDetail());
        ps.setInt(3, blogEntry.getUserid());
        ps.execute();
    }

    public void deleteBlogEntry(Connection cn, int id) throws SQLException {
        String query = "DELETE FROM blogentry WHERE id = ?";
        PreparedStatement ps = cn.prepareStatement(query);
        ps.setInt(1, id);
        ps.execute();
    }

    public void editBlogEntry(Connection cn, int id, String title, String detail) throws SQLException {
        String query = "UPDATE blogentry SET title = ?, detail = ? WHERE id = ?";
        PreparedStatement ps = cn.prepareStatement(query);
        ps.setString(1, title);
        ps.setString(2, detail);
        ps.setInt(3, id);
        ps.execute();
    }

    public int getAllBlogEntryNumber(Connection cn) throws SQLException {
        String query = "SELECT COUNT(*) AS count FROM blogentry";
        ResultSet rs = null;
        Statement st = cn.createStatement();
        rs = st.executeQuery(query);
        while (rs.next()) {;
            return rs.getInt("count");
        }
        return 0;
    }

    public int getFriendsBlogEntryNumber(Connection cn, int userid) throws SQLException {
        ArrayList<BlogEntry> bes = findbyFriend(cn, userid);
        return bes.size();
    }

    public ArrayList<BlogEntry> findFriendsByPage(Connection cn, int userid, int pagestart, int pages) throws SQLException {
        UserDAO UDAO = new UserDAO();
        ArrayList<User> friends = UDAO.findfriends(cn, userid);
        ArrayList<BlogEntry> blogentries = new ArrayList<>();
        for (User friend : friends) {
            String query = "SELECT blogentry.*, user.username FROM blogentry RIGHT JOIN user ON blogentry.userid=user.id WHERE userid = ? ORDER BY created DESC LIMIT ?, ?";
            ResultSet rs = null;

            PreparedStatement ps = cn.prepareStatement(query);
            ps.setInt(1, friend.getId());
            ps.setInt(2, pagestart);
            ps.setInt(3, pages);
            rs = ps.executeQuery();

            while (rs.next()) {
                BlogEntry be = new BlogEntry();
                be.setId(rs.getInt("id"));
                be.setTitle(rs.getString("title"));
                be.setDetail(rs.getString("detail"));
                be.setCreated(rs.getTimestamp("created"));
                be.setUserid(rs.getInt("userid"));
                be.setUsername(rs.getString("username"));
                be.setLikes(noLike(cn, be.getId()));
                blogentries.add(be);
            }
        }
        return blogentries;
    }

    public ArrayList<BlogEntry> findAllByPage(Connection cn, int pagestart, int pages) throws SQLException {
        String query = "SELECT blogentry.*, user.username FROM blogentry LEFT JOIN user ON blogentry.userid=user.id ORDER BY created DESC LIMIT ?,?";
        ResultSet rs = null;
        ArrayList<BlogEntry> blogentries = new ArrayList<BlogEntry>();

        PreparedStatement ps = cn.prepareStatement(query);
        ps.setInt(1, pagestart);
        ps.setInt(2, pages);
        rs = ps.executeQuery();
        BlogEntry be = null;
        while (rs.next()) {
            be = new BlogEntry();
            be.setId(rs.getInt("id"));
            be.setTitle(rs.getString("title"));
            be.setDetail(rs.getString("detail"));
            be.setCreated(rs.getTimestamp("created"));
            be.setUserid(rs.getInt("userid"));
            be.setUsername(rs.getString("username"));
            be.setLikes(noLike(cn, be.getId()));
            blogentries.add(be);
        }
        return blogentries;
    }

    public int noLike(Connection cn, int beid) throws SQLException {
        String query = "SELECT COUNT(*) AS count FROM belike Where beid = ?";
        ResultSet rs = null;
        PreparedStatement ps = cn.prepareStatement(query);
        ps.setInt(1, beid);
        rs = ps.executeQuery();
        while (rs.next()) {
            return rs.getInt("count");
        }
        return 0;
    }

    public Boolean checklike(Connection cn, int beid, int viewerid) throws SQLException {
        String query = "SELECT * FROM belike WHERE beid = ? AND userid = ?";
        ResultSet rs = null;
        PreparedStatement ps = cn.prepareStatement(query);
        ps.setInt(1, beid);
        ps.setInt(2, viewerid);
        rs = ps.executeQuery();
        while (rs.next()) {
            return true;
        }
        return false;
    }

    public void addlike(Connection cn, int beid, int viewerid) throws SQLException {
        String query = "INSERT INTO belike (userid, beid) VALUES (?,?)";
        PreparedStatement ps = cn.prepareStatement(query);
        ps.setInt(1, viewerid);
        ps.setInt(2, beid);
        ps.execute();
    }

    public void deletelike(Connection cn, int beid, int viewerid) throws SQLException {
        String query = "DELETE FROM belike WHERE (userid = ? AND beid = ?)";
        PreparedStatement ps = cn.prepareStatement(query);
        ps.setInt(1, viewerid);
        ps.setInt(2, beid);
        ps.execute();
    }

}
