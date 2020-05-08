package com.example.android3.assignment.Fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.android3.assignment.Adapter.Adapter_TabStudent;
import com.example.android3.assignment.R;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_TabStudent extends Fragment {
    View view;
    TabLayout tabLayout;
    ViewPager pager;

    public Fragment_TabStudent() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_tabstudent, container, false);
        initView();

        return view;
    }
    public void initView(){
        tabLayout = (TabLayout) view.findViewById(R.id.tab_Student);
        pager = (ViewPager) view.findViewById(R.id.vp_Student);
        pager.setAdapter(new Adapter_TabStudent(getChildFragmentManager(),1));
        tabLayout.setupWithViewPager(pager);

    }
}
