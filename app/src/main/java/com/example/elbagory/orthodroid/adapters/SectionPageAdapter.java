package com.example.elbagory.orthodroid.adapters;

import android.util.Pair;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class SectionPageAdapter extends FragmentStatePagerAdapter {
    private List<Pair<Fragment, String>> fragments = new ArrayList<>();

    public SectionPageAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(Fragment fragment, String title) {

        fragments.add(new Pair<Fragment, String>(fragment, title));


    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragments.get(position).second;
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i).first;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
