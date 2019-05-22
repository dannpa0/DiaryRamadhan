package com.dev.rahayu.diaryramadhan;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class FragmentAktivitas extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_aktivitas, container, false);

        // inisialisasi toolbar
//        Toolbar toolbar = view.findViewById(R.id.toolbar_fragment_aktivitas);
//        toolbar.setTitle("Aktivitas Ramadhan");

//            ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);


        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // inflate menu aktivitas
        inflater.inflate(R.menu.menu_fragement_aktivitas, menu);
        super.onCreateOptionsMenu(menu, inflater);

    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        switch (item.getItemId()){
//            case R.id.menu_aktivitas_plus:
//
//                Toast.makeText(getActivity(), "Plus Button ditekan", Toast.LENGTH_SHORT).show();
//
//                break;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
