package com.example.pi.models;

public class StudentScore {

    private String studentName;
    private String studentScorePoint;
    private String Ra;

    public StudentScore(){}

    public StudentScore(String studentName, String studentScorePoint, String Ra) {
        this.studentName = studentName;
        this.studentScorePoint = studentScorePoint;
        this.Ra = Ra;
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

    public String getRa() {
        return Ra;
    }

    public void setRa(String ra) {
        this.Ra = ra;
    }
}
