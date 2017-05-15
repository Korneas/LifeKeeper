package com.camilomontoya.lifekeeper;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.camilomontoya.lifekeeper.fragment.EmergencyFragment;
import com.camilomontoya.lifekeeper.fragment.GameFragment;
import com.camilomontoya.lifekeeper.fragment.HomeFragment;
import com.camilomontoya.lifekeeper.fragment.ProfileFragment;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth auth;
    private TextView currentUser,currentMail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        auth = FirebaseAuth.getInstance();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        currentUser = (TextView) findViewById(R.id.nav_user);
        currentMail = (TextView) findViewById(R.id.nav_mail);

        //currentUser.setText(auth.getCurrentUser().getDisplayName());
        //currentMail.setText("Damn");
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_theoric) {
            HomeFragment f = new HomeFragment();
            getFragmentManager().beginTransaction().replace(R.id.frame, f).commit();
        } else if (id == R.id.nav_game) {
            GameFragment f = new GameFragment();
            getFragmentManager().beginTransaction().replace(R.id.frame, f).commit();
        } else if (id == R.id.nav_emergency) {
            EmergencyFragment f = new EmergencyFragment();
            getFragmentManager().beginTransaction().replace(R.id.frame, f).commit();
        } else if (id == R.id.nav_profile) {
            ProfileFragment f = new ProfileFragment();
            getFragmentManager().beginTransaction().replace(R.id.frame, f).commit();
        } else if (id == R.id.nav_certified) {

        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_exit) {
            auth.signOut();
            startActivity(new Intent(HomeActivity.this,LogActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
