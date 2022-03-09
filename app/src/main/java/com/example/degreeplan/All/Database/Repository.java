package com.example.degreeplan.All.Database;

import android.app.Application;

import com.example.degreeplan.All.DAO.AssessmentDao;
import com.example.degreeplan.All.DAO.CourseDao;
import com.example.degreeplan.All.DAO.TermDao;
import com.example.degreeplan.All.Entities.Assessment;
import com.example.degreeplan.All.Entities.Course;
import com.example.degreeplan.All.Entities.Term;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {
    private CourseDao courseDAO;
    private List<Course> allCourses;
    private TermDao termDAO;
    private List<Term>  allTerms;
    private AssessmentDao assessmentDAO;
    private List<Assessment> allAssessments;
    private static int NUMBER_OF_THREADS=4;
    static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public Repository(Application application){
        TermDatabaseBuilder termDB = TermDatabaseBuilder.getDatabase(application);
        termDAO = termDB.termDAO();
        courseDAO = termDB.courseDao();
        assessmentDAO = termDB.assessmentDao();

    }

    public List<Course> getAllCourses(){
        databaseExecutor.execute(()->{
            allCourses = courseDAO.getAllCourses();
        });

        try {

            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        return allCourses;
    }

    public void insert (Course course){
        databaseExecutor.execute(()->{
            courseDAO.insert(course);
        });

        try {

            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public List<Term> getAllTerms() {
        databaseExecutor.execute(() -> {
            allTerms = termDAO.getAllTerms();
        });

        try {

            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        return allTerms;

    }

    public void insert (Term term){
        databaseExecutor.execute(()->{
            termDAO.insert(term);
        });

        try {

            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public List<Assessment> getAllAssessments(){
        databaseExecutor.execute(()->{
            allAssessments = assessmentDAO.getAllAssessments();
        });

        try {

            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        return allAssessments;
    }

    public void insert (Assessment assessment){
        databaseExecutor.execute(()->{
            assessmentDAO.insert(assessment);
        });

        try {

            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void delete(Course currentCourse) {
        databaseExecutor.execute(()->{
            courseDAO.delete(currentCourse);
        });
    }

    public void update(Term term) {
        databaseExecutor.execute(()->{
            termDAO.update(term);
        });
    }

    public void update(Assessment assessment){
        databaseExecutor.execute(()->{
            assessmentDAO.update(assessment);
        });
    }

    public void delete(Term currentTerm) {
        databaseExecutor.execute(()->{
            termDAO.delete(currentTerm);
        });
    }

    public void delete(Assessment currentAssessment){
        databaseExecutor.execute(()->{
            assessmentDAO.delete(currentAssessment);
        });
    }

    public void update(Course course){
        databaseExecutor.execute(()->{
            courseDAO.update(course);
        });
    }
}
