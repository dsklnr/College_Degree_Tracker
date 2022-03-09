package com.example.degreeplan.All.UI;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;

import com.example.degreeplan.All.Database.Repository;
import com.example.degreeplan.All.Entities.Assessment;
import com.example.degreeplan.All.Entities.Course;
import com.example.degreeplan.All.Entities.Term;
import com.example.degreeplan.R;

public class MainActivity extends AppCompatActivity {
    public static int numAlert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //edit color of action bar
        ActionBar actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#101010"));
        actionBar.setBackgroundDrawable(colorDrawable);

        /*
        Repository thingRepository = new Repository(getApplication());
        Thing thing = new Thing(1, "Ben");
        thingRepository.insert(thing);

        Repository courseRepository = new Repository(getApplication());
        Course course = new Course(1, "C131", "10/24/2021", "03/21/2022", "In-Progress", "James Smith", "(801)453-5745", "js@gmail.com");
        courseRepository.insert(course);

         */

        Repository termRepository = new Repository(getApplication());
        Term term = new Term(1, "Term 1", "10/01/2021", "3/31/2022");
        termRepository.insert(term);

        Course course = new Course(1, 1, "C131", "10/24/2021", "03/21/2022", "In Progress",
                "James Smith", "(801)453-5745", "js@gmail.com", "These are my notes.");
        termRepository.insert(course);

        Assessment assessment = new Assessment(1, 1, "Exam", "OA", "12/31/2021");
        termRepository.insert(assessment);

        Assessment assessment2 = new Assessment(2, 2, "Project", "PA", "10/31/2021");
        termRepository.insert(assessment2);

/*
        Repository assessmentRepository = new Repository(getApplication());
        Assessment assessment = new Assessment(1, "C435", "OA",  "05/30/2022");
        assessmentRepository.insert(assessment);

 */
    }

    public void enterCourses(View view) {
        Intent intent = new Intent(MainActivity.this, CourseDirectory.class);
        startActivity(intent);
    }

    public void enterTerms(View view) {
        Intent intent = new Intent(MainActivity.this, TermDirectory.class);
        startActivity(intent);
    }

    public void enterAssessments(View view){
        Intent intent = new Intent(MainActivity.this, AssessmentDirectory.class);
        startActivity(intent);
    }
}