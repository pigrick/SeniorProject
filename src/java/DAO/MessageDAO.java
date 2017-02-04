/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import domain.BlogEntry;
import domain.Message;
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
public class MessageDAO {
    
    public ArrayList<Message> findMessage(Connection cn, int userid, int targetid) throws SQLException {
        UserDAO UDAO = new UserDAO();
        
        String query = "SELECT * FROM message WHERE (sendid = ? AND receiveid = ?) OR (sendid = ? AND receiveid = ?) ORDER BY id";
        ResultSet rs = null;
        ArrayList<Message> messages = new ArrayList<>();
        PreparedStatement ps = cn.prepareStatement(query);
        ps.setInt(1, userid);
        ps.setInt(2, targetid);
        ps.setInt(3, targetid);
        ps.setInt(4, userid);
        rs = ps.executeQuery();
        
        while(rs.next()){
            Message me = new Message();
            me.setId(rs.getInt("id"));
            me.setSendid(rs.getInt("sendid"));
            me.setSendname(UDAO.getUsername(cn, rs.getInt("sendid")));
            me.setReceiveid(rs.getInt("receiveid"));
            me.setReceivename(UDAO.getUsername(cn, rs.getInt("receiveid")));
            me.setDetails(rs.getString("details"));
            me.setCreated(rs.getDate("created"));
            me.setReaded(rs.getBoolean("readed"));
            messages.add(me);
        }
        return messages;
    }
    
     public ArrayList<Message> findMessageWithLimit(Connection cn, int userid, int targetid, int commentid) throws SQLException {
        UserDAO UDAO = new UserDAO();
        
        String query = "SELECT * FROM message WHERE ((sendid = ? AND receiveid = ?) OR (sendid = ? AND receiveid = ?)) AND id > ? ORDER BY id";
        ResultSet rs = null;
        ArrayList<Message> messages = new ArrayList<>();
        PreparedStatement ps = cn.prepareStatement(query);
        ps.setInt(1, userid);
        ps.setInt(2, targetid);
        ps.setInt(3, targetid);
        ps.setInt(4, userid);
        ps.setInt(5, commentid);
        rs = ps.executeQuery();
        
        while(rs.next()){
            Message me = new Message();
            me.setId(rs.getInt("id"));
            me.setSendid(rs.getInt("sendid"));
            me.setSendname(UDAO.getUsername(cn, rs.getInt("sendid")));
            me.setReceiveid(rs.getInt("receiveid"));
            me.setReceivename(UDAO.getUsername(cn, rs.getInt("receiveid")));
            me.setDetails(rs.getString("details"));
            me.setCreated(rs.getDate("created"));
            me.setReaded(rs.getBoolean("readed"));
            messages.add(me);
        }
        return messages;
    }
    
     public int lastIdOfMessage(Connection cn, int userid, int targetid) throws SQLException{
        String query = "SELECT * FROM message WHERE (sendid = ? AND receiveid = ?) OR (sendid = ? AND receiveid = ?) ORDER BY id DESC LIMIT 1";
        ResultSet rs = null;
        PreparedStatement ps = cn.prepareStatement(query);
        ps.setInt(1, userid);
        ps.setInt(2, targetid);
        ps.setInt(3, targetid);
        ps.setInt(4, userid);
        rs = ps.executeQuery();
        while(rs.next()){
            return (rs.getInt("id"));
        }
        return 0;
    }
    
    public void addMessage(Connection cn, int sendid, int receiveid, String details) throws SQLException{
        String query = "INSERT INTO message (sendid, receiveid, details, readed) values(?,?,?, 0)";
        PreparedStatement ps = cn.prepareStatement(query);
        ps.setInt(1, sendid);
        ps.setInt(2, receiveid);
        ps.setString(3, details);
        ps.execute();
        
    }
    
    public void read(Connection cn, int messageid) throws SQLException{
        String query = "UPDATE message SET readed = 1 WHERE id = ?";
        PreparedStatement ps = cn.prepareStatement(query);
        ps.setInt(1, messageid);
        ps.execute();
    }
    
    public ArrayList<String> getNameFromUnreadMessage(Connection cn, int receiveid) throws SQLException{
        String query = "SELECT DISTINCT(sendid) FROM message WHERE receiveid = ? AND readed = 0";
        PreparedStatement ps = cn.prepareStatement(query);
        UserDAO UDAO = new UserDAO();
        ResultSet rs = null;
        ps.setInt(1, receiveid);
        rs = ps.executeQuery();
        ArrayList<String> names = new ArrayList<>();
        while(rs.next()){
            names.add(UDAO.getUsername(cn, rs.getInt("sendid")));
        }
        return names;
    
    }   
}
