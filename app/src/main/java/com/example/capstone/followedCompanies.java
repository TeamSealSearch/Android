package com.example.capstone;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.ArraySet;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link followedCompanies#newInstance} factory method to
 * create an instance of this fragment.
 */
public class followedCompanies extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View main;
    private JSONObject userData;
    private LinearLayoutManager manager;
    private RecyclerView recyclerView;
    private ArrayList<CompanyProfile> companies = new ArrayList<>();
    private CompanyProfileAdapter adapter;
    private LinearLayoutManager verticalLayout;

    public followedCompanies() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment followedCompanies.
     */
    // TODO: Rename and change types and number of parameters
    public static followedCompanies newInstance (String param1, String param2) {
        followedCompanies fragment = new followedCompanies();
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
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_followed_companies, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        main = getView();

        setupRecycler();
        //parse json data for followed companies
    }

    private void setupRecycler() {
        manager = new LinearLayoutManager(getContext());
        recyclerView = main.findViewById(R.id.followedCompanyRecycler);

        recyclerView.setLayoutManager(manager);

        fetchFollowing();

        adapter = new CompanyProfileAdapter(companies, this);

        verticalLayout = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(verticalLayout);

        recyclerView.setAdapter(adapter);
    }

    private void fetchFollowing() {
        companies.add(new CompanyProfile("Google", "Engineering"));
        companies.add(new CompanyProfile("Google", "IT"));
        companies.add(new CompanyProfile("Google", "Cybersec"));
        companies.add(new CompanyProfile("Google", "Custodian"));
        companies.add(new CompanyProfile("Google", "Customer Service"));
        companies.add(new CompanyProfile("Google", "Engineering"));
    }
}