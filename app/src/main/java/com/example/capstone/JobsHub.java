package com.example.capstone;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.json.JSONException;
import org.json.JSONObject;
//https://guides.codepath.com/android/fragment-navigation-drawer

public class JobsHub extends AppCompatActivity {

    JSONObject userData;

    private DrawerLayout drawer;
    private Toolbar toolbar;
    private NavigationView navigationView;

    private ActionBarDrawerToggle drawerToggle;
    private boolean employerMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hub_activity_main);

        Intent i = getIntent();
        try { userData = new JSONObject(i.getStringExtra("userData")); }
        catch (Exception e) { e.printStackTrace(); }

        Bundle fragUserData = new Bundle();
        fragUserData.putString("userData", userData.toString());
        Fragment homeFrag = new frag_applicantMain_browseJobs();
        homeFrag.setArguments(fragUserData);

        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(
                        R.anim.slide_in,
                        R.anim.fade_out,
                        R.anim.fade_in,
                        R.anim.slide_out
                )
                .replace(R.id.flContent, homeFrag)
                .addToBackStack(null)
                .commit();

        //replace actionbar with the toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //set up drawer
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        //set up the drawer icon
        drawerToggle = setupDrawerToggle();
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerToggle.syncState();

        drawer.addDrawerListener(drawerToggle); //add listener to sync

        navigationView = (NavigationView) findViewById(R.id.nvView);
        try {
            setupDrawer();
        } catch (Exception e) {e.printStackTrace();}

    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, drawer, R.string.drawer_open, R.string.drawer_close);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.jobs_hub, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item))
            return true;
        else {
            Uri settingsURL = Uri.parse("https://sealsearchjobs.azurewebsites.net/Settings");

            Intent openBrowser = new Intent(Intent.ACTION_VIEW, settingsURL);
            startActivity(openBrowser);
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupDrawer() throws JSONException {
        //only show the relevant menu items for a user
        if (employerMode)
            navigationView.getMenu().setGroupVisible(R.id.applicantItems, false);
        else
            navigationView.getMenu().setGroupVisible(R.id.employerItems, false);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectDrawerItem(item);
                return true;
            }
        });

        //need to define header to modify it's xml
        View header = navigationView.getHeaderView(0);
        TextView headerName = header.findViewById(R.id.header_name);
        headerName.setText(String.format("%s, %s (%s)", userData.getString("firstName"), userData.getString("lastName"), userData.getString("userName")));
    }

    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the fragment to show based on nav item clicked
        // Fragments are for DATA and LOGIC, Activities are for full swaps
        Fragment fragment = null;
        Class fragmentClass;

        Bundle userDataBundle = new Bundle();
        userDataBundle.putString("userData", userData.toString());

        switch(menuItem.getItemId()) {
            case R.id.a_followedCompanies:
                fragmentClass = followedCompanies.class;
                break;
            case R.id.a_modResume:
                fragmentClass = frag_modifyResume.class;
                break;
//            case R.id.a_modFilters:
//                fragmentClass = ThirdFragment.class;
//                break;
            default:
                fragmentClass = frag_applicantMain_browseJobs.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
            fragment.setArguments(userDataBundle); //pass uname, firstname, dob to the fragments
        } catch (Exception e) { e.printStackTrace(); }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .setCustomAnimations(
                        R.anim.slide_in,
                        R.anim.fade_out,
                        R.anim.fade_in,
                        R.anim.slide_out
                )
                .replace(R.id.flContent, fragment)
                .addToBackStack(null)
                .commit();

        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set action bar title
        setTitle(menuItem.getTitle());
        // Close the navigation drawer
        drawer.closeDrawers();
    }
}