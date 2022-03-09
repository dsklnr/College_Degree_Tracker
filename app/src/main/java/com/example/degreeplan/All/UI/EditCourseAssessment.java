package com.example.degreeplan.All.UI;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

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

import com.example.degreeplan.All.Database.Repository;
import com.example.degreeplan.All.Entities.Assessment;
import com.example.degreeplan.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class EditCourseAssessment extends AppCompatActivity {
    private EditText course;
    private EditText title;
    private EditText assessmentType;
    private EditText dueDate;
    private int assessmentId;
    private int courseId;
    private String name;
    private String type;
    private String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_course_assessment);

        //display back arrow in action bar
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //edit color of action bar
        ActionBar actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#101010"));
        actionBar.setBackgroundDrawable(colorDrawable);

        assessmentId = getIntent().getIntExtra("assessmentId", -1);

        courseId = getIntent().getIntExtra("courseId", -1);
        course = findViewById(R.id.courseId);
        course.setText(String.valueOf(courseId));

        name = getIntent().getStringExtra("name");
        title = findViewById(R.id.assessmentTitle);
        title.setText(name);

        type = getIntent().getStringExtra("type");
        assessmentType = findViewById(R.id.assessmentType);
        assessmentType.setText(type);

        date = getIntent().getStringExtra("due");
        dueDate = findViewById(R.id.assessmentDueDate);
        dueDate.setText(date);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.delete_notify, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;

            case R.id.notify:
                String dateFromScreen = dueDate.getText().toString();
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
                Intent intent2 = new Intent(EditCourseAssessment.this, MyReceiver.class);
                intent2.putExtra("key", title.getText() + " is due on " + myDate);
                PendingIntent sender = PendingIntent.getBroadcast(EditCourseAssessment.this, 2, intent2, 0);
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sender);
                return true;

            case R.id.delete:
                Repository termRepository = new Repository(getApplication());
                Assessment assessment = new Assessment(assessmentId, courseId, name, type, date);
                termRepository.delete(assessment);

                Intent intent = new Intent(EditCourseAssessment.this, CourseDirectory.class);
                startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }

    public void onSaveUpdateAssessment(View view){
        Repository termRepository = new Repository(getApplication());
        Assessment assessment = new Assessment(assessmentId, Integer.valueOf(course.getText().toString()),
                title.getText().toString(), assessmentType.getText().toString(), dueDate.getText().toString());
        termRepository.update(assessment);

        Intent intent = new Intent(EditCourseAssessment.this, CourseDirectory.class);
        startActivity(intent);
    }
}