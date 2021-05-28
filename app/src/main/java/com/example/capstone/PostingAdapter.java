package com.example.capstone;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PostingAdapter extends RecyclerView.Adapter<PostingViewHolder> {

    Context context;
    ArrayList<Posting> postings;
    private browseJobs_applicantMain main;
    private long DURATION = 200;
    private boolean on_attach = true;

    public PostingAdapter (ArrayList<Posting> p, browseJobs_applicantMain frag) {
        postings = p;
        main = frag;
    }

    @Override
    public PostingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.recycler_layout_jobposting, parent, false);
        view.setOnClickListener(main);

        return new PostingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostingViewHolder holder, int position) {
        holder.jobTitle.setText(postings.get(position).getJobTitle());
        holder.company.setText(postings.get(position).getCompany());
        holder.qualifications.setText(postings.get(position).getQualifications());

        setAnimation(holder.itemView, position);
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                on_attach = false;
                super.onScrollStateChanged(recyclerView, newState);
            }
        });

        super.onAttachedToRecyclerView(recyclerView);
    }

    private void setAnimation(View itemView, int i) {
        if (!on_attach) {
            i = -1;
        }
        boolean isNotFirstItem = i == -1;
        i++;
        itemView.setAlpha(0.f);
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator animator = ObjectAnimator.ofFloat(itemView, "alpha", 0.f, 0.5f, 1.0f);
        ObjectAnimator.ofFloat(itemView, "alpha", 0.f).start();
        animator.setStartDelay(isNotFirstItem ? DURATION / 2 : (i * DURATION / 3));
        animator.setDuration(500);
        animatorSet.play(animator);
        animator.start();
    }

    @Override
    public int getItemCount() {
        return postings.size();
    }

}
