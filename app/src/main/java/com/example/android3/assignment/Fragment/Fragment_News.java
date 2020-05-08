package com.example.android3.assignment.Fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ListView;
import android.widget.ViewFlipper;

import androidx.fragment.app.Fragment;

import com.example.android3.assignment.Model.ReadRss;
import com.example.android3.assignment.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_News extends Fragment {
    View view;
    ReadRss readRss;
    ListView listView;
    ViewFlipper viewFlipper;
    Animation slide_in, slide_out;

    public Fragment_News() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_news, container, false);
        initView();
        return view;
    }

    public void initView() {
        listView = (ListView) view.findViewById(R.id.fNews_ListViewRss);
        viewFlipper = (ViewFlipper) view.findViewById(R.id.fNews_VF);

        slide_in = AnimationUtils.loadAnimation(getActivity(),R.anim.slide_in);
        slide_out = AnimationUtils.loadAnimation(getActivity(),R.anim.slide_out);
        readRss = new ReadRss(getActivity(),listView);
        readRss.execute();
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);
        viewFlipper.setInAnimation(slide_in);
        viewFlipper.setOutAnimation(slide_out);



    }
}
