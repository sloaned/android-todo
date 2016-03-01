package com.catalystdevworks.todo.entities;

/**
 * Created by g on 2/29/16.
 */
public class Token {
    public Token(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private String token;
}
