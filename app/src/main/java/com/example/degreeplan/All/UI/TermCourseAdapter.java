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

import com.example.degreeplan.All.Entities.Assessment;
import com.example.degreeplan.All.Entities.Course;
import com.example.degreeplan.All.Entities.Term;
import com.example.degreeplan.R;

import java.util.ArrayList;
import java.util.List;

public class TermCourseAdapter extends RecyclerView.Adapter<TermCourseAdapter.TermCourseHolder>{
    private Context context;
    private List<Course> mCourses;
    private TextView courseTitle;
    private TextView courseStatus;
    private final LayoutInflater minflater;
    private List<Term> terms = new ArrayList<>();
    private List<Course> termCourses;

    public TermCourseAdapter(Context context, ArrayList<Course> courseArrayList){
        minflater = LayoutInflater.from(context);
        this.context = context;
        this.mCourses = courseArrayList;
    }

    @NonNull
    @Override
    public TermCourseAdapter.TermCourseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.term_courses_card, parent, false);
        return new TermCourseAdapter.TermCourseHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TermCourseAdapter.TermCourseHolder holder, int position) {
        if (mCourses != null) {

            for (int i = 0; i < mCourses.size(); i++){
                Course course = mCourses.get(i);
                termCourses = new ArrayList<>();
                if (terms.contains(course.getTermId())){
                    termCourses.add(course);
                }
            }

            courseTitle = holder.itemView.findViewById(R.id.courseTitle);
            courseTitle.setText(mCourses.get(position).getCourseTitle());

            Course course = mCourses.get(position);
            holder.setDetails(course);

            //change card colors
            if (position % 2 == 0) {
                holder.itemView.setBackgroundColor(Color.parseColor("#3B26FF"));
            } else {
                holder.itemView.setBackgroundColor(Color.parseColor("#009A0A"));
            }
        }

        else {
            courseTitle.setText("No Assessment Titles");
        }
    }

    @Override
    public int getItemCount() { return mCourses.size(); }

    public void setCourses(List<Course> allCourses) {
        mCourses = allCourses;
    }

    class TermCourseHolder extends RecyclerView.ViewHolder {
        private TextView courseTitle, courseId, courseStatus, courseStart, courseEnd, courseInstructor;

        TermCourseHolder(View itemView) {
            super(itemView);
            courseTitle = itemView.findViewById(R.id.courseTitle);
            courseId = itemView.findViewById(R.id.courseId);
            courseStatus = itemView.findViewById(R.id.courseStatus);
            courseStart = itemView.findViewById(R.id.startDate);
            courseEnd = itemView.findViewById(R.id.endDate);
            courseInstructor = itemView.findViewById(R.id.courseInstructor);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    final Course current = mCourses.get(position);
                    Intent intent = new Intent(context, EditTermCourse.class);
                    intent.putExtra("courseId", current.getCourseId());
                    intent.putExtra("termId", current.getTermId());
                    intent.putExtra("title", current.getCourseTitle());
                    intent.putExtra("start", current.getCourseStartDate());
                    intent.putExtra("end", current.getCourseEndDate());
                    intent.putExtra("status", current.getCourseStatus());
                    intent.putExtra("instructor", current.getInstructorName());
                    intent.putExtra("phone", current.getInstructorPhoneNumber());
                    intent.putExtra("email", current.getInstructorEmail());
                    intent.putExtra("notes", current.getCourseNotes());
                    context.startActivity(intent);
                }
            });
        }
        void setDetails(Course course){
            courseTitle.setText(course.getCourseTitle());
            courseId.setText(String.valueOf(course.getCourseId()));
            courseStatus.setText(course.getCourseStatus());
            courseStart.setText(course.getCourseStartDate());
            courseEnd.setText(course.getCourseEndDate());
            courseInstructor.setText(course.getInstructorName());
        }
    }
}
