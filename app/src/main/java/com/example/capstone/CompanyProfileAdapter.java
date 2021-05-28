package com.example.capstone;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CompanyProfileAdapter extends RecyclerView.Adapter<CompanyProfileViewHolder> {
    ArrayList<CompanyProfile> companies;
    followedCompanies main;

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
    }

    @Override
    public int getItemCount() {
        return companies.size();
    }
}
