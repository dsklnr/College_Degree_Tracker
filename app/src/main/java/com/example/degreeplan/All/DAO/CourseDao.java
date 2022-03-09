package com.example.degreeplan.All.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.degreeplan.All.Entities.Course;

import java.util.List;

@Dao
public interface CourseDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Course course);

    @Update
    void update (Course course);

    @Delete
    void delete (Course course);

    @Query("SELECT * FROM COURSE_TABLE ORDER BY courseId ASC")
    List<Course> getAllCourses();
}
