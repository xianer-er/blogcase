package com.xianer.controller;

//import java.security.Timestamp;
import java.sql.Timestamp;
public class Blogs {
   private int id;
   private String title;
   private String category;
   private String updatetime;
   private String content;

    public Blogs() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    @Override
    public String toString() {
        return "Blogs{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", category='" + category + '\'' +
                ", updatetime='" + updatetime + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
