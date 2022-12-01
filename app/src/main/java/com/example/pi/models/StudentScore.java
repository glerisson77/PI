package com.example.pi.models;

public class StudentScore {

    private String studentName;
    private String studentScorePoint;

    public StudentScore(){}

    public StudentScore(String studentName, String studentScorePoint) {
        this.studentName = studentName;
        this.studentScorePoint = studentScorePoint;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentScorePoint() {
        return studentScorePoint;
    }

    public void setStudentScorePoint(String studentScorePoint) {
        this.studentScorePoint = studentScorePoint;
    }
}
