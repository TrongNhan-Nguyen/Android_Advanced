package com.example.android3.assignment.Fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.android3.assignment.Adapter.Adapter_ListView_Transcript;
import com.example.android3.assignment.DAO.DAO_Transcript;
import com.example.android3.assignment.Model.Course;
import com.example.android3.assignment.Model.Transcript;
import com.example.android3.assignment.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Transcript extends Fragment {
    View view;
    ListView listView;
    Adapter_ListView_Transcript adapterTranscript;
    List<Transcript> transcriptList;
    DAO_Transcript dao_transcript;


    public Fragment_Transcript() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_transcript, container, false);
        initView();
        return view;
    }
    public void initView() {
        dao_transcript = new DAO_Transcript(getActivity());
//        insertData();
        listView = (ListView) view.findViewById(R.id.fTranscript_ListView);
        transcriptList = dao_transcript.getAllData();
        adapterTranscript = new Adapter_ListView_Transcript(getActivity(), R.layout.raw_transcript, transcriptList);
        listView.setAdapter(adapterTranscript);
    }

    public void insertData(){
//        dao_transcript.insert(new Transcript("SKI1013", "Kỹ năng học tập", 8,"Passed"));
//        dao_transcript.insert(new Transcript("COM1024", "Tin học văn phòng", 9, "Passed"));
//        dao_transcript.insert(new Transcript("COM1012", "Tin học cơ sở", 8.5,"Passed"));
//        dao_transcript.insert(new Transcript("MUL1013", "Photoshop CS6", 9.5,"Passed"));
//        dao_transcript.insert(new Transcript("COM2012", "Cơ sở dữ liệu", 8.7,"Passed"));
//        dao_transcript.insert(new Transcript("WEB1013", "Lập trình java 1", 9.3,"Passed"));
    }
}
