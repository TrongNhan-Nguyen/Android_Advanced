package com.example.android3.assignment.Fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.android3.assignment.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_About extends Fragment {
    View view;
    TextView txtInfo, txtInfoDetail, txtGuie, txtGuideDetail, txtPrivacy, txtPrivacyDetail, txtRelease, txtReleaseDetail;

    public Fragment_About() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_about, container, false);
        initView();

        return view;
    }

    private void initView() {
        txtInfo = (TextView) view.findViewById(R.id.fgGT_Info);
        txtGuie = (TextView) view.findViewById(R.id.fgGT_Guide);
        txtPrivacy = (TextView) view.findViewById(R.id.fgGT_Privacy);
        txtRelease = (TextView) view.findViewById(R.id.fgGT_Release);
        txtInfoDetail = (TextView) view.findViewById(R.id.fgGT_InfoDetail);
        txtGuideDetail = (TextView) view.findViewById(R.id.fgGT_GuideDetail);
        txtPrivacyDetail = (TextView) view.findViewById(R.id.fgGT_PrivacyDetail);
        txtReleaseDetail = (TextView) view.findViewById(R.id.fgGT_ReleaseDetail);

        hideDetail();

        txtInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleDetail(txtInfoDetail);
            }
        });


        txtGuie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleDetail(txtGuideDetail);
            }
        });


        txtPrivacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleDetail(txtPrivacyDetail);
            }
        });


        txtRelease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleDetail(txtReleaseDetail);
            }
        });
    }

    public void toggleDetail(View view){
        if (view.getVisibility() == view.VISIBLE){
            view.setVisibility(view.GONE);
        }else {
            view.setVisibility(view.VISIBLE);
        }

    }


    public void hideDetail(){
        txtInfoDetail.setVisibility(TextView.GONE);
        txtGuideDetail.setVisibility(TextView.GONE);
        txtPrivacyDetail.setVisibility(TextView.GONE);
        txtReleaseDetail.setVisibility(TextView.GONE);
    }
}
