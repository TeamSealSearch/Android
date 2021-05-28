package com.example.capstone;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link browseJobs_applicantMain#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class browseJobs_applicantMain extends Fragment implements View.OnClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ImageView profilePic;
    JSONObject userData;
    View main;
    private ArrayList<Posting> postings = new ArrayList<>();
    private LinearLayoutManager manager;
    private RecyclerView recyclerView;
    private PostingAdapter adapter;
    private LinearLayoutManager HorizontalLayout;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment browseJobs_applicantMain.
     */
    // TODO: Rename and change types and number of parameters
    public static browseJobs_applicantMain newInstance(String param1, String param2) {
        browseJobs_applicantMain fragment = new browseJobs_applicantMain();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public browseJobs_applicantMain() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString("userData"); //get the data passed from JobsHub

            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_browse_jobs_applicant_main, container, false);
    }

    @Override //set up views and drawables after onCreateView
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        main = getView();
        profilePic = main.findViewById(R.id.browseProfilePic);

        try { userData = new JSONObject(mParam1); }
        catch (JSONException e) { e.printStackTrace(); }

        try {
            TextView fname = main.findViewById(R.id.browseFirstName); fname.setText(userData.getString("firstName"));
            TextView lname = main.findViewById(R.id.browselastName); lname.setText(userData.getString("lastName"));
            TextView uname = main.findViewById(R.id.browseUsername);
            uname.setText(String.format("(%s)", userData.getString("userName")));
        } catch (Exception e) {e.printStackTrace();}

        setPicture();

        //Set toolbar
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Your Profile");

        setupRecycler();

        setupChips();
    }


    private void setPicture() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.seal1);
        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), bitmap);
        roundedBitmapDrawable.setCircular(true);
        profilePic.setImageDrawable(roundedBitmapDrawable);
    }

    private void setupRecycler() {
        manager = new LinearLayoutManager(getContext());
        recyclerView = main.findViewById(R.id.browseRecycler);

        recyclerView.setLayoutManager(manager);

        fetchAvailableJobs();

        adapter = new PostingAdapter(postings, this);

        HorizontalLayout = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(HorizontalLayout);

        recyclerView.setAdapter(adapter);

    }

    //implement database fetch query
    private void fetchAvailableJobs() {
        postings.add(new Posting("Software Engineer I", "IT, CS Degree, Experience, Social skills", "Google", "Entry Level"));
        postings.add(new Posting("Software Engineer II", "IT, IT Certs, Experience, 90 Hours Availability", "Amazon", "Senior Level"));
        postings.add(new Posting("Software Engineer III", "IT, CS Degree, Experience, Social skills", "Depaul", "Mid Level"));
        postings.add(new Posting("Software Engineer I", "IT, CS Degree, Experience, Social skills", "Uber", "Entry Level"));
        postings.add(new Posting("Software Engineer I", "IT, CS Degree, Experience, Social skills", "Netflix", "Entry Level"));
        postings.add(new Posting("Software Engineer I", "IT, CS Degree, Experience, Social skills", "Grubhub", "Entry Level"));
    }

    private void setupChips() {
        ChipGroup chipGroup = main.findViewById(R.id.chipFilters);
        ArrayList<Chip> chips = new ArrayList<>();

        for (int i=1; i <= 6; i++) {
            Chip filter = new Chip(getContext());
            filter.setText("Test filter: " + i);
            filter.setChipBackgroundColorResource(R.color.applicantPrimary);
            filter.setCloseIconVisible(true);
            filter.setWidth(12);
            filter.setElevation(8);

            chipGroup.addView(filter);
        }
    }


    @Override
    public void onClick(View view) {
        int pos = recyclerView.getChildLayoutPosition(view);
        JSONObject postingData = new JSONObject();
        Posting forFragment = postings.get(pos);

        try {
            postingData.put("jobTitle", forFragment.getJobTitle());
            postingData.put("jobCompany", forFragment.getCompany());
            postingData.put("expLevel", forFragment.getExpLevel());
            postingData.put("jobDescription", forFragment.getJobDescription());

            //prepare data
            Bundle jobPostingBundle = new Bundle();
            jobPostingBundle.putString("jobData", postingData.toString());

            //set up fragment with data
            Fragment postingFrag = new job_posting_fragment();
            postingFrag.setArguments(jobPostingBundle);

            getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
                .replace(R.id.flContent, postingFrag)
                .addToBackStack(null)
                .commit();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}