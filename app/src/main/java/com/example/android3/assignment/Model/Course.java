package com.example.android3.assignment.Model;

public class Course {
    String id, nameCourse, nameSemester;


    public Course() {
    }

    public Course(String id, String nameCourse) {
        this.id = id;
        this.nameCourse = nameCourse;
    }

    public Course(String id, String nameCourse, String nameSemester) {
        this.id = id;
        this.nameCourse = nameCourse;
        this.nameSemester = nameSemester;
    }





    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameCourse() {
        return nameCourse;
    }

    public void setNameCourse(String nameCourse) {
        this.nameCourse = nameCourse;
    }

    public String getNameSemester() {
        return nameSemester;
    }

    public void setNameSemester(String nameSemester) {
        this.nameSemester = nameSemester;
    }

}
