package com.example.degreeplan.All.UI;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;


import com.example.degreeplan.All.Database.Repository;
import com.example.degreeplan.All.Entities.Course;
import com.example.degreeplan.R;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;
import java.util.Objects;

public class CourseDirectory extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CourseAdapter adapter;
    private ArrayList<Course> courseArrayList;
    private ImageButton onViewDetails;
    private Repository repository;
    private ImageButton deleteButton;
    private int courseId;
    private Course currentCourse;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_directory);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        initializeCardView();

        repository = new Repository(getApplication());
        List<Course> allCourses = repository.getAllCourses();
        RecyclerView recyclerView = findViewById(R.id.courseRecyclerView);
        final CourseAdapter courseAdapter = new CourseAdapter(this, courseArrayList);
        recyclerView.setAdapter(courseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        courseAdapter.setCourses(allCourses);


        courseId = getIntent().getIntExtra("id", -1);

        //edit color of action bar
        ActionBar actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#101010"));
        actionBar.setBackgroundDrawable(colorDrawable);


        //CourseDatabaseBuilder db = Room.databaseBuilder(getApplicationContext(), CourseDatabaseBuilder.class, "CourseDatabase").allowMainThreadQueries().build();

        //List<Course> course = db.courseDAO().getAllCourses();
        //db.courseDAO().getAllCourses();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;

            case R.id.addItem:
                startActivity(new Intent(CourseDirectory.this, AddCourse.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initializeCardView() {
        recyclerView = findViewById(R.id.courseRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        courseArrayList = new ArrayList<>();

        adapter= new CourseAdapter(this, courseArrayList);
        recyclerView.setAdapter(adapter);

        //createCourseData();

    }
}