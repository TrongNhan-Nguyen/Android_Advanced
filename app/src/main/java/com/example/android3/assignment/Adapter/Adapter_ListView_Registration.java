package com.example.android3.assignment.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.android3.assignment.Acitivities.MainActivity;
import com.example.android3.assignment.DAO.DAO_Registration;
import com.example.android3.assignment.DAO.DAO_Schedule;
import com.example.android3.assignment.DAO.DAO_Transcript;
import com.example.android3.assignment.Fragment.Fragment_CourseContainer;
import com.example.android3.assignment.Model.Course;
import com.example.android3.assignment.Model.Schedule;
import com.example.android3.assignment.Model.Transcript;
import com.example.android3.assignment.R;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Adapter_ListView_Registration extends BaseAdapter {
    Context context;
    int layout;
    List<Course> courseList;
    List<Schedule> scheduleList;
    List<Transcript> transcripts;
    DAO_Registration dao_registration;
    DAO_Schedule dao_schedule;
    DAO_Transcript dao_transcript;
    int index = -1;
    public Adapter_ListView_Registration(Context context, int layout, List<Course> courseList) {
        this.context = context;
        this.layout = layout;
        this.courseList = courseList;
    }

    @Override
    public int getCount() {
        return courseList.size();
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layout, null);
        dao_schedule = new DAO_Schedule(context);
        dao_registration = new DAO_Registration(context);
        dao_transcript = new DAO_Transcript(context);
        scheduleList = dao_schedule.getAllData();
        transcripts = dao_transcript.getDataLearning();
        TextView txtID = (TextView) convertView.findViewById(R.id.raw_Registration_ID);
        TextView txtNameCourse = (TextView) convertView.findViewById(R.id.raw_Registration_NameCourse);
        ImageView imgDelete = (ImageView) convertView.findViewById(R.id.raw_Registration_ImgDelete);
        ImageView imgConfirm = (ImageView) convertView.findViewById(R.id.raw_Registration_ImageCheck);
        final Course course = courseList.get(position);



        txtID.setText(course.getId());
        txtNameCourse.setText(course.getNameCourse());
        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                dialog.setTitle("Bạn có muốn huỷ ký môn " + course.getNameCourse() + " không?");
                dialog.setPositiveButton("CÓ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        courseList.remove(course);
                        dao_registration.deleteCourse(course);
                        toast("Đã huỷ đăng ký môn " + course.getNameCourse());
                        notifyDataSetChanged();
                        refresh();
                    }
                });
                dialog.setNegativeButton("KHÔNG ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.show();

            }
        });

        imgConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = position;
                dialogConfirm();
            }
        });
        return convertView;
    }




    public void dialogConfirm(){
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_confirm);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        final Course course = courseList.get(index);



        final TextView txtStart = (TextView) dialog.findViewById(R.id.dialog_confirm_txtStart);
        final TextView txtEnd = (TextView) dialog.findViewById(R.id.dialog_confirm_txtEnd);
        final TextView txtCourseID = (TextView) dialog.findViewById(R.id.dialog_confirm_txtCourseID);
        final TextView txtCourseName = (TextView) dialog.findViewById(R.id.dialog_confirm_txtCourseName);
        final Spinner spinnerDay = (Spinner) dialog.findViewById(R.id.dialog_confirm_spinnerDay);
        final Spinner spinnerBlock = (Spinner) dialog.findViewById(R.id.dialog_confirm_spinnerBlock);
        final Spinner spinnerShift = (Spinner) dialog.findViewById(R.id.dialog_confirm_spinnerShift);
        Button btnValidation = (Button) dialog.findViewById(R.id.dialog_confirm_btnValidation);
        final Button btnConfirm = (Button) dialog.findViewById(R.id.dialog_confirm_btnConfirm);
        Button btnCancel = (Button) dialog.findViewById(R.id.dialog_confirm_btnCancel);

        String[] listDay = {"Even","Odd"};
        final Integer[] listBlock = {1,2};
        Integer[] listShift = {1,2,3,4};

        ArrayAdapter<String> adapterDay = new  ArrayAdapter(context ,android.R.layout.simple_spinner_item, listDay);
        ArrayAdapter<Integer> adapterBlock = new  ArrayAdapter(context ,android.R.layout.simple_spinner_item, listBlock);
        ArrayAdapter<Integer> adapterShift = new  ArrayAdapter(context ,android.R.layout.simple_spinner_item, listShift);

        spinnerDay.setAdapter(adapterDay);
        spinnerBlock.setAdapter(adapterBlock);
        spinnerShift.setAdapter(adapterShift);

        txtCourseID.setText(course.getId());
        txtCourseName.setText(course.getNameCourse());
        if (transcripts.size()>0){
            btnConfirm.setEnabled(false);
            btnValidation.setEnabled(true);
        }else {
            btnConfirm.setEnabled(true);
            btnValidation.setEnabled(false);
        }



        spinnerBlock.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (listBlock[position] == 1){
                    txtStart.setText("2020-02-15");
                    txtEnd.setText("2020-03-15");
                }else {
                    txtStart.setText("2020-03-15");
                    txtEnd.setText("2020-04-15");
                }
            }



            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnValidation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validation(txtStart.getText().toString(),
                            txtEnd.getText().toString(),
                            spinnerDay.getSelectedItem().toString(),
                            Integer.parseInt(spinnerBlock.getSelectedItem().toString()),
                            Integer.parseInt(spinnerShift.getSelectedItem().toString()),
                            btnConfirm);
            }
        });
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String startDay = txtStart.getText().toString();
                String endDay = txtEnd.getText().toString();
                String days = spinnerDay.getSelectedItem().toString();
                String courseID = txtCourseID.getText().toString();
                String courseName = txtCourseName.getText().toString();
                int block = Integer.parseInt(spinnerBlock.getSelectedItem().toString());
                int shift = Integer.parseInt(spinnerShift.getSelectedItem().toString());
                double scores = 0;
                String status = "Learning";
                Schedule add = new Schedule(courseID,courseName,block,shift);
                Transcript transcript = new Transcript(courseID,courseName,scores,status);
                Course delete = new Course(courseID,courseName);
                dao_schedule.insert(startDay,endDay,days,add);
                dao_transcript.insert(transcript);
                dao_registration.deleteCourse(delete);
                courseList.remove(course);
                notifyDataSetChanged();
                refresh();
                toast("Đã thêm vào lịch học, vui vào lịch học để kiểm tra lại");

                dialog.dismiss();
            }
        });


        dialog.show();
    }
    public void validation(String startDay,String endDay, String days ,int block, int shift, Button btn){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localSDate = LocalDate.parse(startDay, formatter);
        LocalDate localEDate = LocalDate.parse(endDay, formatter);
        DayOfWeek dayOfWeek = null;
        int val = 0;
        LocalDate date = localSDate;
        while (!date.isAfter(localEDate)) {
            dayOfWeek = DayOfWeek.from(date);
            val = dayOfWeek.getValue();
            if (days.equalsIgnoreCase("Even")){
                if (val == 1 || val == 3 || val == 5) {
                    for (int i = 0; i<scheduleList.size(); i++){
                        if (scheduleList.get(i).getDate().equalsIgnoreCase(formatter.format(date)) &&
                                scheduleList.get(i).getBlock() == block &&
                                scheduleList.get(i).getShift() == shift){
                            toast("Trùng lịch học vui lòng kiểm tra lại");
                            return;
                        }

                    }

                }
            }if (days.equalsIgnoreCase("Odd")){
                if (val == 2 || val == 4 || val == 6) {
                    for (int i = 0; i<scheduleList.size(); i++){
                        if (scheduleList.get(i).getDate().equalsIgnoreCase(formatter.format(date)) &&
                                scheduleList.get(i).getBlock() == block &&
                                scheduleList.get(i).getShift() == shift){
                            toast("Trùng lịch học vui lòng kiểm tra lại");
                            return;
                        }

                    }

                }
            }
            date = date.plusDays(1);
        }

            toast("Có thể đăng ký!!!");
            btn.setEnabled(true);
    }
    private void refresh() {
        ((MainActivity)context).getSupportFragmentManager()
                .beginTransaction().replace(R.id.frameLayout_Container,new Fragment_CourseContainer()).commit();
    }

    public void toast(String s){
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }
}
