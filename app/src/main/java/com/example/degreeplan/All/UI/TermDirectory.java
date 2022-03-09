package com.example.degreeplan.All.UI;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.text.Transliterator;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageButton;

import com.example.degreeplan.All.Database.Repository;
import com.example.degreeplan.All.Entities.Course;
import com.example.degreeplan.All.Entities.Term;
import com.example.degreeplan.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TermDirectory extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TermAdapter adapter;
    private ArrayList<Term> termArrayList;
    private ImageButton onViewDetails;
    private Repository repository;
    private ImageButton deleteButton;
    private int termId;
    private Term currentTerm;
    private List<Term> mTerm;
    private Context context;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_directory);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        initializeCardView();

        repository = new Repository(getApplication());
        List<Term> allTerms = repository.getAllTerms();
        RecyclerView recyclerView = findViewById(R.id.termRecyclerView);
        final TermAdapter termAdapter = new TermAdapter(this, termArrayList);
        recyclerView.setAdapter(termAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        termAdapter.setTerms(allTerms);

        termId = getIntent().getIntExtra("id", -1);

        //edit color of action bar
        ActionBar actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#101010"));
        actionBar.setBackgroundDrawable(colorDrawable);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_menu, menu);
        return true;
    }

    private void initializeCardView() {
        recyclerView = findViewById(R.id.termRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        termArrayList = new ArrayList<>();

        adapter= new TermAdapter(this, termArrayList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;

            case R.id.addItem:
                startActivity(new Intent(TermDirectory.this, AddTerm.class));
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    /*
    public void onEditTerm(View view){
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = getAdapterPosition();
                final Term current = mTerm.get(position);
                Intent intent = new Intent(context, AddTerm.class);
                intent.putExtra("ID", current.getTermId());
                intent.putExtra("name", current.getTermName());
                intent.putExtra("start", current.getTermStartDate());
                intent.putExtra("end", current.getTermEndDate());
                context.startActivity(intent);
            }
        });
    }

     */
}