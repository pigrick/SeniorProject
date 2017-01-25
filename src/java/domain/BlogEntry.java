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
public class BlogEntry {

    private int id;
    private String title;
    private String detail;
    private int userid;
    private Date created;
    private String username;
    private int likes;
    private Boolean viewerlikes;

    public BlogEntry() {
    }

    public BlogEntry(String title, String detail, int userid) {
        this.title = title;
        this.detail = detail;
        this.userid = userid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    
    
    
}
