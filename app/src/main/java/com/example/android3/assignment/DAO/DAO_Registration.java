package com.example.android3.assignment.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.android3.assignment.Database.Database_Student;
import com.example.android3.assignment.Model.Course;

import java.util.ArrayList;
import java.util.List;

public class DAO_Registration {
    SQLiteDatabase db;
    Database_Student database;
    Context context;


    public static final String TABLE_REGISTRATION = "table_registration";
    public static final String COURSE_NAME = "course_name";
    public static final String COURSE_ID = "course_id";
    public static final String CREATE_TABLE_REGISTRATION = "CREATE TABLE IF NOT EXISTS " + TABLE_REGISTRATION + "( " +
            COURSE_ID + " TEXT PRIMARY KEY NOT NULL, " +
            COURSE_NAME + " TEXT NOT NULL)";

    public DAO_Registration(Context context) {
        this.context = context;
        database = new Database_Student(context);
        writableDatabase();
    }

//    public long insert(Semesters semesters){
//        writableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(NAME, semesters.getName());
//        long result = 0;
//        try {
//            result = db.insert(TABLE_REGISTRATION,null, values);
//        }catch (Exception e){
//
//        }
//        return result;
//    }
//    public List<Semesters> getDataBySemester(){
//        writableDatabase();
//        List<Semesters> semestersList = new ArrayList<>();
//        String selectQuery = "SELECT * FROM " + TABLE_REGISTRATION;
//        Cursor cursor = db.rawQuery(selectQuery,null);
//        if (cursor.moveToFirst()){
//            do {
//                Semesters semesters = new Semesters();
//                semesters.setName(cursor.getString(cursor.getColumnIndex(NAME)));
//                semestersList.add(semesters);
//            }while (cursor.moveToNext());
//        }
//        db.close();
//        return semestersList;
//    }
//
//    public int deleteCourse(Semesters semesters){
//        writableDatabase();
//        String selection = NAME + "=?";
//        String[] selectArgs = {semesters.getName()};
//        return db.delete(TABLE_REGISTRATION,selection,selectArgs);
//    }

    public long insert(Course schedule){
        writableDatabase();
        ContentValues values = new ContentValues();
        values.put(COURSE_ID, schedule.getId());
        values.put(COURSE_NAME,schedule.getNameCourse());
        long result = 0;
        try {
            result = db.insert(TABLE_REGISTRATION,null, values);
        }catch (Exception e){

        }
        return result;
    }
    public List<Course> getData(){
        writableDatabase();
        List<Course> scheduleList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_REGISTRATION;
        Cursor cursor = db.rawQuery(selectQuery,null);
        if (cursor.moveToFirst()){
            scheduleList.clear();
            do {
                Course schedule = new Course();
                schedule.setId(cursor.getString(cursor.getColumnIndex(COURSE_ID)));
                schedule.setNameCourse(cursor.getString(cursor.getColumnIndex(COURSE_NAME)));
                scheduleList.add(schedule);
            }while (cursor.moveToNext());


        }
        db.close();
        return scheduleList;
    }

    public int deleteCourse(Course course){
        writableDatabase();
        String selection = COURSE_ID + "=?";
        String[] selectArgs = {course.getId()};
        return db.delete(TABLE_REGISTRATION,selection,selectArgs);
    }


    public void writableDatabase(){
        db = database.getWritableDatabase();
    }
}
