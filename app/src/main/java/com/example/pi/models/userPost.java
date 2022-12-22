package com.example.pi.models;

public class userPost {

    private String userName;
    private String posteDate;
    private String userProfilePicture;
    private String postContent;
    private String userCourses;

    public userPost(String userName, String posteDate, String userProfilePicture, String postContent, String userCourses) {
        this.userName = userName;
        this.posteDate = posteDate;
        this.userProfilePicture = userProfilePicture;
        this.postContent = postContent;
        this.userCourses = userCourses;
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
}
