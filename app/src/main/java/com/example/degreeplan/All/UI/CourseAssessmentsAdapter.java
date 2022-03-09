package com.example.degreeplan.All.UI;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.icu.util.Output;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.degreeplan.All.Entities.Assessment;
import com.example.degreeplan.All.Entities.Course;
import com.example.degreeplan.R;

import java.util.ArrayList;
import java.util.List;

public class CourseAssessmentsAdapter extends RecyclerView.Adapter
        <CourseAssessmentsAdapter.CourseAssessmentHolder> {
    private Context context;
    private List<Assessment> mAssessments;
    private TextView assessmentTitle;
    private TextView assessmentType;
    private final LayoutInflater minflater;
    private List<Course> courses = new ArrayList<>();
    private List<Assessment> courseAssessments;

    public CourseAssessmentsAdapter(Context context, ArrayList<Assessment> assessmentArrayList){
        minflater = LayoutInflater.from(context);
        this.context = context;
        this.mAssessments = assessmentArrayList;
    }

    @NonNull
    @Override
    public CourseAssessmentsAdapter.CourseAssessmentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.assessment_card, parent, false);
        return new CourseAssessmentsAdapter.CourseAssessmentHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseAssessmentsAdapter.CourseAssessmentHolder holder, int position) {
        if (mAssessments != null) {

            for (int i = 0; i < mAssessments.size(); i++){
                Assessment assessment = mAssessments.get(i);
                courseAssessments = new ArrayList<>();
                if (courses.contains(assessment.getCourseId())){
                    courseAssessments.add(assessment);
                }
            }

            assessmentTitle = holder.itemView.findViewById(R.id.assessmentTitle);
            assessmentTitle.setText(mAssessments.get(position).getAssessmentTitle());

            Assessment assessment = mAssessments.get(position);
            holder.setDetails(assessment);

            //change card colors
            if (position % 2 == 0) {
                holder.itemView.setBackgroundColor(Color.parseColor("#3B26FF"));
            } else {
                holder.itemView.setBackgroundColor(Color.parseColor("#009A0A"));
            }
        }

        else {
            assessmentTitle.setText("No Assessment Titles");
        }
    }

    @Override
    public int getItemCount() {
        return mAssessments.size();
    }

    public void setAssessments(List<Assessment> allAssessments) {
        mAssessments = allAssessments;
    }

    class CourseAssessmentHolder extends RecyclerView.ViewHolder {
        private TextView assessmentTitle, assessmentType, assessmentDate;

        CourseAssessmentHolder(View itemView) {
            super(itemView);
            assessmentTitle = itemView.findViewById(R.id.assessmentTitle);
            assessmentType = itemView.findViewById(R.id.assessmentType);
            assessmentDate = itemView.findViewById(R.id.assessmentDueDate);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    final Assessment current = mAssessments.get(position);
                    Intent intent = new Intent(context, EditCourseAssessment.class);
                    intent.putExtra("assessmentId", current.getAssessmentId());
                    intent.putExtra("courseId", current.getCourseId());
                    intent.putExtra("name", current.getAssessmentTitle());
                    intent.putExtra("type", current.getAssessmentType());
                    intent.putExtra("due", current.getAssessmentDueDate());
                    context.startActivity(intent);
                }
            });
        }
        void setDetails(Assessment assessment){
            assessmentTitle.setText(assessment.getAssessmentTitle());
            assessmentType.setText(assessment.getAssessmentType());
            assessmentDate.setText(assessment.getAssessmentDueDate());
        }
    }
}
