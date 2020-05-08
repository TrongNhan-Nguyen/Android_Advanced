package com.example.android3.assignment.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.android3.assignment.DAO.DAO_Registration;
import com.example.android3.assignment.DAO.DAO_Course;
import com.example.android3.assignment.DAO.DAO_Schedule;
import com.example.android3.assignment.DAO.DAO_Semesters;
import com.example.android3.assignment.DAO.DAO_Transcript;

public class Database_Student extends SQLiteOpenHelper {
    public static final String DATABASE = "student.db";
    public static final int VERSION = 1;
    public Database_Student(@Nullable Context context) {
        super(context, DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DAO_Semesters.CREATE_TABLE_SEMESTERS);
        db.execSQL(DAO_Course.CREATE_TABLE_COURSE);
        db.execSQL(DAO_Registration.CREATE_TABLE_REGISTRATION);
        db.execSQL(DAO_Schedule.CREATE_TABLE_SCHEDULE);
        db.execSQL(DAO_Transcript.CREATE_TABLE_TRANSCRIPT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
