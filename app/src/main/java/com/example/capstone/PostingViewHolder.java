package com.example.capstone;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PostingViewHolder extends RecyclerView.ViewHolder {

    TextView jobTitle;
    TextView company;
    TextView qualifications;

    public PostingViewHolder(View view) {
        super(view);

        jobTitle = view.findViewById(R.id.cardJobTitle);
        company = view.findViewById(R.id.cardCompany);
        qualifications = view.findViewById(R.id.cardQualifications);
    }

}
