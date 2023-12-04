package com.harsh.attendance.views;

import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import com.harsh.attendance.R;
import com.harsh.attendance.controllers.DBHandler;
import com.harsh.attendance.controllers.StudentListAdapter;
import com.harsh.attendance.models.Student;

public class ViewStudentByBranchSemActivity extends AppCompatActivity {

    ArrayList<Student> studentList;
    String branch;
    String semester;
    DBHandler dbHandler = new DBHandler(this);
    private ArrayAdapter<String> listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view_layout);

        branch = getIntent().getExtras().getString("branch");
        semester = getIntent().getExtras().getString("sem");

        TextView titleTextView = findViewById(R.id.textView_title);
        titleTextView.setText(R.string.STUDENT_LIST);
        RecyclerView recyclerView = findViewById(R.id.main_recycler_view);
        this.studentList = dbHandler.getAllStudentByBranchSem(branch, semester);

        StudentListAdapter adapter = new StudentListAdapter(studentList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}