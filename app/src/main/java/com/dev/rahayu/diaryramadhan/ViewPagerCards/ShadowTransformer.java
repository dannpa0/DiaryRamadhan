package com.dev.rahayu.diaryramadhan.ViewPagerCards;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.view.View;


// thanks to https://github.com/DevExchanges/ViewPagerCards
public class ShadowTransformer implements ViewPager.OnPageChangeListener ,ViewPager.PageTransformer {

    private ViewPager viewPager;
    private CardAdapter adapter;
    private float lastOffset;
    private boolean scalingEnabled;

    public ShadowTransformer(ViewPager viewPager, CardAdapter adapter){
        this.viewPager = viewPager;
        viewPager.addOnPageChangeListener(this);
        this.adapter = adapter;
    }

    public void enableScaling(boolean enable){
        if(scalingEnabled && ! enable){
            //shrink main card
            CardView currentCard = adapter.getCardViewAt(viewPager.getCurrentItem());
            if(currentCard != null){
                currentCard.animate().scaleX(1);
                currentCard.animate().scaleY(1);
            }
        }else if(!scalingEnabled && enable){
            // grow main card
            CardView currentCard = adapter.getCardViewAt(viewPager.getCurrentItem());
            if(currentCard != null){
                // enlarge current item
                currentCard.animate().scaleY(1.1f);
                currentCard.animate().scaleX(1.1f);
            }
        }

        scalingEnabled = enable;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        int realCurrentPosition;
        int nextPosition;
        float baseElevation = adapter.getBaseElevation();
        float realOffset;
        boolean goingLeft = lastOffset > positionOffset;

        // If we're going backwards, onPageScrolled receives the last position
        // instead of the current one
        if(goingLeft){
            realCurrentPosition = position +1;
            nextPosition = position;
            realOffset = 1 - positionOffset;
        }else{
            nextPosition = position + 1;
            realCurrentPosition = position;
            realOffset = positionOffset;
        }

        // Avoid crash on overscroll
        if(nextPosition > adapter.getCount() -1 || realCurrentPosition > adapter.getCount() -1){
            return ;
        }

        CardView currentCard = adapter.getCardViewAt(realCurrentPosition);

        // This might be null if a fragment is being used
        // and the views weren't created yet
        if (currentCard != null) {
            if (scalingEnabled) {
                currentCard.setScaleX((float) (1 + 0.1 * (1 - realOffset)));
                currentCard.setScaleY((float) (1 + 0.1 * (1 - realOffset)));
            }
            currentCard.setCardElevation((baseElevation + baseElevation
                * (CardAdapter.MAX_ELEVATION_FACTOR - 1) * (1 - realOffset)));
        }

        CardView nextCard = adapter.getCardViewAt(nextPosition);

        // We might be scrolling fast enough so that the next (or previous) card
        // was already destroyed or a fragment might not have been created yet
        if (nextCard != null) {
            if (scalingEnabled) {
                nextCard.setScaleX((float) (1 + 0.1 * (realOffset)));
                nextCard.setScaleY((float) (1 + 0.1 * (realOffset)));
            }
            nextCard.setCardElevation((baseElevation + baseElevation
                * (CardAdapter.MAX_ELEVATION_FACTOR - 1) * (realOffset)));
        }

        lastOffset = positionOffset;


    }

    @Override
    public void onPageSelected(int i) {

    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void transformPage(@NonNull View view, float v) {

    }
}
