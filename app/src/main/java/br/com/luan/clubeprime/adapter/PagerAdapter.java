package br.com.luan.clubeprime.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import br.com.luan.clubeprime.fragments.Fragment1;
import br.com.luan.clubeprime.fragments.Fragment2;
import br.com.luan.clubeprime.fragments.Fragment3;


/**
 * Created by Dev_Maker on 25/10/2016.
 */
public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                Fragment1 tab1 = new Fragment1();
                return tab1;
            case 1:
                Fragment2 tab2 = new Fragment2();
                return tab2;
            case 2:
                Fragment3 tab3 = new Fragment3();
                return tab3;
            default:
                Fragment1 tab = new Fragment1();
                return tab;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
