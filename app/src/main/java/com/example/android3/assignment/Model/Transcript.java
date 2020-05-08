package com.example.android3.assignment.Model;

public class Transcript {
     String courseID, courseName,status;
    double scores;

    public Transcript() {
    }

    public Transcript(String courseID, String courseName,double scores,  String status) {
        this.courseID = courseID;
        this.courseName = courseName;

        this.status = status;
        this.scores = scores;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getScores() {
        return scores;
    }

    public void setScores(double scores) {
        this.scores = scores;
    }
}
