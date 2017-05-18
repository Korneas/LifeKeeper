package com.camilomontoya.lifekeeper;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
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

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth auth;
    private TextView currentUser,currentMail;

    private Toolbar toolbar;

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private int[] tabIcon = {
            R.drawable.ic_menu_theoric,
            R.drawable.ic_menu_gaming,
            R.drawable.ic_menu_emergency,
            R.drawable.ic_menu_profile
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        auth = FirebaseAuth.getInstance();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int pos = tab.getPosition();

                switch (pos){
                    case 0:
                        toolbar.setTitle(getString(R.string.nav_theoric));
                        break;
                    case 1:
                        toolbar.setTitle(getString(R.string.nav_game));
                        break;
                    case 2:
                        toolbar.setTitle(getString(R.string.nav_emergency));
                        break;
                    case 3:
                        toolbar.setTitle(getString(R.string.nav_profile));
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

            //currentUser = (TextView) findViewById(R.id.nav_user);
            //currentMail = (TextView) findViewById(R.id.nav_mail);

            //currentUser.setText(auth.getCurrentUser().getDisplayName());
            //currentMail.setText(auth.getCurrentUser().getEmail());

    }

    private void setupViewPager(ViewPager viewPager){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new HomeFragment(),"Teorico");
        adapter.addFragment(new GameFragment(),"Practico");
        adapter.addFragment(new EmergencyFragment(),"Emergencia");
        adapter.addFragment(new ProfileFragment(),"Perfil");
        viewPager.setAdapter(adapter);
    }

    private void setupTabIcons(){
        for (int i = 0; i < tabIcon.length; i++) {
            tabLayout.getTabAt(i).setIcon(tabIcon[i]);
        }
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager){
            super(manager);
        }

        @Override
        public Fragment getItem(int position){
            return mFragmentList.get(position);
        }

        @Override
        public int getCount(){
            return mFragmentList.size();
        }

        public void addFragment(Fragment frag,String title){
            mFragmentTitleList.add(title);
            mFragmentList.add(frag);
        }

        @Override
        public CharSequence getPageTitle(int position){
            //return mFragmentTitleList.get(position);
            return null;
        }


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

        if (id == R.id.nav_certified) {

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
