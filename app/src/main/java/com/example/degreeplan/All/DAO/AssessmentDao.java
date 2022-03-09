package com.example.degreeplan.All.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.degreeplan.All.Entities.Assessment;
import com.example.degreeplan.All.Entities.Course;

import java.util.List;

@Dao
public interface AssessmentDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Assessment assessment);

    @Update
    void update (Assessment assessment);

    @Delete
    void delete (Assessment assessment);

    @Query("SELECT * FROM ASSESSMENT_TABLE ORDER BY assessmentId ASC")
    List<Assessment> getAllAssessments();
}
