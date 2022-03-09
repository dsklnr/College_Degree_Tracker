package com.example.degreeplan.All.UI;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.degreeplan.All.Database.Repository;
import com.example.degreeplan.All.Entities.Assessment;
import com.example.degreeplan.All.Entities.Course;
import com.example.degreeplan.R;

import java.util.Objects;

public class AddTermCourse extends AppCompatActivity {
    private EditText courseId;
    private EditText termId;
    private EditText title;
    private EditText status;
    private EditText start;
    private EditText end;
    private EditText instructor;
    private EditText phone;
    private EditText email;
    private EditText note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_term_course);

        //display back arrow in action bar
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //edit color of action bar
        ActionBar actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#101010"));
        actionBar.setBackgroundDrawable(colorDrawable);

        courseId = (EditText) findViewById(R.id.courseId);
        termId = (EditText) findViewById(R.id.termId);
        title = (EditText) findViewById(R.id.courseTitle);
        start = (EditText) findViewById(R.id.startDate);
        end = (EditText) findViewById(R.id.endDate);
        status = (EditText) findViewById(R.id.addCourseStatus);
        instructor = (EditText) findViewById(R.id.instructorsName);
        phone = (EditText) findViewById(R.id.instructorPhone);
        email = (EditText) findViewById(R.id.instructorEmail);
        note = (EditText) findViewById(R.id.courseNote);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onSaveAddTermCourse(View view){
        Repository termRepository = new Repository(getApplication());
        Course course = new Course(Integer.parseInt(courseId.getText().toString()), Integer.parseInt(termId.getText().toString()),
                title.getText().toString(), start.getText().toString(), end.getText().toString(), status.getText().toString(),
                instructor.getText().toString(), phone.getText().toString(), email.getText().toString(),
                note.getText().toString());
        termRepository.insert(course);

        Intent intent = new Intent(AddTermCourse.this, TermDirectory.class);
        startActivity(intent);

    }
}