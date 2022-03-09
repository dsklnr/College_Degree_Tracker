package com.example.degreeplan.All.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.degreeplan.All.Entities.Course;
import com.example.degreeplan.All.Entities.Term;

import java.util.List;

@Dao
public interface TermDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Term term);

    @Update
    void update (Term term);

    @Delete
    void delete (Term term);

    @Query("SELECT * FROM TERM_TABLE ORDER BY termId ASC")
    List<Term> getAllTerms();
}
