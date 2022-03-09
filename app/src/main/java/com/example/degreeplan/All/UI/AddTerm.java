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
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.degreeplan.All.Database.Repository;
import com.example.degreeplan.All.Entities.Course;
import com.example.degreeplan.All.Entities.Term;
import com.example.degreeplan.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class AddTerm extends AppCompatActivity {
    private EditText termId;
    private EditText title;
    private EditText start;
    private EditText end;
    private Repository termRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_term);

        //display back arrow in action bar
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        termRepository = new Repository(getApplication());

        //edit color of action bar
        ActionBar actionBar = getSupportActionBar();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#101010"));
        actionBar.setBackgroundDrawable(colorDrawable);

        termId = (EditText) findViewById(R.id.termId);
        title = (EditText) findViewById(R.id.termTitle);
        start = (EditText) findViewById(R.id.termStartDate);
        end = (EditText) findViewById(R.id.termEndDate);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;

                /*
            case R.id.share:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send");
                //optional. Here we are setting the title of the content
                sendIntent.putExtra(Intent.EXTRA_TITLE, "Send Message Title");
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
                return true;

            case R.id.notify:
                String dateFromScreen = start.getText().toString();
                String format = "mm/dd/yyyy";
                SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.US);
                Date myDate= null;
                try{
                    myDate = sdf.parse(dateFromScreen);
                }
                catch (ParseException e){
                    e.printStackTrace();
                }
                Long trigger = myDate.getTime();
                Intent intent = new Intent(AddTerm.this, MyReceiver.class);
                intent.putExtra("key", "message I want to see");
                PendingIntent sender = PendingIntent.getBroadcast(AddTerm.this, ++MainActivity.numAlert, intent, 0);
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sender);

                 */

        }
        return super.onOptionsItemSelected(item);
    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.notify_share_list, menu);
        return true;
    }

     */

    public void onSaveAddTerm(View view){
        int termID = Integer.valueOf(String.valueOf(termId.getText()));
        String termTitle = String.valueOf(title.getText());
        String startDate = String.valueOf(start.getText());
        String endDate = String.valueOf(end.getText());

        Term term = new Term(termID, termTitle, startDate, endDate);
        termRepository.insert(term);

        Intent intent = new Intent(AddTerm.this, TermDirectory.class);
        startActivity(intent);
    }
}