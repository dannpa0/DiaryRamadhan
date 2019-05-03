package com.dev.rahayu.diaryramadhan;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {


    Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigationView;

        navigationView = findViewById(R.id.bottom_navigation);

        fragment = new FragmentHome();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_base, fragment);
        transaction.commit();



        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                FragmentTransaction transaction;
                switch (menuItem.getItemId()){

                    case R.id.menu_main_home:
                        fragment = new FragmentHome();
                        transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_base, fragment);
                        transaction.commit();
                        return true;
                    case R.id.menu_main_list:
                        fragment = new FragmentAktivitas();
                        transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_base, fragment);
                        transaction.commit();
                        return true;
                    case R.id.menu_main_about:
                        fragment = new FragmentAbout();
                        transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_base, fragment);
                        transaction.commit();
                        return true;
                }
                return false;
            }
        });


    }
}
