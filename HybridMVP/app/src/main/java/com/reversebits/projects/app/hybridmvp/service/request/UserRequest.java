package com.reversebits.projects.app.hybridmvp.service.request;

/**
 * Created by TapanHP on 11/10/2016.
 */

public class UserRequest {

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String email;
    private String password;

}
