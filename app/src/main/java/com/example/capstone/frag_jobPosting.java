package com.example.capstone;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link frag_jobPosting#newInstance} factory method to
 * create an instance of this fragment.
 */
public class frag_jobPosting extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View main;
    JSONObject jobData;
    Button applyButton;

    public frag_jobPosting() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment job_posting.
     */
    // TODO: Rename and change types and number of parameters
    public static frag_jobPosting newInstance(String param1, String param2) {
        frag_jobPosting fragment = new frag_jobPosting();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString("jobData");
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_job_posting, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        main = getView();

        try { jobData = new JSONObject(mParam1); }
        catch (JSONException e) { e.printStackTrace(); }
        try {
            TextView jobTitle = main.findViewById(R.id.postingTitle); jobTitle.setText(jobData.getString("jobTitle"));
            TextView jobCompany = main.findViewById(R.id.postingCompany); jobCompany.setText(jobData.getString("jobCompany"));
            TextView jobExpLevel = main.findViewById(R.id.postingLevel); jobExpLevel.setText(jobData.getString("expLevel"));

            TextView jobDescription = main.findViewById(R.id.postingDescription); jobDescription.setText(jobData.getString("jobDescription"));

            applyButton = main.findViewById(R.id.applyButton);
            applyButton.setOnClickListener(view1 -> applyTojob(view1));

        } catch (Exception e) {e.printStackTrace();}

    }

    public void applyTojob(View v) {
        try {
            Toast.makeText(getContext(), "You have successfully applied to " + jobData.getString("jobTitle"), Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}