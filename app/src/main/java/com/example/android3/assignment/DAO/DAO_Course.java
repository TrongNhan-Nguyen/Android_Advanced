package com.example.android3.assignment.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.android3.assignment.Database.Database_Student;
import com.example.android3.assignment.Model.Course;

import java.util.ArrayList;
import java.util.List;

public class DAO_Course {
    SQLiteDatabase db;
    Database_Student database;
    Context context;

    public static final String TABLE_COURSE = "table_course";
    public static final String COURSE_ID = "id";
    public static final String COURSE_NAME = "name_course";
    public static final String SEMESTER_NAME = "semester_name";

    public static final String CREATE_TABLE_COURSE = "CREATE TABLE IF NOT EXISTS " + TABLE_COURSE + "( " +
            COURSE_ID + " TEXT PRIMARY KEY, " +
            COURSE_NAME + " TEXT NOT NULL, " +
            SEMESTER_NAME + " TEXT NOT NULL )";

    public DAO_Course(Context context) {
        this.context = context;
        database = new Database_Student(context);
        writableDatabase();
    }

    public long insert(Course schedule){
        writableDatabase();
        ContentValues values = new ContentValues();
        values.put(COURSE_ID, schedule.getId());
        values.put(COURSE_NAME, schedule.getNameCourse());
        values.put(SEMESTER_NAME, schedule.getNameSemester());
        long result =0;
        try {
            result = db.insert(TABLE_COURSE,null, values);
        }catch (Exception e){

        }
        return result;
    }
    public List<Course> getData(String sql, String...selectionArgs){
        writableDatabase();
        List<Course> scheduleList = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,selectionArgs);
        if (cursor.moveToFirst()){
            scheduleList.clear();
            do {
                Course schedule = new Course();
                schedule.setId(cursor.getString(cursor.getColumnIndex(COURSE_ID)));
                schedule.setNameCourse(cursor.getString(cursor.getColumnIndex(COURSE_NAME)));
                schedule.setNameSemester(cursor.getString(cursor.getColumnIndex(SEMESTER_NAME)));
                scheduleList.add(schedule);
            }while (cursor.moveToNext());
        }
        db.close();
        return scheduleList;
    }
    public List<Course> getDataByName(String name){
        String sql = "SELECT * FROM " + TABLE_COURSE +" WHERE " + COURSE_NAME + "=?";
        List<Course> courseList = getData(sql,name);
        return courseList;
    }
    public List<String> getNames(){
        List<String> listName = new ArrayList<>();
        String sql = "SELECT * FROM "+ TABLE_COURSE;
        List<Course> courseList = getData(sql);
        for (int i = 0; i< courseList.size(); i++){
            listName.add(courseList.get(i).getNameCourse());
        }
        return listName;
    }
    public List<Course> getDataBySemester(String name){
        String sql = "SELECT * FROM " + TABLE_COURSE +" WHERE " + SEMESTER_NAME + "=?";
        List<Course> scheduleList = getData(sql,name);
        return scheduleList;
    }

    public void writableDatabase(){
        db = database.getWritableDatabase();
    }
}
