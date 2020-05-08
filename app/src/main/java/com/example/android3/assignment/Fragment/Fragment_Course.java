package com.example.android3.assignment.Fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.android3.assignment.Adapter.Adapter_ListView_Course;
import com.example.android3.assignment.Adapter.Adapter_ListView_Registration;
import com.example.android3.assignment.Adapter.Adapter_Semesters;
import com.example.android3.assignment.DAO.DAO_Registration;
import com.example.android3.assignment.DAO.DAO_Course;
import com.example.android3.assignment.DAO.DAO_Schedule;
import com.example.android3.assignment.DAO.DAO_Semesters;
import com.example.android3.assignment.DAO.DAO_Transcript;
import com.example.android3.assignment.Model.Course;
import com.example.android3.assignment.Model.Semesters;
import com.example.android3.assignment.Model.Transcript;
import com.example.android3.assignment.R;

import java.util.List;

public class Fragment_Course extends Fragment {
    View view;
    Spinner spinner;
    List<Semesters> semestersList;
    List<Course> listRegistration, listCourse;
    List<Transcript> transcriptList;
    DAO_Semesters dao_semesters;
    DAO_Course dao_Course;
    DAO_Schedule dao_schedule;
    DAO_Registration dao_registration;
    DAO_Transcript dao_transcript;
    List<String> listName;
    Adapter_Semesters adapter_semesters;
    Adapter_ListView_Course adapter_lvSC;
    Adapter_ListView_Registration adapter_lvCR;
    ListView listViewCS, listViewCR;
    TextView txtTotal;
    AutoCompleteTextView aCTVSearch;
    ImageView imgClear;
    int index = -1;
    int max;



    public Fragment_Course() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_course, container, false);
        initView();
        return view;
    }

    public void initView() {
        dao_registration = new DAO_Registration(getActivity());
        dao_semesters = new DAO_Semesters(getActivity());
        dao_Course = new DAO_Course(getActivity());
        dao_schedule = new DAO_Schedule(getActivity());
        dao_transcript = new DAO_Transcript(getActivity());
        transcriptList = dao_transcript.getAllData();
        listName = dao_Course.getNames();
        max = 6 - dao_transcript.getDataLearning().size();
//        insertSemesters();
//        insertCourse();

        spinner = (Spinner) view.findViewById(R.id.fCourse_SpinnerSemesters);
        listViewCS = (ListView) view.findViewById(R.id.fCourse_ListViewCS);
        listViewCR = (ListView) view.findViewById(R.id.fCourse_ListViewCR);
        txtTotal = (TextView) view.findViewById(R.id.fCourse_txtTotalregistraton);
        aCTVSearch = (AutoCompleteTextView) view.findViewById(R.id.fCourse_AC_Search);
        imgClear = (ImageView) view.findViewById(R.id.fCourse_ImgClear);



        ArrayAdapter<String> adapterName = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, listName);
        setListViewCR(dao_registration.getData());
        aCTVSearch.setThreshold(1);
        aCTVSearch.setAdapter(adapterName);
        aCTVSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = aCTVSearch.getText().toString();
                setListViewCS(dao_Course.getDataByName(name));
            }
        });
        semestersList = dao_semesters.getData();
        adapter_semesters = new Adapter_Semesters(getActivity(), R.layout.raw_semester, semestersList);
        spinner.setAdapter(adapter_semesters);

        imgClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aCTVSearch.setText("");
            }
        });


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                index = position;
                String semester = semestersList.get(position).getName();
                setListViewCS(dao_Course.getDataBySemester(semester));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        listViewCS.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                if (listRegistration.size() >= max) {
                    toast("Mỗi kỳ chỉ được đăng ký tối đa là  6 môn, Vui lòng kiểm tra lại lịch học");
                    return true;
                } else {
                    for (int i = 0; i < transcriptList.size(); i++) {

                        if (transcriptList.get(i).getCourseID().equalsIgnoreCase(listCourse.get(position).getId())) {
                            toast("Môn này bạn đã passed, hoặc đang học, vui lòng kiểm tra lại");
                            return true;
                        }
                    }
                }

                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                dialog.setTitle("Bạn có muốn đăng ký môn " + listCourse.get(position).getNameCourse() + " không?");
                dialog.setPositiveButton("ĐĂNG KÝ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = listCourse.get(position).getNameCourse();
                        String id = listCourse.get(position).getId();
                        Course add = new Course(id, name);
                        if (dao_registration.insert(add) > 0) {
                            refreshLV();
                            setTotal();
                            Toast.makeText(getActivity(), "ĐĂNG KÝ THÀNH CÔNG", Toast.LENGTH_SHORT).show();
                        } else {

                            toast("LỚP NÀY ĐÃ ĐƯỢC ĐĂNG KÝ, VUI LÒNG CHỌN LỚP KHÁC");

                        }
                    }
                });
                dialog.setNegativeButton("HUỶ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.show();

                return true;
            }
        });

    }

    public void insertSemesters() {
//        dao_semesters.insert(new Semesters("SPRING 2020"));
//        dao_semesters.insert(new Semesters("FALL 2019"));
//        dao_semesters.insert(new Semesters("SUMMER 2019"));
//        dao_semesters.insert(new Semesters("SPRING 2019"));
    }

    public void insertCourse() {
//        dao_Course.insert(new Course("SKI1013","Kỹ năng học tập","SPRING 2019"));
//        dao_Course.insert(new Course("COM1024","Tin học văn phòng","SPRING 2019"));
//        dao_Course.insert(new Course("COM1012","Tin học cơ sở","SPRING 2019"));
//        dao_Course.insert(new Course("MUL1013","Photoshop CS6","SPRING 2019"));
//
//        dao_Course.insert(new Course("COM2012","Cơ sở dữ liệu","SUMMER 2019"));
//        dao_Course.insert(new Course("WEB1013","Xây dựng trang Web","SUMMER 2019"));
//        dao_Course.insert(new Course("MOB1013","Lập trình Java 1","SUMMER 2019"));
//        dao_Course.insert(new Course("WEB1042","Javascript cơ bản","SUMMER 2019"));
//
//        dao_Course.insert(new Course("MOB1022","Lập trình Java 2","FALL 2019"));
//        dao_Course.insert(new Course("MOB1032","Lập trình Android","FALL 2019"));
//        dao_Course.insert(new Course("WEB3022","HTML5&CSS3","FALL 2019"));
//        dao_Course.insert(new Course("MOB202","Giao diện trên Android","FALL 2019"));
//
//        dao_Course.insert(new Course("MOB201","Android nâng cao","SPRING 2020"));
//        dao_Course.insert(new Course("MOB204","Dự án mẫu","SPRING 2020"));
//        dao_Course.insert(new Course("PRO1121","Dự án 1","SPRING 2020"));
    }

    public void setListViewCR(List<Course> courses) {
        listRegistration = courses;
        adapter_lvCR = new Adapter_ListView_Registration(getActivity(), R.layout.raw_registration, listRegistration);
        listViewCR.setAdapter(adapter_lvCR);
        setTotal();
    }

    public void setListViewCS(List<Course> courseList) {
        listCourse = courseList;
        adapter_lvSC = new Adapter_ListView_Course(getActivity(), R.layout.raw_course, listCourse);
        listViewCS.setAdapter(adapter_lvSC);

    }

    public void refreshLV() {
        listRegistration.clear();
        listRegistration.addAll(dao_registration.getData());
        adapter_lvCR.notifyDataSetChanged();
        listViewCR.invalidateViews();
        listViewCR.refreshDrawableState();
    }

    public void setTotal() {
        txtTotal.setText(String.valueOf(listRegistration.size()));
    }

    public void toast(String s) {
        Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
    }

}
