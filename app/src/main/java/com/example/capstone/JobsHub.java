package com.example.capstone;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
//https://guides.codepath.com/android/fragment-navigation-drawer

public class JobsHub extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActionBarDrawerToggle drawerToggle;

    private DrawerLayout drawer;
    private NavigationView navigationView;

    private boolean employerMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobs_hub);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);


        //replace actionbar with the toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        //In other words, this sets top level destinations for all the fragments. Top level = they can see hamburger icon at all times
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();

//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration); //bind arrows + hamburger to bar
//        NavigationUI.setupWithNavController(navigationView, navController);

//        toggleMode();
//        setupDrawer();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.jobs_hub, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawer.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //    @Override
//    public boolean onSupportNavigateUp() {
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
//                || super.onSupportNavigateUp();
//    }


//    private void setupDrawer() {
//        if (employerMode)
//            navigationView.getMenu().setGroupVisible(R.id.applicantItems, false);
//        else
//            navigationView.getMenu().setGroupVisible(R.id.employerItems, false);
//
//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                drawerItemSelected(item);
//                return true;
//            }
//        });
//    }

//    private void drawerItemSelected(MenuItem item) {
//        Fragment fragment = null;
//        Class fragmentClass;
//
//        switch(item.getItemId()) {
//            case R.id.a_followedCompanies:
//                Toast.makeText(this, "Now opening followed companies page", Toast.LENGTH_SHORT).show();
//            case R.id.a_browse:
//                Toast.makeText(this, "Now opening browse jobs page", Toast.LENGTH_SHORT).show();
//            case R.id.a_modFilters:
//                Toast.makeText(this, "Now opening modify filters page", Toast.LENGTH_SHORT).show();
//            case R.id.a_modResume:
//                Toast.makeText(this, "Now opening modify resume page", Toast.LENGTH_SHORT).show();
//        }
//    }
}