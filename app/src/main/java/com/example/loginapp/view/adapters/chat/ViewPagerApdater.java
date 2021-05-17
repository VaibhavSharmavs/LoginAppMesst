package com.example.loginapp.view.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerApdater  extends FragmentPagerAdapter
{
    private List<Fragment> mFragments;



    public ViewPagerApdater(FragmentManager supportFragmentManager, List<Fragment> mFragments) {
        super(supportFragmentManager);
        this.mFragments = mFragments;
    }

    @Override
    public Fragment getItem (int position)
    {
        return mFragments.get(position);
    }

    @Override
    public int getCount ()
    {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle (int position)
    {
        // TODO: implement your own page title.
        return mFragments.get(position).getClass().getSimpleName();
    }
}
