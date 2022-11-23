package com.example.pi;

public class projectInformation {

    private String projectName;
    private String professorName;
    private String projectResume;
    private String projectContact;
    private String imageName;

    public projectInformation(){}

    public projectInformation(String projectName, String professorName, String projectResume, String projectContact, String imageName) {
        this.projectName = projectName;
        this.professorName = professorName;
        this.projectResume = projectResume;
        this.projectContact = projectContact;
        this.imageName = imageName;
    }

    public String getName() {
        return projectName;
    }

    public void setName(String professorName) {
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


}
