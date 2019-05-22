package com.dev.rahayu.diaryramadhan.ViewPagerCards;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dev.rahayu.diaryramadhan.R;


// thanks to https://github.com/DevExchanges/ViewPagerCards
public class CardFragment extends Fragment {

    private CardView cardView;

    public static Fragment getInstance(int position){
        CardFragment f = new CardFragment();
        Bundle args   = new Bundle();
        args.putInt("position", position);
        f.setArguments(args);

        return f;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.card_home_tips, container, false);

        cardView = view.findViewById(R.id.card_home_tips);
        cardView.setMaxCardElevation(cardView.getCardElevation() * CardAdapter.MAX_ELEVATION_FACTOR);

        TextView title = view.findViewById(R.id.card_home_tips_title);
        TextView desc = view.findViewById(R.id.card_home_tips_desc);

        title.setText(String.format("Card %d", getArguments().getInt("position")));



        return view;


    }

    public CardView getCardView(){
        return cardView;
    }
}
