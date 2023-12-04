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
import com.harsh.attendance.controllers.StudentAttendanceAdapter;
import com.harsh.attendance.models.Student;

public class AddAttendanceActivity extends AppCompatActivity {

    ArrayList<Student> studentBeanList;
    int sessionId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view_layout);

        sessionId = getIntent().getExtras().getInt("sessionId");

        TextView titleTextView = findViewById(R.id.textView_title);
        titleTextView.setText(R.string.ADD_ATTENDANCE);
        RecyclerView recyclerView = findViewById(R.id.main_recycler_view);
        studentBeanList = ((AppContext) AddAttendanceActivity.this
                .getApplicationContext()).getStudentList();

        StudentAttendanceAdapter adapter = new StudentAttendanceAdapter(studentBeanList, sessionId);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}