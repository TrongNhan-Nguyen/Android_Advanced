package com.example.android3.assignment.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.android3.assignment.Model.Course;
import com.example.android3.assignment.R;

import java.util.List;

public class Adapter_ListView_Course extends BaseAdapter {
    Context context;
    int layout;
    List<Course> courses;

    public Adapter_ListView_Course(Context context, int layout, List<Course> courses) {
        this.context = context;
        this.layout = layout;
        this.courses = courses;
    }

    @Override
    public int getCount() {
        return courses.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layout, null);
        TextView txtID = (TextView) convertView.findViewById(R.id.raw_Course_ID);
        TextView txtNameCourse = (TextView) convertView.findViewById(R.id.raw_Course_Name);
        TextView txtNameSemester = (TextView) convertView.findViewById(R.id.raw_Course_Semester);
        Course schedule = courses.get(position);

        txtID.setText(schedule.getId());
        txtNameCourse.setText(schedule.getNameCourse());
        txtNameSemester.setText(schedule.getNameSemester());

        return convertView;
    }
}
