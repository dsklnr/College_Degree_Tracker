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
import com.example.degreeplan.R;

import java.util.ArrayList;
import java.util.List;

public class AssessmentAdapter extends RecyclerView.Adapter<AssessmentAdapter.AssessmentHolder>{
    private Context context;
    private List<Assessment> mAssessments;
    private TextView assessmentTitle;
    private final LayoutInflater minflater;

    public AssessmentAdapter(Context context, ArrayList<Assessment> assessmentArrayList){
            minflater = LayoutInflater.from(context);
            this.context = context;
            this.mAssessments = assessmentArrayList;
            }

    @Override
    public void onBindViewHolder(@NonNull AssessmentAdapter.AssessmentHolder holder, int position) {
        if (mAssessments != null) {
            Assessment assessment = mAssessments.get(position);
            holder.setDetails(assessment);

            assessmentTitle = holder.itemView.findViewById(R.id.assessmentTitle);
            assessmentTitle.setText(mAssessments.get(position).getAssessmentTitle());

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

    public void setAssessments(List<Assessment> assessments){
        mAssessments = assessments;
    }

    @Override
    public int getItemCount() {
        return mAssessments.size();
    }

    @NonNull
    @Override
    public AssessmentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.assessment_card, parent, false);
        return new AssessmentHolder(view);
    }

    class AssessmentHolder extends RecyclerView.ViewHolder {
        private TextView assessmentTitle, assessmentType, assessmentDate;

        AssessmentHolder(View itemView) {
            super(itemView);
            assessmentTitle = itemView.findViewById(R.id.assessmentTitle);
            assessmentType = itemView.findViewById(R.id.assessmentType);
            assessmentDate = itemView.findViewById(R.id.assessmentDueDate);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    final Assessment current = mAssessments.get(position);
                    Intent intent = new Intent(context, EditAssessment.class);
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
