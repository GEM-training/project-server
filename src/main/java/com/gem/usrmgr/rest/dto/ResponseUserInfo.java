package com.gem.usrmgr.rest.dto;

import java.util.List;

/**
 * Created by quanda on 19/02/2016.
 */
public class ResponseUserInfo {
    private String username;
    private String token;
    private List<String> role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<String> getRole() {
        return role;
    }

    public void setRole(List<String> role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "ResponseUserInfo{" +
                "username='" + username + '\'' +
                ", token='" + token + '\'' +
                ", role=" + role +
                '}';
    }
}
