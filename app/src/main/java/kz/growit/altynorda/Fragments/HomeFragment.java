package kz.growit.altynorda.Fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import kz.growit.altynorda.Adapters.HomePagerAdapter;
import kz.growit.altynorda.MainActivity;
import kz.growit.altynorda.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private MainActivity activity;
    private RelativeLayout cityRL;
    private ImageView filtersIV;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        activity = (MainActivity) getActivity();

        cityRL = activity.cityToolbarRL;
        TextView cityTV = activity.cityToolbarTV;

        cityRL.setVisibility(View.VISIBLE);
        cityTV.setText("Астана");

        filtersIV = (ImageView) activity.filtersIV;
        filtersIV.setVisibility(View.VISIBLE);

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        TabLayout tabLayout = (TabLayout) rootView.findViewById(R.id.homeTabLayout);

        tabLayout.addTab(tabLayout.newTab().setText("СПИСОК"));
        tabLayout.addTab(tabLayout.newTab().setText("Карта"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) rootView.findViewById(R.id.homeVP);

        final HomePagerAdapter adapter = new HomePagerAdapter
                (activity.getSupportFragmentManager(), tabLayout.getTabCount());

        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return rootView;
    }


    @Override
    public void onPause() {
        super.onPause();
        cityRL.setVisibility(View.GONE);
        filtersIV.setVisibility(View.GONE);
    }
}
