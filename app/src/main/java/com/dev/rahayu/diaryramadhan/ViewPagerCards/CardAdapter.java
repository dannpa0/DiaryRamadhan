package com.dev.rahayu.diaryramadhan.ViewPagerCards;

import android.support.v7.widget.CardView;

// thanks to https://github.com/DevExchanges/ViewPagerCards
public interface CardAdapter {

    public final int MAX_ELEVATION_FACTOR = 8;

    float getBaseElevation();

    CardView getCardViewAt(int position);

    int getCount();
}