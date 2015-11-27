package kz.growit.altynorda.Fragments;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kz.growit.altynorda.Adapters.ListingsRVAdapter;
import kz.growit.altynorda.MainActivity;
import kz.growit.altynorda.Models.Listings;
import kz.growit.altynorda.R;
import kz.growit.altynorda.singleton.AppController;
import kz.growit.altynorda.utils.SaveSharedPreferences;

import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListListingsFragment extends Fragment {
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private ArrayList<Listings> listings = new ArrayList<>();
    private ListingsRVAdapter myAdapter;

    public ListListingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_list_listings, container, false);

        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeLayoutListLFSRL);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.listListingsFragmentRV);

        getData();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                getData();
            }
        });

        myAdapter = new ListingsRVAdapter(listings, getActivity());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(myAdapter);

        return rootView;
    }

    private void getData() {
        listings.clear();
        String url = "http://altynorda.kz/ListingsAPI/GetCityListings?cityId=" + SaveSharedPreferences.getPrefCityId(getActivity().getApplicationContext());
        JsonArrayRequest getListings = new JsonArrayRequest(
                Request.Method.GET,
                url,
                new JSONObject(),
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                swipeRefreshLayout.setRefreshing(false);
                                Listings temp = new Listings(response.getJSONObject(i));
                                listings.add(temp);
                                myAdapter.notifyDataSetChanged();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        );
        AppController.getInstance().addToRequestQueue(getListings, "get listings");
    }

}
