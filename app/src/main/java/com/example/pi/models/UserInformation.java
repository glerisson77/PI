package com.example.pi.models;

public class UserInformation {

    private String userName;
    private String userRa;

    public UserInformation(){}

    public UserInformation(String userName, String userRa) {
        this.userName = userName;
        this.userRa = userRa;
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
}
