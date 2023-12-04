package com.harsh.attendance.views;

import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import com.harsh.attendance.R;
import com.harsh.attendance.context.AppContext;
import com.harsh.attendance.controllers.AttendanceAdapter;
import com.harsh.attendance.models.Attendance;

public class ViewTotalAttendanceActivity extends AppCompatActivity {

    ArrayList<Attendance> attendanceList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view_layout);

        TextView titleTextView = findViewById(R.id.textView_title);
        titleTextView.setText(R.string.ATTENDANCE_LIST);
        RecyclerView recyclerView = findViewById(R.id.main_recycler_view);
        this.attendanceList = ((AppContext) ViewTotalAttendanceActivity.this
                .getApplicationContext()).getAttendanceList();

        AttendanceAdapter adapter = new AttendanceAdapter(attendanceList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}