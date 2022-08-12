package com.ldh.monsterlearn.Model;

import com.google.gson.annotations.SerializedName;

public class LogInResult {
    @SerializedName("statusCode")
    private int statusCode;
    @SerializedName("user")
    private User user;

    public LogInResult(int statusCode, User user) {
        this.statusCode = statusCode;
        this.user = user;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
