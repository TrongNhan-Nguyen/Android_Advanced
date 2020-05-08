package com.example.android3.assignment.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.android3.assignment.Database.Database_Student;
import com.example.android3.assignment.Model.Transcript;

import java.util.ArrayList;
import java.util.List;

public class DAO_Transcript {
    Context context;
    SQLiteDatabase db;
    Database_Student database;

    public static final String TABLE_TRANSCRIPT = "table_transcript";
    public static final String COURSE_ID = "course_id";
    public static final String COURSE_NAME = "course_name";
    public static final String SCORES = "scores";
    public static final String STATUS = "status";
    public static final String CREATE_TABLE_TRANSCRIPT = "CREATE TABLE IF NOT EXISTS " + TABLE_TRANSCRIPT +"( " +
            COURSE_ID + " TEXT PRIMARY KEY, " +
            COURSE_NAME + " TEXT NOT NULL, " +
            SCORES + " REAL, " +
            STATUS + " TEXT NOT NULL)";


    public DAO_Transcript(Context context) {
        this.context = context;
        database = new Database_Student(context);
        writableDataBase();
    }

    public long insert(Transcript transcript){
        writableDataBase();
        long result = 0;
        ContentValues values = new ContentValues();
        values.put(COURSE_ID,transcript.getCourseID());
        values.put(COURSE_NAME, transcript.getCourseName());
        values.put(SCORES, transcript.getScores());
        values.put(STATUS, transcript.getStatus());
        try{
            result = db.insert(TABLE_TRANSCRIPT,null,values);
        }catch (Exception e){

        }
        return  result;
    }

    public List<Transcript> getData(String sql, String...Args){
        writableDataBase();
        List<Transcript> transcriptList = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,Args);
        if (cursor.moveToFirst()){
            do {
                Transcript transcript = new Transcript();
                transcript.setCourseID(cursor.getString(cursor.getColumnIndex(COURSE_ID)));
                transcript.setCourseName(cursor.getString(cursor.getColumnIndex(COURSE_NAME)));
                transcript.setScores(cursor.getInt(cursor.getColumnIndex(SCORES)));
                transcript.setStatus(cursor.getString(cursor.getColumnIndex(STATUS)));
                transcriptList.add(transcript);

            }while (cursor.moveToNext());
        }
        return transcriptList;
    }

    public void deleteLearning(){
        String sql = "DELETE FROM " + TABLE_TRANSCRIPT + " WHERE " + STATUS + " = 'Learning'" ;
        db.execSQL(sql);

    }
    public List<Transcript> getAllData(){
        String sql = "SELECT * FROM " + TABLE_TRANSCRIPT;
        List<Transcript> transcriptList = getData(sql);
        return transcriptList;
    }
    public List<Transcript> getDataLearning() {
        String sql = "SELECT * FROM " + TABLE_TRANSCRIPT + " WHERE " + STATUS + " = 'Learning'";
        List<Transcript> transcriptList = getData(sql);
        return transcriptList;
    }
    public void writableDataBase(){
        db = database.getWritableDatabase();
    }
}
