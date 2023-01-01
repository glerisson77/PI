package com.example.pi.models;

public class userPost {

    private String userName;
    private String posteDate;
    private String userProfilePicture;
    private String postContent;
    private String userCourses;
    private String userRa;
    private String postID;
    private String userID;

    public userPost(){}

    public userPost(String userName, String posteDate, String userProfilePicture, String postContent, String userCourses, String userRa, String postID, String userID) {
        this.userName = userName;
        this.posteDate = posteDate;
        this.userProfilePicture = userProfilePicture;
        this.postContent = postContent;
        this.userCourses = userCourses;
        this.userRa = userRa;
        this.postID = postID;
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPosteDate() {
        return posteDate;
    }

    public void setPosteDate(String posteDate) {
        this.posteDate = posteDate;
    }

    public String getUserProfilePicture() {
        return userProfilePicture;
    }

    public void setUserProfilePicture(String userProfilePicture) {
        this.userProfilePicture = userProfilePicture;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public String getUserCourses() {
        return userCourses;
    }

    public void setUserCourses(String userCourses) {
        this.userCourses = userCourses;
    }

    public String getUserRa() {
        return userRa;
    }

    public void setUserRa(String userRa) {
        this.userRa = userRa;
    }

    public String getPostID() {
        return postID;
    }

    public void setPostID(String postID) {
        this.postID = postID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
