package com.example.capstone;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CompanyProfileViewHolder extends RecyclerView.ViewHolder {
    TextView companyName;
    TextView industry;
    ImageView logo;

    public CompanyProfileViewHolder(@NonNull View itemView) {
        super(itemView);

        companyName = itemView.findViewById(R.id.following_companyName);
        industry = itemView.findViewById(R.id.following_companyIndustry);
    }
}
