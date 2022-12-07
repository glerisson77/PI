package com.example.pi.models;

public class StudentScore {

    private String studentName;
    private int studentScorePoint;
    private String Ra;

    public StudentScore(){}

    public StudentScore(String studentName, int studentScorePoint, String ra) {
        this.studentName = studentName;
        this.studentScorePoint = studentScorePoint;
        Ra = ra;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getStudentScorePoint() {
        return studentScorePoint;
    }

    public void setStudentScorePoint(int studentScorePoint) {
        this.studentScorePoint = studentScorePoint;
    }

    public String getRa() {
        return Ra;
    }

    public void setRa(String ra) {
        Ra = ra;
    }
}
