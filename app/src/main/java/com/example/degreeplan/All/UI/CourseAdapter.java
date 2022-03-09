package com.example.degreeplan.All.UI;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.degreeplan.All.Entities.Course;
import com.example.degreeplan.R;

import java.util.ArrayList;
import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseHolder> {
    private Context context;
    private List<Course> mCourses;
    private TextView courseTitle;
    private final LayoutInflater minflater;

    public CourseAdapter(Context context, ArrayList<Course> courseArrayList){
        minflater = LayoutInflater.from(context);
        this.context = context;
        this.mCourses = courseArrayList;
    }

    @NonNull
    @Override
    public CourseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.course__card, parent, false);
        return new CourseHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseHolder holder, int position) {
        if (mCourses != null) {
            Course course = mCourses.get(position);
            holder.setDetails(course);

            courseTitle = holder.itemView.findViewById(R.id.courseTitle);
            courseTitle.setText(mCourses.get(position).getCourseTitle());
            //String coursetitles = courses.get(position).getCourseTitle();
            //courseTitle.setText(coursetitles);

            //holder.itemView.findViewById(R.id.courseTitle) = courses.get(position);


            //change card colors
            if (position % 2 == 0) {
                holder.itemView.setBackgroundColor(Color.parseColor("#3B26FF"));
            } else {
                holder.itemView.setBackgroundColor(Color.parseColor("#009A0A"));
            }
        }

        else {
            courseTitle.setText("No Course Titles");
        }


    }

    public void setCourses(List<Course> courses){
        mCourses = courses;
    }

    @Override
    public int getItemCount() {
        return mCourses.size();
    }


    class CourseHolder extends RecyclerView.ViewHolder{
        private TextView courseTitle, courseId, courseStatus, courseStartDate, courseEndDate, courseInstructor, instructorPhone, instructorEmail;

        CourseHolder(View itemView){
            super(itemView);
            courseTitle = itemView.findViewById(R.id.courseTitle);
            courseId = itemView.findViewById(R.id.courseId1);
            courseStatus = itemView.findViewById(R.id.courseStatus);
            courseStartDate = itemView.findViewById(R.id.startDate);
            courseEndDate = itemView.findViewById(R.id.endDate);
            courseInstructor = itemView.findViewById(R.id.courseInstructor);
            instructorPhone = itemView.findViewById(R.id.instructorPhone);
            instructorEmail = itemView.findViewById(R.id.instructorEmail);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    final Course current = mCourses.get(position);
                    Intent intent = new Intent(context, EditCourse.class);
                    intent.putExtra("courseId", current.getCourseId());
                    intent.putExtra("termId", current.getTermId());
                    intent.putExtra("name", current.getCourseTitle());
                    intent.putExtra("status", current.getCourseStatus());
                    intent.putExtra("start", current.getCourseStartDate());
                    intent.putExtra("end", current.getCourseEndDate());
                    intent.putExtra("instructor", current.getInstructorName());
                    intent.putExtra("email", current.getInstructorEmail());
                    intent.putExtra("phone", current.getInstructorPhoneNumber());
                    intent.putExtra("notes", current.getCourseNotes());
                    context.startActivity(intent);
                }
            });

        }

        void setDetails(Course course){
            courseTitle.setText(course.getCourseTitle());
            courseId.setText(String.valueOf(course.getCourseId()));
            courseStatus.setText(course.getCourseStatus());
            courseStartDate.setText(course.getCourseStartDate());
            courseEndDate.setText(course.getCourseEndDate());
            courseInstructor.setText(course.getInstructorName());
            instructorPhone.setText(course.getInstructorPhoneNumber());
            instructorEmail.setText(course.getInstructorEmail());
        }
    }
}
