package com.example.pi.models;

import com.google.firebase.database.FirebaseDatabase;

public class UserInformation {

    private String userName;
    private String userRa;
    private String courses;
    private String profilePicture;
    private String oldProfilePicture;
    private String status;
    private String userId;
//    add the id to find the right user

    public UserInformation(){}

    public UserInformation(String userName, String userRa, String courses, String profilePicture, String oldProfilePicture, String status, String userId) {
        this.userName = userName;
        this.userRa = userRa;
        this.courses = courses;
        this.profilePicture = profilePicture;
        this.oldProfilePicture = oldProfilePicture;
        this.status = status;
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserRa() {
        return userRa;
    }

    public void setUserRa(String userRa) {
        this.userRa = userRa;
    }

    public String getCourses() {
        return courses;
    }

    public void setCourses(String courses) {
        this.courses = courses;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getOldProfilePicture() {
        return oldProfilePicture;
    }

    public void setOldProfilePicture(String oldProfilePicture) {
        this.oldProfilePicture = oldProfilePicture;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
