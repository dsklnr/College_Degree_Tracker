package com.example.degreeplan.All.UI;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.degreeplan.All.Database.Repository;
import com.example.degreeplan.All.Entities.Assessment;
import com.example.degreeplan.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AssessmentDirectory extends AppCompatActivity {
    private RecyclerView recyclerView;
    private AssessmentAdapter adapter;
    private ArrayList<Assessment> assessmentArrayList;
    private Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_directory);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //edit color of action bar
        ActionBar actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#101010"));
        actionBar.setBackgroundDrawable(colorDrawable);

        initializeCardView();

        repository = new Repository(getApplication());
        List<Assessment> allAssessments = repository.getAllAssessments();
        RecyclerView recyclerView = findViewById(R.id.assessmentRecyclerView);
        final AssessmentAdapter assessmentAdapter = new AssessmentAdapter(this, assessmentArrayList);
        recyclerView.setAdapter(assessmentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        assessmentAdapter.setAssessments(allAssessments);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;

            case R.id.addItem:
                startActivity(new Intent(AssessmentDirectory.this, AddAssessment.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initializeCardView() {
        recyclerView = findViewById(R.id.assessmentRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        assessmentArrayList = new ArrayList<>();

        adapter= new AssessmentAdapter(this, assessmentArrayList);
        recyclerView.setAdapter(adapter);
    }
}