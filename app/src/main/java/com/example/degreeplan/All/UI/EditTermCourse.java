package com.example.degreeplan.All.UI;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.degreeplan.All.Database.Repository;
import com.example.degreeplan.All.Entities.Assessment;
import com.example.degreeplan.All.Entities.Course;
import com.example.degreeplan.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EditTermCourse extends AppCompatActivity {
    private EditText title;
    private EditText term;
    private EditText start;
    private EditText end;
    private EditText status;
    private EditText instructor;
    private EditText phone;
    private EditText email;
    private EditText note;
    private TextView assessments;
    private int courseId;
    private int termId;
    private String name;
    private String startDate;
    private String endDate;
    private String courseStatus;
    private String instructorName;
    private String instructorEmail;
    private String instructorPhone;
    private String notes;
    private String courseAssessments;
    private Repository repository;
    private ArrayList<Assessment> courseAssessmentArrayList;
    private ArrayList<Course> termCourseArrayList;
    private RecyclerView recyclerView;
    private CourseAssessmentsAdapter adapter;
    private Course currentCourse;
    private int numAssessments;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_term_course);

        //display back arrow in action bar
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //edit color of action bar
        ActionBar actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#101010"));
        actionBar.setBackgroundDrawable(colorDrawable);

        //input current course details
        //courseId = getIntent().getStringExtra("courseId");

        courseId = getIntent().getIntExtra("courseId", -1);

        termId = getIntent().getIntExtra("termId", -1);
        term = findViewById(R.id.termId);
        term.setText(String.valueOf(termId));

        name = getIntent().getStringExtra("title");
        title = findViewById(R.id.courseTitle);
        title.setText(name);

        courseStatus = getIntent().getStringExtra("status");
        status = findViewById(R.id.courseStatus);
        status.setText(courseStatus);

        startDate = getIntent().getStringExtra("start");
        start = findViewById(R.id.startDate);
        start.setText(startDate);

        endDate = getIntent().getStringExtra("end");
        end = findViewById(R.id.endDate);
        end.setText(endDate);

        instructorName = getIntent().getStringExtra("instructor");
        instructor = findViewById(R.id.instructorName);
        instructor.setText(instructorName);

        instructorEmail = getIntent().getStringExtra("email");
        email = findViewById(R.id.instructorEmail);
        email.setText(instructorEmail);

        instructorPhone = getIntent().getStringExtra("phone");
        phone = findViewById(R.id.instructorPhone);
        phone.setText(instructorPhone);

        notes = getIntent().getStringExtra("notes");
        note = findViewById(R.id.courseNotes);
        note.setText(notes);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    public void onSaveUpdateTermCourse(View view){
        Repository termRepository = new Repository(getApplication());
        Course course = new Course(courseId, Integer.parseInt(term.getText().toString()), title.getText().toString(), start.getText().toString(), end.getText().toString(),
                status.getText().toString(), instructor.getText().toString(), phone.getText().toString(), email.getText().toString(), note.getText().toString());
        termRepository.update(course);


        Intent intent = new Intent(EditTermCourse.this, TermDirectory.class);
        startActivity(intent);
    }
}