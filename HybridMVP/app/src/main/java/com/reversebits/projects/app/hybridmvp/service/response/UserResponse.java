package com.reversebits.projects.app.hybridmvp.service.response;

/**
 * Created by TapanHP on 11/10/2016.
 */

public class UserResponse {

    private String name;
    private String photoUri;
    private String userMail;
    private String userID;

    public UserResponse(String userID, String name, String userMail, String photoUri ) {
        this.name = name;
        this.photoUri = photoUri;
        this.userMail = userMail;
        this.userID = userID;
    }

    public UserResponse() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoUri() {
        return photoUri;
    }

    public void setPhotoUri(String photoUri) {
        this.photoUri = photoUri;
    }



    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
