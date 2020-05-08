package com.example.android3.assignment.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.android3.assignment.Fragment.Fragment_Schedule;
import com.example.android3.assignment.Fragment.Fragment_Transcript;
import com.example.android3.assignment.Fragment.Fragment_Profile;

public class Adapter_TabStudent extends FragmentStatePagerAdapter {
    public Adapter_TabStudent(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new Fragment_Profile();
            case 1:
                return new Fragment_Schedule();
            case 2:
                return new Fragment_Transcript();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "PROFILE";
            case 1:
                return "SCHEDULE";
            case 2:
                return "TRANSCRIPT";
        }

        return null;
    }
}
