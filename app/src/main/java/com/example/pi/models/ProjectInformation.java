package com.example.pi.models;

public class ProjectInformation {

    private String projectName;
    private String professorName;
    private String projectResume;
    private String projectContact;
    private String imageName;
    private String raMatching;
    private String userUploader;

    public ProjectInformation(){}

    public ProjectInformation(String projectName, String professorName, String projectResume, String projectContact, String imageName, String raMatching, String userUploader) {
        this.projectName = projectName;
        this.professorName = professorName;
        this.projectResume = projectResume;
        this.projectContact = projectContact;
        this.imageName = imageName;
        this.raMatching = raMatching;
        this.userUploader = userUploader;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProfessorName() {
        return professorName;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }

    public String getProjectResume() {
        return projectResume;
    }

    public void setProjectResume(String projectResume) {
        this.projectResume = projectResume;
    }

    public String getProjectContact() {
        return projectContact;
    }

    public void setProjectContact(String projectContact) {
        this.projectContact = projectContact;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getRaMatching() {
        return raMatching;
    }

    public void setRaMatching(String raMatching) {
        this.raMatching = raMatching;
    }

    public String getUserUploader() {
        return userUploader;
    }

    public void setUserUploader(String userUploader) {
        this.userUploader = userUploader;
    }
}
