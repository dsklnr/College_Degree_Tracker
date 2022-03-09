package com.example.degreeplan.All.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.degreeplan.All.DAO.AssessmentDao;
import com.example.degreeplan.All.DAO.CourseDao;
import com.example.degreeplan.All.DAO.TermDao;
import com.example.degreeplan.All.Entities.Assessment;
import com.example.degreeplan.All.Entities.Course;
import com.example.degreeplan.All.Entities.Term;

@Database(entities = {Term.class, Course.class, Assessment.class}, version=5, exportSchema = false)
public abstract class TermDatabaseBuilder extends RoomDatabase {
    public abstract TermDao termDAO();
    public abstract CourseDao courseDao();
    public abstract AssessmentDao assessmentDao();

    private static volatile TermDatabaseBuilder INSTANCE;

    static TermDatabaseBuilder getDatabase(final Context context){
        if (INSTANCE == null){
            synchronized (TermDatabaseBuilder.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), TermDatabaseBuilder.class, "MyTermDatabase")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
