package com.example.android3.assignment.Fragment;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.android3.assignment.Adapter.Adapter_ListView_Schedule;
import com.example.android3.assignment.DAO.DAO_Schedule;
import com.example.android3.assignment.Model.Schedule;
import com.example.android3.assignment.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Schedule extends Fragment {
View view;
ListView listView;
Adapter_ListView_Schedule adapterSchedule;
List<Schedule> scheduleList;
DAO_Schedule dao_schedule;

    public Fragment_Schedule() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_schedule, container, false);
        initView();

        return view;
    }
    public void initView(){
        dao_schedule = new DAO_Schedule(getActivity());
        listView = (ListView) view.findViewById(R.id.fSchedule_ListView);
        setListView(dao_schedule.getAllData());

    }

    public void dialogViewByDate(){
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_view_by_date);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        final TextView txtStart = (TextView) dialog.findViewById(R.id.dialog_Schedule_TxtStart);
        final TextView txtEnd = (TextView) dialog.findViewById(R.id.dialog_Schedule_TxtEnd);
        Button btnSearch = (Button)  dialog.findViewById(R.id.dialog_Schedule_BtnSearch);
        Button btnClear = (Button)  dialog.findViewById(R.id.dialog_Schedule_BtnClear);
        Button btnCancel = (Button)  dialog.findViewById(R.id.dialog_Schedule_Btncancel);

        txtEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker(txtEnd);
            }
        });

        txtStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker(txtStart);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtEnd.setText("");
                txtStart.setText("");
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtStart.getText().toString().isEmpty() || txtEnd.getText().toString().isEmpty()){
                    toast("Vui lòng nhập ngày bắt đầu và kết thúc");
                }else {
                    String start = txtStart.getText().toString();
                    String end = txtEnd.getText().toString();
                    setListView(dao_schedule.getDataByDate(start,end));
                    dialog.dismiss();
                }

            }
        });

        dialog.show();
    }

    public void setListView(List<Schedule> scheduleList){
        adapterSchedule = new Adapter_ListView_Schedule(getActivity(),R.layout.raw_schedule,scheduleList);
        listView.setAdapter(adapterSchedule);

    }

    public void datePicker(final TextView txt){
        final Calendar calendar = Calendar.getInstance();
        int day, month, year;
        day = calendar.get(Calendar.DATE);
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);

        DatePickerDialog pickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year, month, dayOfMonth);
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                txt.setText(dateFormat.format(calendar.getTime()));
            }
        }, year, month, day);
        pickerDialog.show();
    }

    public void toast(String s){
        Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_schedule,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_Schedule_All:
                setListView(dao_schedule.getAllData());
                break;
            case R.id.menu_Schedule_Exam:
                setListView(dao_schedule.getExamDay());
                break;
            case R.id.menu_Schedule_Date:
                dialogViewByDate();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
