package com.example.android3.assignment.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.widget.ListView;

import androidx.annotation.RequiresApi;

import com.example.android3.assignment.Database.Database_Student;
import com.example.android3.assignment.Model.Schedule;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DAO_Schedule {
    Context context;
    Database_Student database;
    SQLiteDatabase db;
    public static final String TABLE_SCHEDULE = "table_schedule";
    public static final String DATE = "date";
    public static final String COURSE_ID = "id_course";
    public static final String COURSE_NAME = "name_course";
    public static final String BLOCK = "block";
    public static final String SHIFT = "shift";

    public static final String CREATE_TABLE_SCHEDULE = "CREATE TABLE IF NOT EXISTS " +
            TABLE_SCHEDULE + "( " +
                DATE + " TEXT NOT NULL, " +
                COURSE_ID + " TEXT NOT NULL, " +
                COURSE_NAME + " TEXT NOT NULL, " +
                BLOCK + " INTEGER NOT NULL, " +
                SHIFT + " INTEGER NOT NULL, " +
                "PRIMARY KEY (" + DATE + "," + COURSE_ID +"))";

    public DAO_Schedule(Context context) {
        this.context = context;
        database = new Database_Student(context);
        writableDatabase();
    }

    public long insert(String startDay, String endDay, String days, Schedule schedule){
        writableDatabase();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localSDate = LocalDate.parse(startDay, formatter);
        LocalDate localEDate = LocalDate.parse(endDay, formatter);
        System.out.println(formatter.format(localSDate));
        ContentValues values = new ContentValues();
        DayOfWeek dayOfWeek = null;
        int val = 0;
        long result = 0;
        LocalDate date = localSDate;
        while (!date.isAfter(localEDate)) {
            dayOfWeek = DayOfWeek.from(date);
            val = dayOfWeek.getValue();
            if (days.equalsIgnoreCase("Even")){
                if (val == 1 || val == 3 || val == 5) {
                    values.put(DATE,formatter.format(date));
                    values.put(COURSE_ID, schedule.getCourseId());
                    values.put(COURSE_NAME, schedule.getCourseName());
                    values.put(BLOCK, schedule.getBlock());
                    values.put(SHIFT, schedule.getShift());
                    result = db.insert(TABLE_SCHEDULE,null,values);
                }
            }else if (days.equalsIgnoreCase("Odd")){
                if (val == 2 || val == 4 || val == 6) {
                    values.put(DATE,formatter.format(date));
                    values.put(COURSE_ID, schedule.getCourseId());
                    values.put(COURSE_NAME, schedule.getCourseName());
                    values.put(BLOCK, schedule.getBlock());
                    values.put(SHIFT, schedule.getShift());
                    result = db.insert(TABLE_SCHEDULE,null,values);
                }
            }
            date = date.plusDays(1);
        }
        return result;
    }

    public void deleteAll(){
        writableDatabase();
        String sql = "DELETE FROM " + TABLE_SCHEDULE;
        db.execSQL(sql);
    }

    public List<Schedule> getData(String sql, String...Args){
        writableDatabase();
        List<Schedule> scheduleList = new ArrayList<>();

        Cursor cursor = db.rawQuery(sql,Args);
        if (cursor.moveToFirst()){
            scheduleList.clear();
            do {
                Schedule schedule = new Schedule();
                schedule.setDate(cursor.getString(cursor.getColumnIndex(DATE)));
                schedule.setCourseId(cursor.getString(cursor.getColumnIndex(COURSE_ID)));
                schedule.setCourseName(cursor.getString(cursor.getColumnIndex(COURSE_NAME)));
                schedule.setBlock(cursor.getInt(cursor.getColumnIndex(BLOCK)));
                schedule.setShift(cursor.getInt(cursor.getColumnIndex(SHIFT)));
                scheduleList.add(schedule);

            }while (cursor.moveToNext());
        }

        return scheduleList;
    }



    public List<Schedule> getAllData(){
        String sql = "SELECT * FROM " + TABLE_SCHEDULE + " ORDER BY " + DATE;
        List<Schedule> scheduleList = getData(sql);
        return scheduleList;
    }

    public List<Schedule> getDataByDate(String start, String end){
        String sql = "SELECT * FROM " + TABLE_SCHEDULE + " WHERE " + DATE + " BETWEEN '" + start +
            "' AND '" + end + "'";
        List<Schedule> scheduleList = getData(sql);
        return scheduleList;
    }
    public List<Schedule> getExamDay(){
        String sql = "SELECT * FROM (SELECT * FROM " + TABLE_SCHEDULE + " ORDER BY " + DATE + " ASC) " +
                " GROUP BY " + COURSE_NAME;
        List<Schedule> scheduleList = getData(sql);
        return scheduleList;
    }




    public void writableDatabase(){
        db  = database.getWritableDatabase();
    }
}
