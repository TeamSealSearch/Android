package com.example.capstone;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//Animations from https://betterprogramming.pub/android-recyclerview-with-beautiful-animations-5e9b34dbb0fa

public class CompanyProfileAdapter extends RecyclerView.Adapter<CompanyProfileViewHolder> {
    ArrayList<CompanyProfile> companies;
    followedCompanies main;
    long DURATION = 250;
    private boolean on_attach = true;

    public CompanyProfileAdapter(ArrayList<CompanyProfile> c, followedCompanies frag) {
        companies = c;
        main = frag;
    }

    @NonNull
    @Override
    public CompanyProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
            .from(parent.getContext())
            .inflate(R.layout.recycler_layout_companyprofile, parent, false);
        return new CompanyProfileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CompanyProfileViewHolder holder, int position) {
        holder.companyName.setText(companies.get(position).getName());
        holder.industry.setText(companies.get(position).getIndustry());

        setAnimation(holder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return companies.size();
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
        if (!on_attach){
            i = -1;
        }
        boolean not_first_item = i == -1;
        i = i + 1;

        itemView.setTranslationX(itemView.getX() + 1200);
        itemView.setAlpha(0.f);

        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator animatorTranslateY = ObjectAnimator.ofFloat(itemView, "translationX", itemView.getX() + 400, 0);
        ObjectAnimator animatorAlpha = ObjectAnimator.ofFloat(itemView, "alpha", 1.f);
        ObjectAnimator.ofFloat(itemView, "alpha", 0.f).start();
        animatorTranslateY.setStartDelay(not_first_item ? DURATION : (i * DURATION));
        animatorTranslateY.setDuration((not_first_item ? 2 : 1) * DURATION);
        animatorSet.playTogether(animatorTranslateY, animatorAlpha);
        animatorSet.start();
    }
    
}
