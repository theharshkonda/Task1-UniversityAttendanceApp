package com.harsh.attendance.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.harsh.attendance.R;
import com.harsh.attendance.context.AppContext;
import com.harsh.attendance.controllers.DBHandler;
import com.harsh.attendance.models.Faculty;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button loginBtn = findViewById(R.id.user_login_btn);

        DBHandler dbHandler = new DBHandler(this);

        SharedPreferences prefs = getSharedPreferences("my-prefs", MODE_PRIVATE);
        boolean loggedIn = prefs.getBoolean("logged_in", false);
        if (loggedIn) {
            String userRole = prefs.getString("user_role", "");
            if (userRole.equals("ADMIN")) {
                Intent intent = new Intent(MainActivity.this, AdminDashboardActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            } else if (userRole.equals("FACULTY")) {
                int facultyId = prefs.getInt("faculty_id", 0);
                Intent intent = new Intent(MainActivity.this,
                        AddAttendanceSessionActivity.class);
                Faculty faculty = dbHandler.getFacultyById(facultyId);
                ((AppContext) this.getApplicationContext()).setFaculty(faculty);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Login to proceed further.",
                        Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Login to proceed further.",
                    Toast.LENGTH_LONG).show();
        }

        loginBtn.setOnClickListener(v -> {
            Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(loginIntent);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}