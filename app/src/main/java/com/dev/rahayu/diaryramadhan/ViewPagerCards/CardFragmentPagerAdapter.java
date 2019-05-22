package com.dev.rahayu.diaryramadhan.ViewPagerCards;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.widget.CardView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

// thanks to https://github.com/DevExchanges/ViewPagerCards
public class CardFragmentPagerAdapter extends FragmentStatePagerAdapter implements CardAdapter {

    private List<CardFragment> fragments;
    private float baseElevation;

    public CardFragmentPagerAdapter(FragmentManager fm, float baseElevation){
        super(fm);
        fragments = new ArrayList<>();
        this.baseElevation = baseElevation;

        for (int i = 0; i < 8; i++){
            addCardFragment(new CardFragment());

        }
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        Object fragment = super.instantiateItem(container, position);
        fragments.set(position, (CardFragment) fragment);
        return fragment;
    }

    @Override
    public Fragment getItem(int i) {
        return CardFragment.getInstance(i);
    }

    @Override
    public float getBaseElevation() {
        return baseElevation;
    }

    @Override
    public CardView getCardViewAt(int position) {
        return fragments.get(position).getCardView();
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    public void addCardFragment(CardFragment cardFragment){
        fragments.add(cardFragment);
    }
}
