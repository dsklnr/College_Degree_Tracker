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
import com.example.degreeplan.R;

import java.util.Objects;

public class AddCourseAssessment extends AppCompatActivity {
    private EditText courseId;
    private EditText assessmentId;
    private EditText title;
    private EditText type;
    private EditText date;
    private int course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course_assessment);

        //display back arrow in action bar
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //edit color of action bar
        ActionBar actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#101010"));
        actionBar.setBackgroundDrawable(colorDrawable);

        courseId = (EditText) findViewById(R.id.courseId);
        assessmentId = (EditText) findViewById(R.id.assessmentId);
        title = (EditText) findViewById(R.id.assessmentTitle);
        type = (EditText) findViewById(R.id.assessmentType);
        date = (EditText) findViewById(R.id.assessmentDueDate);
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

    public void onSaveAddCourseAssessment(View view){
        int courseID = Integer.valueOf(String.valueOf(courseId.getText()));
        int assessmentID = Integer.valueOf(String.valueOf(assessmentId.getText()));
        String name = String.valueOf(title.getText());
        String assessmentType = String.valueOf(type.getText());
        String dueDate = String.valueOf(date.getText());

        Repository termRepository = new Repository(getApplication());
        Assessment assessment = new Assessment(assessmentID, courseID, name, assessmentType, dueDate);
        termRepository.insert(assessment);

        Intent intent = new Intent(AddCourseAssessment.this, CourseDirectory.class);
        startActivity(intent);

    }
}