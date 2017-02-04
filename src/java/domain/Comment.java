/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.Date;

/**
 *
 * @author yeerick
 */
public class Comment {
    private int id;
    private String comment;
    private int beid;
    private int userid;
    private Date created;
    private String username;
    private int likes;
    private Boolean viewerlikes;
    private Boolean read;
    
    public Comment(){
        
    }

    public Comment(String comment, int beid, int userid) {
        this.comment = comment;
        this.beid = beid;
        this.userid = userid;
    }
    
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getBeid() {
        return beid;
    }

    public void setBeid(int beid) {
        this.beid = beid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public Boolean getViewerlikes() {
        return viewerlikes;
    }

    public void setViewerlikes(Boolean viewerlikes) {
        this.viewerlikes = viewerlikes;
    }

    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }
    
    
    
}
