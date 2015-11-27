package kz.growit.altynorda.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import kz.growit.altynorda.Fragments.FavoritesFragment;
import kz.growit.altynorda.Fragments.ListListingsFragment;
import kz.growit.altynorda.Fragments.MapListingsFragment;

/**
 * Created by Талгат on 27.11.2015.
 */
public class HomePagerAdapter extends FragmentStatePagerAdapter {

    private int numOfTabs;

    public HomePagerAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ListListingsFragment();
            case 1:
                return new MapListingsFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
