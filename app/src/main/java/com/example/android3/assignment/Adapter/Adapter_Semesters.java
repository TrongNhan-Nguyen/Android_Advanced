package com.example.android3.assignment.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.android3.assignment.Model.Semesters;
import com.example.android3.assignment.R;

import java.util.List;

public class Adapter_Semesters extends BaseAdapter {
    Context context;
    int layout;
    List<Semesters> semestersList;

    public Adapter_Semesters(Context context, int layout, List<Semesters> semestersList) {
        this.context = context;
        this.layout = layout;
        this.semestersList = semestersList;
    }

    @Override
    public int getCount() {
        return semestersList.size();
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
        TextView textView = (TextView) convertView.findViewById(R.id.rawSpinner_Semesters);
        Semesters semesters = semestersList.get(position);
        textView.setText(semesters.getName());

        return convertView;
    }
}
