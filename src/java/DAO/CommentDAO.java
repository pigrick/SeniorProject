/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import domain.BlogEntry;
import domain.Comment;
import domain.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author yeerick
 */
public class CommentDAO {

    public ArrayList<Comment> findbyBlogEntryId(Connection cn, int beid, int viewerid) throws SQLException {
        String query = "SELECT comment.*, user.username FROM comment RIGHT JOIN user ON comment.userid=user.id WHERE beid = ? ORDER BY created DESC";
        ResultSet rs = null;
        PreparedStatement st = cn.prepareStatement(query);
        st.setInt(1, beid);
        rs = st.executeQuery();
        ArrayList<Comment> comments = new ArrayList<Comment>();

        while (rs.next()) {
            Comment cm = new Comment();
            cm.setId(rs.getInt("id"));
            cm.setComment(rs.getString("comment"));
            cm.setUserid(rs.getInt("userid"));
            cm.setCreated(rs.getDate("created"));
            cm.setUsername(rs.getString("username"));
            cm.setLikes(noLike(cn,cm.getId()));

            cm.setViewerlikes(checklike(cn, cm.getId(), viewerid));

            comments.add(cm);
        }
        return comments;
    }

    public ArrayList<Comment> findbyBlogEntryId(Connection cn, int beid) throws SQLException {
        String query = "SELECT comment.*, user.username FROM comment RIGHT JOIN user ON comment.userid=user.id WHERE beid = ? ORDER BY created DESC";
        ResultSet rs = null;
        PreparedStatement st = cn.prepareStatement(query);
        st.setInt(1, beid);
        rs = st.executeQuery();
        ArrayList<Comment> comments = new ArrayList<Comment>();

        while (rs.next()) {
            Comment cm = new Comment();
            cm.setId(rs.getInt("id"));
            cm.setComment(rs.getString("comment"));
            cm.setUserid(rs.getInt("userid"));
            cm.setCreated(rs.getDate("created"));
            cm.setUsername(rs.getString("username"));
            cm.setLikes(noLike(cn, cm.getId()));

            comments.add(cm);
        }
        return comments;
    }

    public void addComment(Connection cn, Comment comment) throws SQLException {
        String query = "INSERT INTO comment (comment, userid, beid) VALUES (?,?,?)";
        PreparedStatement ps = cn.prepareStatement(query);
        ps.setString(1, comment.getComment());
        ps.setInt(2, comment.getUserid());
        ps.setInt(3, comment.getBeid());
        ps.execute();
    }

    public void deleteComment(Connection cn, int id) throws SQLException {
        String query = "DELETE FROM comment WHERE id = ?";
        PreparedStatement ps = cn.prepareStatement(query);
        ps.setInt(1, id);
        ps.execute();
    }

    public int noLike(Connection cn, int commentid) throws SQLException {
        String query = "SELECT COUNT(*) AS count FROM commentlike Where commentid = ?";
        ResultSet rs = null;
        PreparedStatement ps = cn.prepareStatement(query);
        ps.setInt(1, commentid);
        rs = ps.executeQuery();
        while(rs.next()){
            return rs.getInt("count");
        }
        return 0;           
    }

    public Boolean checklike(Connection cn, int commentid, int viewerid) throws SQLException {
        String query = "SELECT * FROM commentlike WHERE commentid = ? AND userid = ?";
        ResultSet rs = null;
        PreparedStatement ps = cn.prepareStatement(query);
        ps.setInt(1, commentid);
        ps.setInt(2, viewerid);
        rs = ps.executeQuery();
        while (rs.next()) {
            return true;
        }
        return false;
    }

    public void addlike(Connection cn, int commentid, int viewerid) throws SQLException {
        String query = "INSERT INTO commentlike (userid, commentid) VALUES (?,?)";
        PreparedStatement ps = cn.prepareStatement(query);
        ps.setInt(1, viewerid);
        ps.setInt(2, commentid);
        ps.execute();
    }

    public void deletelike(Connection cn, int commentid, int viewerid) throws SQLException {
        String query = "DELETE FROM commentlike WHERE (userid = ? AND commentid = ?)";
        PreparedStatement ps = cn.prepareStatement(query);
        ps.setInt(1, viewerid);
        ps.setInt(2, commentid);
        ps.execute();
    }
}
