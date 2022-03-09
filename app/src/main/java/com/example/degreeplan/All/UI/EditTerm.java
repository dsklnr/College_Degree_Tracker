package com.example.degreeplan.All.UI;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.degreeplan.All.Database.Repository;
import com.example.degreeplan.All.Entities.Assessment;
import com.example.degreeplan.All.Entities.Course;
import com.example.degreeplan.All.Entities.Term;
import com.example.degreeplan.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class EditTerm extends AppCompatActivity {
    private EditText title;
    private EditText start;
    private EditText end;
    private ArrayList<Term> termList;
    private String name;
    private int termId;
    private String startDate;
    private String endDate;
    private Repository repository;
    private TextView termName;
    private RecyclerView recyclerView;
    private ArrayList<Course> termCourseArrayList;
    private TermCourseAdapter adapter;
    private Term currentTerm;
    private int numCourses;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_term);

        //display back arrow in action bar
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //edit color of action bar
        ActionBar actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#101010"));
        actionBar.setBackgroundDrawable(colorDrawable);


        //input current course details
        name = getIntent().getStringExtra("name");
        title = findViewById(R.id.termTitle);
        title.setText(name);

        termId = getIntent().getIntExtra("termId", -1);

        startDate = getIntent().getStringExtra("start");
        start = findViewById(R.id.termStartDate);
        start.setText(startDate);

        endDate = getIntent().getStringExtra("end");
        end = findViewById(R.id.termEndDate);
        end.setText(endDate);

        termName = findViewById(R.id.termCourses);
        termName.setText(name);

        initializeCardView();

        repository = new Repository(getApplication());
        List<Course> allCourses = repository.getAllCourses();
        RecyclerView recyclerView = findViewById(R.id.termCoursesRv);
        final TermCourseAdapter termCourseAdapter = new TermCourseAdapter(this, termCourseArrayList);
        recyclerView.setAdapter(termCourseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        termCourseAdapter.setCourses(allCourses);

        List<Course> filterCourse = new ArrayList<>();
        for (Course course : repository.getAllCourses()){
            if (course.getTermId() == termId){
                filterCourse.add(course);
            }
        }

        termCourseAdapter.setCourses(filterCourse);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;

            case R.id.delete:
                for (Term term : repository.getAllTerms()){
                    if (term.getTermId() == getIntent().getIntExtra("termId", -1)){
                        currentTerm = term;
                    }
                }

                numCourses = 0;
                for(Course course : repository.getAllCourses()){
                    if (course.getTermId() == termId){
                        ++numCourses;
                    }
                }

                if (numCourses == 0){
                    repository.delete(currentTerm);
                    Toast.makeText(EditTerm.this, currentTerm.getTermName() + " was deleted", Toast.LENGTH_LONG).show();
                    Intent intents = new Intent(EditTerm.this, TermDirectory.class);
                    startActivity(intents);
                }

                else{
                    Toast.makeText(EditTerm.this, "Can't delete a term with courses", Toast.LENGTH_LONG).show();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.delete, menu);
        return true;
    }

    private void initializeCardView() {
        recyclerView = findViewById(R.id.termCoursesRv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        termCourseArrayList = new ArrayList<>();

        adapter= new TermCourseAdapter(this, termCourseArrayList);
        recyclerView.setAdapter(adapter);
    }

    public void onSaveUpdateTerm(View view){
        Repository termRepository = new Repository(getApplication());
        Term term = new Term(termId, title.getText().toString(), start.getText().toString(), end.getText().toString());
        termRepository.update(term);

        Intent intent = new Intent(EditTerm.this, TermDirectory.class);
        startActivity(intent);
    }

    public void onAddCourse(View view){
        Intent intent = new Intent(EditTerm.this, AddTermCourse.class);
        startActivity(intent);
    }
}