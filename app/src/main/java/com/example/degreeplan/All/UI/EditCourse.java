package com.example.degreeplan.All.UI;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.degreeplan.All.Database.Repository;
import com.example.degreeplan.All.Entities.Assessment;
import com.example.degreeplan.All.Entities.Course;
import com.example.degreeplan.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class EditCourse extends AppCompatActivity {
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
    private RecyclerView recyclerView;
    private CourseAssessmentsAdapter adapter;
    private Course currentCourse;
    private int numAssessments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_course);

        //display back arrow in action bar
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        initializeCardView();

        repository = new Repository(getApplication());
        List<Assessment> allAssessments = repository.getAllAssessments();
        RecyclerView recyclerView = findViewById(R.id.courseAssessmentsRv);
        final CourseAssessmentsAdapter courseAssessmentsAdapter = new CourseAssessmentsAdapter(this, courseAssessmentArrayList);
        recyclerView.setAdapter(courseAssessmentsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        courseAssessmentsAdapter.setAssessments(allAssessments);

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

        name = getIntent().getStringExtra("name");
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

        courseAssessments = getIntent().getStringExtra("name");
        assessments = findViewById(R.id.courseAssessments);
        assessments.setText(courseAssessments);

        List<Assessment> filterAssessment = new ArrayList<>();
        for (Assessment assessment : repository.getAllAssessments()){
            if (assessment.getCourseId() == courseId){
                filterAssessment.add(assessment);
            }
        }

        courseAssessmentsAdapter.setAssessments(filterAssessment);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;

            case R.id.share:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, note.getText().toString());
                //optional. Here we are setting the title of the content
                sendIntent.putExtra(Intent.EXTRA_TITLE, "Share Course Notes");
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
                return true;

            case R.id.notify:
                String dateFromScreen = start.getText().toString();
                String format = "MM/dd/yyyy";
                SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.US);
                Date myDate= null;
                try{
                    myDate = sdf.parse(dateFromScreen);
                }
                catch (ParseException e){
                    e.printStackTrace();
                }
                Long trigger = myDate.getTime();
                Intent intent = new Intent(EditCourse.this, MyReceiver.class);
                intent.putExtra("key", title.getText() + " starts on " + myDate);
                PendingIntent sender = PendingIntent.getBroadcast(EditCourse.this, 0, intent, 0);
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sender);
                return true;

            case R.id.delete:
                for (Course course : repository.getAllCourses()){
                    if (course.getCourseId() == getIntent().getIntExtra("courseId", -1)){
                        currentCourse = course;
                    }
                }

                numAssessments = 0;
                for(Assessment assessment : repository.getAllAssessments()){
                    if (assessment.getCourseId() == courseId){
                        ++numAssessments;
                    }
                }

                if (numAssessments == 0){
                    repository.delete(currentCourse);
                    Toast.makeText(EditCourse.this, currentCourse.getCourseTitle() + " was deleted", Toast.LENGTH_LONG).show();
                    Intent intents = new Intent(EditCourse.this, CourseDirectory.class);
                    startActivity(intents);
                }

                else{
                    Toast.makeText(EditCourse.this, "Can't delete a course with assessments", Toast.LENGTH_LONG).show();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.notify_share_list, menu);
        return true;
    }

    private void initializeCardView() {
        recyclerView = findViewById(R.id.courseAssessmentsRv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        courseAssessmentArrayList = new ArrayList<>();

        adapter= new CourseAssessmentsAdapter(this, courseAssessmentArrayList);
        recyclerView.setAdapter(adapter);
    }

    public void onSaveUpdateCourse(View view) {
        Repository termRepository = new Repository(getApplication());
        Course course = new Course(courseId, Integer.parseInt(term.getText().toString()), title.getText().toString(), start.getText().toString(), end.getText().toString(),
                status.getText().toString(), instructor.getText().toString(), phone.getText().toString(), email.getText().toString(), note.getText().toString());
        termRepository.update(course);

        Intent intent = new Intent(EditCourse.this, CourseDirectory.class);
        startActivity(intent);
    }

    public void onAddAssessment(View view){
        Intent intent = new Intent(EditCourse.this, AddCourseAssessment.class);
        startActivity(intent);
    }
}