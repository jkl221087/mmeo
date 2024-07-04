package com.example.memowebapp.model;

import java.util.Date;

public class Memo {
    private Long id;
    private String title;
    private String content;
    private Date createdDate;
    private Long userId;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Long getUserId() {
        return userId;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    // Constructor, getters and setters
    // 省略了構造函數、getter和setter方法
}
