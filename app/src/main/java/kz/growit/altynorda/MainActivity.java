package kz.growit.altynorda;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import java.io.IOException;

import kz.growit.altynorda.Fragments.FavoritesFragment;
import kz.growit.altynorda.Fragments.HomeFragment;
import kz.growit.altynorda.utils.SaveSharedPreferences;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public RelativeLayout cityToolbarRL;
    public TextView cityToolbarTV;
    public FloatingActionButton fab;
    public ImageView filtersIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        cityToolbarRL = (RelativeLayout) findViewById(R.id.cityToolbarRL);
        cityToolbarRL.setVisibility(View.GONE);
        cityToolbarTV = (TextView) findViewById(R.id.cityToolbarTV);

        filtersIV = (ImageView) findViewById(R.id.toolbarItem1IV);
        filtersIV.setVisibility(View.GONE);

        fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        HomeFragment fragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction()
                .addToBackStack(fragment.getClass().getSimpleName())
                .replace(R.id.container, fragment, fragment.getClass().getSimpleName())
                .commit();

        MyTask mt = new MyTask();
        mt.execute();
    }

    class MyTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            InstanceID instanceID = InstanceID.getInstance(getApplicationContext());
            String token = null;
            try {
                token = instanceID.getToken("807020885211", GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
            SaveSharedPreferences.setPrefToken(getApplicationContext(), token);
            //fRg1xjwM5H8:APA91bEjEAtJ1xlod4VO2YPWi15e3inGvlKg-1bwFHvUNOhRiSioXfL3qCO_MkaKqKgeHM2dQ8i_gum3s0yNLi8YMh8h-jVENSpV4GE_xiJ0IkSedtAcVVou3G_2r-6NGnfOoZq_Mqx2
            return token;
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
//            Fragment fragment = getSupportFragmentManager().getFragments().get(getFragmentManager().getBackStackEntryCount() - 1);
//            fragment.onResume();
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment fragment = null;

        if (id == R.id.nav_home) {
            fragment = new HomeFragment();
        } else if (id == R.id.nav_hotels) {

        } else if (id == R.id.nav_agencies) {

        } else if (id == R.id.nav_favorites) {
            fragment = new FavoritesFragment();
        } else if (id == R.id.nav_question) {

        } else if (id == R.id.nav_legal) {

        } else if (id == R.id.nav_info) {

        }
        if (fragment != null)
            getSupportFragmentManager().beginTransaction()
                    .addToBackStack(fragment.getClass().getSimpleName())
                    .replace(R.id.container, fragment, fragment.getClass().getSimpleName())
                    .commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
