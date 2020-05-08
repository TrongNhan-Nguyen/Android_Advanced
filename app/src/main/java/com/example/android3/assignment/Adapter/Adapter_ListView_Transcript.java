package com.example.android3.assignment.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.android3.assignment.Model.Course;
import com.example.android3.assignment.Model.Transcript;
import com.example.android3.assignment.R;

import java.util.List;

public class Adapter_ListView_Transcript extends BaseAdapter {
    Context context;
    int layout;
    List<Transcript> transcriptList;

    public Adapter_ListView_Transcript(Context context, int layout, List<Transcript> transcriptList) {
        this.context = context;
        this.layout = layout;
        this.transcriptList = transcriptList;
    }

    @Override
    public int getCount() {
        return transcriptList.size();
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
        TextView txtID = (TextView) convertView.findViewById(R.id.raw_Transcript_ID);
        TextView txtName = (TextView) convertView.findViewById(R.id.raw_Transcript_Name);
        TextView txtScore = (TextView) convertView.findViewById(R.id.raw_Transcript_Scores);
        TextView txtStatus = (TextView) convertView.findViewById(R.id.raw_Transcript_Status);

        Transcript transcript = transcriptList.get(position);

        txtID.setText(transcript.getCourseID());
        txtName.setText(transcript.getCourseName());
        txtScore.setText(String.valueOf(transcript.getScores()));
        txtStatus.setText(transcript.getStatus());
        return convertView;
    }
}
