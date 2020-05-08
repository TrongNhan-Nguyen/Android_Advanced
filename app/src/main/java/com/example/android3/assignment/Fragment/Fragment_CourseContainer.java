package com.example.android3.assignment.Fragment;


import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.android3.assignment.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_CourseContainer extends Fragment {
View view;
BottomNavigationView navigationView;

    public Fragment_CourseContainer() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_coursecontainer, container, false);
        getChildFragmentManager().beginTransaction().replace(R.id.fCourseContainer_FrameLayout, new Fragment_Course()).commit();
        initView();

        return view;
    }
    public void initView(){
        navigationView = (BottomNavigationView) view.findViewById(R.id.fCourseContainer_BottomNavigation);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_course:
                        getChildFragmentManager().beginTransaction().replace(
                                R.id.fCourseContainer_FrameLayout,new Fragment_Course()).commit();
                    break;
                    case R.id.navigation_student:
                        getChildFragmentManager().beginTransaction().replace(
                                R.id.fCourseContainer_FrameLayout,new Fragment_TabStudent()).commit();
                        break;
                }
                return true;
            }
        });

    }
}
