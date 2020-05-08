package com.example.android3.assignment.Model;

public class Schedule {
    String date, courseId, courseName;
    int block, shift;

    public Schedule() {
    }

    public Schedule(String courseId, String courseName, int block, int shift) {
        this.date = date;
        this.courseId = courseId;
        this.courseName = courseName;
        this.block = block;
        this.shift = shift;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getBlock() {
        return block;
    }

    public void setBlock(int block) {
        this.block = block;
    }

    public int getShift() {
        return shift;
    }

    public void setShift(int shift) {
        this.shift = shift;
    }
}
