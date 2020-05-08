package com.example.android3.assignment.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.android3.assignment.Model.Schedule;
import com.example.android3.assignment.R;

import java.util.List;

public class Adapter_ListView_Schedule extends BaseAdapter {
    Context context;
    int layout;
    List<Schedule> scheduleList;

    public Adapter_ListView_Schedule(Context context, int layout, List<Schedule> scheduleList) {
        this.context = context;
        this.layout = layout;
        this.scheduleList = scheduleList;
    }

    @Override
    public int getCount() {
        return scheduleList.size();
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
        convertView = inflater.inflate(layout,null);

        TextView txtDate = (TextView) convertView.findViewById(R.id.raw_schedule_txtDate);
        TextView txtCourseID = (TextView) convertView.findViewById(R.id.raw_schedule_txtCourseID);
        TextView txtCourseName = (TextView) convertView.findViewById(R.id.raw_schedule_txtCourseName);
        TextView txtBlock = (TextView) convertView.findViewById(R.id.raw_schedule_txtBlock);
        TextView txtShift = (TextView) convertView.findViewById(R.id.raw_schedule_txtShift);

        if (position %2 == 0){
            convertView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }else {
            convertView.setBackgroundColor(Color.parseColor("#FFCCCCCC"));
        }

        Schedule schedule = scheduleList.get(position);
        txtDate.setText(schedule.getDate());
        txtCourseID.setText(schedule.getCourseId());
        txtCourseName.setText(schedule.getCourseName());
        txtBlock.setText(String.valueOf(schedule.getBlock()));
        txtShift.setText(String.valueOf(schedule.getShift()));


        return convertView;
    }
}
