package com.dev.rahayu.diaryramadhan;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dev.rahayu.diaryramadhan.ViewPagerCards.CardFragmentPagerAdapter;
import com.dev.rahayu.diaryramadhan.ViewPagerCards.ShadowTransformer;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;


public class FragmentHome extends Fragment implements View.OnClickListener {

    private String TAG = "FragmentHome";
    GoogleSignInClient mGoogleSignInClient;

    FirebaseAuth mAuth;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ViewPager vp = view.findViewById(R.id.home_view_pager);

        CardFragmentPagerAdapter pagerAdapter =
            new CardFragmentPagerAdapter(getActivity().getSupportFragmentManager(), dpToPixels(2, getContext()));

        ShadowTransformer fragmentCardShadowTransformer = new ShadowTransformer(vp, pagerAdapter);
        fragmentCardShadowTransformer.enableScaling(true);

        vp.setAdapter(pagerAdapter);
        vp.setPageTransformer(false, fragmentCardShadowTransformer);
        vp.setOffscreenPageLimit(3);

//        Button logout = view.findViewById(R.id.btn_home_logout);
//        logout.setOnClickListener(this);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mAuth = FirebaseAuth.getInstance();
        mGoogleSignInClient = GoogleSignIn.getClient(getContext(), gso);



        return view;
    }

    private float dpToPixels(int dp, Context context) {
        return dp * (context.getResources().getDisplayMetrics().density);
    }

    @Override
    public void onClick(View v) {

//        switch (v.getId()){
//            case R.id.btn_home_logout:
//                Toast.makeText(getContext(), "FragmentHomeContext : " + this.getContext(), Toast.LENGTH_SHORT).show();
//                signOut();
//                break;
//        }
    }

    // method untuk sign out
    private void signOut(){
        FirebaseAuth.getInstance().signOut();
        mGoogleSignInClient.signOut().addOnCompleteListener((Activity) this.getContext(),
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getActivity(), "Logout", Toast.LENGTH_SHORT).show();
                    }
                });
        Intent i = new Intent(getActivity(), LoginActivity.class);
        startActivity(i);
        getActivity().finish();
    }
}
