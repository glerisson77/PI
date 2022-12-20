package com.example.pi.models;

public class UserInformation {

    private String userName;
    private String userRa;
    private String courses;
    private String profilePicture;
    private String status;
//    add the id to find the right user

    public UserInformation(){}

    public UserInformation(String userName, String userRa, String courses, String profilePicture, String status) {
        this.userName = userName;
        this.userRa = userRa;
        this.courses = courses;
        this.profilePicture = profilePicture;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
