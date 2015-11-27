package kz.growit.altynorda.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import kz.growit.altynorda.R;
import kz.growit.altynorda.singleton.AppController;
import kz.growit.altynorda.utils.SaveSharedPreferences;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailListingFragment extends Fragment {


    public DetailListingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getData();
        return inflater.inflate(R.layout.fragment_detail_listing, container, false);
    }

    private void getData() {
        String url = "http://altynorda.kz/ListingsAPI/GetListing?id=" + SaveSharedPreferences.getPrefListingId(getActivity().getApplicationContext());
        JsonObjectRequest getListing = new JsonObjectRequest(
                Request.Method.GET,
                url,
                new JSONObject(),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        );
        AppController.getInstance().addToRequestQueue(getListing, "get listing");
    }

}
