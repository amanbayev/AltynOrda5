package kz.growit.altynorda.Fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;

import kz.growit.altynorda.Adapters.CommentsRVAdapter;
import kz.growit.altynorda.MainActivity;
import kz.growit.altynorda.Models.Comments;
import kz.growit.altynorda.Models.Listings;
import kz.growit.altynorda.R;
import kz.growit.altynorda.utils.SaveSharedPreferences;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailListingFragment extends Fragment {

    private SliderLayout slider;
    private TextView price, address, description, username;
    private ImageView fav;
    private Listings temp;
    private RecyclerView recyclerView;

    public DetailListingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail_listing, container, false);
        temp = SaveSharedPreferences.getPrefSelectedListingJSON(getActivity().getApplicationContext());

        fav = (ImageView) rootView.findViewById(R.id.favoriteListingDetailIV);
        slider = (SliderLayout) rootView.findViewById(R.id.sliderListingDetail);
        price = (TextView) rootView.findViewById(R.id.priceDetailFragmentTV);
        address = (TextView) rootView.findViewById(R.id.addressDetailFragmentTV);
        username = (TextView) rootView.findViewById(R.id.usernameDetailFragmentTV);
        description = (TextView) rootView.findViewById(R.id.descriptionDetailFragmentTV);

        price.setText(temp.getPrice());
        address.setText(temp.getAddress());
        description.setText(temp.getDescription());
        username.setText("Телефон: " + temp.getUsername());


        MainActivity activity = (MainActivity) getActivity();
        FloatingActionButton fab = activity.fab;
        fab.setVisibility(View.VISIBLE);
        // for changing FAB color, but remember it will change it on every SCREEN!
        //fab.setBackgroundTintList(new ColorStateList(new int[][]{new int[]{0}}, new int[]{Color.parseColor("#336699")}));
        fab.setImageDrawable(activity.getResources().getDrawable(android.R.drawable.ic_menu_call));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uri = "tel:+" + temp.getUsername();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(uri));
                startActivity(intent);
            }
        });

        slider.removeAllSliders();
        for (int i = 0; i < temp.getAllPictures().size(); i++) {
            if (temp.getAllPictures().get(i).getPictureSize() == 1) {
                DefaultSliderView dsv = new DefaultSliderView(getActivity().getApplicationContext());
                dsv.image("http://altynorda.kz" + temp.getAllPictures().get(i).getImageUrl());
                slider.addSlider(dsv);
            }
        }

        recyclerView = (RecyclerView) rootView.findViewById(R.id.commentsDetailFragmentRV);
        ArrayList<Comments> comments = temp.getAllComments();
        if (comments.size() > 0)
            for (int i = 0; i < 15; i++) {
                comments.add(comments.get(0));
            }
        CommentsRVAdapter myAdapter = new CommentsRVAdapter(comments);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(myAdapter);
        recyclerView.getLayoutParams().height = 150 * comments.size();

        return rootView;
    }


}
