package com.dev.rahayu.diaryramadhan;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;

public class MainActivity extends AppCompatActivity {


    Fragment fragment;
    GoogleSignInClient mGoogleSignInClient;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;

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

        // ketika memilih button navigation
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                FragmentTransaction transaction;
                switch (menuItem.getItemId()) {

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

    public void checkCurrentUser() {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            // User is sign in
        } else {
            // User not sign in
        }
    }

    public void getUserProfile() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String name = user.getDisplayName();
            String email = user.getEmail();

            boolean emailVerified = user.isEmailVerified();

            String uid = user.getUid();


        }
    }

    public void getProviderData() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            for (UserInfo profile : user.getProviderData()) {

                String providerId = profile.getProviderId();

            }
        }
    }

    public void updateProfile() {

    }

    public void signOut() {
        FirebaseAuth.getInstance().signOut();
        mGoogleSignInClient.signOut().addOnCompleteListener(this,
                new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(MainActivity.this, "Logout", Toast.LENGTH_SHORT).show();
            }
        });
        Intent i = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(i);
        finish();
    }
}
