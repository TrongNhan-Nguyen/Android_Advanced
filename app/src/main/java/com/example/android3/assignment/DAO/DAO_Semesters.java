package com.example.android3.assignment.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.android3.assignment.Database.Database_Student;
import com.example.android3.assignment.Model.Semesters;

import java.util.ArrayList;
import java.util.List;

public class DAO_Semesters {
    SQLiteDatabase db;
    Database_Student database;
    Context context;

    public static final String TABLE_SEMESTER = "table_semesters";
    public static final String SEMESTERS = "semesters";
    public static final String CREATE_TABLE_SEMESTERS = "CREATE TABLE IF NOT EXISTS " + TABLE_SEMESTER +
            "( " + SEMESTERS + " TEXT PRIMARY KEY NOT NULL )";

    public DAO_Semesters(Context context) {
        this.context = context;
        database = new Database_Student(context);
        writableDatabase();
    }

    public long insert(Semesters semesters){
        writableDatabase();
        ContentValues values = new ContentValues();
        values.put(SEMESTERS, semesters.getName());
        long result = 0;
        try {
            result = db.insert(TABLE_SEMESTER,null, values);
        }catch (Exception e){

        }
        return 0;
    }

    public List<Semesters> getData(){
        writableDatabase();
        List<Semesters> semestersList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_SEMESTER;
        Cursor cursor = db.rawQuery(selectQuery,null);
        if (cursor.moveToFirst()){
            do {
                Semesters semesters = new Semesters();
                semesters.setName(cursor.getString(cursor.getColumnIndex(SEMESTERS)));
                semestersList.add(semesters);
            }while (cursor.moveToNext());
        }
        db.close();
        return semestersList;
    }


    public void writableDatabase(){
        db = database.getWritableDatabase();
    }
}
