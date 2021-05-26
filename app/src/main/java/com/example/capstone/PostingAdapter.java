package com.example.capstone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PostingAdapter extends RecyclerView.Adapter<PostingViewHolder> {

    Context context;
    ArrayList<Posting> postings;
    private browseJobs_applicantMain main;

    public PostingAdapter (ArrayList<Posting> p, browseJobs_applicantMain frag) {
        postings = p;
        main = frag;
    }

    @Override
    public PostingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.browse_posting_card, parent, false);
        view.setOnClickListener(main);

        return new PostingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostingViewHolder holder, int position) {
        holder.jobTitle.setText(postings.get(position).getJobTitle());
        holder.company.setText(postings.get(position).getCompany());
        holder.qualifications.setText(postings.get(position).getQualifications());
    }

    @Override
    public int getItemCount() {
        return postings.size();
    }

}
