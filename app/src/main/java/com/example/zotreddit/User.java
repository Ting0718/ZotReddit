package com.example.zotreddit;

import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private ArrayList<Message> posts;

    public User()
    {
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
