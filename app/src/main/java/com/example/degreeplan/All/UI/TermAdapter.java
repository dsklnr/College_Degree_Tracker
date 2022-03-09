package com.example.degreeplan.All.UI;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.degreeplan.All.Entities.Course;
import com.example.degreeplan.All.Entities.Term;
import com.example.degreeplan.R;

import java.util.ArrayList;
import java.util.List;

public class TermAdapter extends RecyclerView.Adapter<TermAdapter.TermHolder> {
    private Context context;
    private List<Term> mTerm;
    private TextView termTitle;
    private final LayoutInflater minflater;
    private ImageButton termDeleteButton;

    public TermAdapter(Context context, ArrayList<Term> termArrayList) {
        minflater = LayoutInflater.from(context);
        this.context = context;
        this.mTerm = termArrayList;
    }

    @NonNull
    @Override
    public TermHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.term_card, parent, false);
        return new TermHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TermAdapter.TermHolder holder, int position) {
        if (mTerm != null) {
            Term term = mTerm.get(position);
            holder.setDetails(term);

            termTitle = holder.itemView.findViewById(R.id.termTitle);
            termTitle.setText(mTerm.get(position).getTermName());

            if (position % 2 == 0) {
                holder.itemView.setBackgroundColor(Color.parseColor("#B92CFF"));
            } else {
                holder.itemView.setBackgroundColor(Color.parseColor("#03BFCC"));
            }
        }

    }

    public void setTerms(List<Term> terms){
        mTerm = terms;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mTerm.size();
    }

    class TermHolder extends RecyclerView.ViewHolder {
        private TextView termTitle, termId, termStartDate, termEndDate, termCourses;

        public TermHolder(@NonNull View itemView) {
            super(itemView);
            termTitle = itemView.findViewById(R.id.termTitle);
            termId = itemView.findViewById(R.id.termId);
            termStartDate = itemView.findViewById(R.id.termStartDate);
            termEndDate = itemView.findViewById(R.id.termEndDate);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    final Term current = mTerm.get(position);
                    Intent intent = new Intent(context, EditTerm.class);
                    intent.putExtra("termId", current.getTermId());
                    intent.putExtra("name", current.getTermName());
                    intent.putExtra("start", current.getTermStartDate());
                    intent.putExtra("end", current.getTermEndDate());
                    context.startActivity(intent);
                }
            });
        }

        void setDetails(Term term){
            termTitle.setText(term.getTermName());
            termId.setText(String.valueOf(term.getTermId()));
            termStartDate.setText(term.getTermStartDate());
            termEndDate.setText(term.getTermEndDate());
        }
    }

    public void termDeleteButton(Term view){

    }

}

