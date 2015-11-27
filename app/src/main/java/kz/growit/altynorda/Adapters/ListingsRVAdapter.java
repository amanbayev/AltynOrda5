package kz.growit.altynorda.Adapters;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import kz.growit.altynorda.Fragments.DetailListingFragment;
import kz.growit.altynorda.MainActivity;
import kz.growit.altynorda.Models.Listings;
import kz.growit.altynorda.R;
import kz.growit.altynorda.singleton.AppController;
import kz.growit.altynorda.utils.SaveSharedPreferences;

/**
 * Created by Талгат on 27.11.2015.
 */
public class ListingsRVAdapter extends RecyclerView.Adapter<ListingsRVAdapter.ListingsRVViewHolder> {
    private ArrayList<Listings> listings;
    private Activity activity;
    private Boolean isFavorite = false;
    private String token;

    public ListingsRVAdapter(ArrayList<Listings> listings, Activity activity) {
        this.activity = activity;
        this.listings = listings;
    }

    @Override
    public ListingsRVViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.listing_row_cv, parent, false);

        return new ListingsRVViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ListingsRVViewHolder holder, final int position) {
        final Listings item = listings.get(position);
        holder.username.setText(item.getUsername());
        holder.username.setTextColor(activity.getResources().getColor(R.color.colorAccent));
        holder.address.setText(item.getAddress());
        String areaTXT = item.getTotalArea() + " " + Html.fromHtml("м&#8306;");
        holder.totalArea.setText(areaTXT);
        String kitchenTxt = item.getKitchenArea() + " " + Html.fromHtml("м&#8306;");
        holder.Kitchen.setText(kitchenTxt);
        holder.RoomCount.setText(String.valueOf(item.getRoomCount()));
        holder.Price.setText(item.getPrice());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showListing(item.getId(), item);
            }
        });

        holder.Id = item.getId();
        String floorTXT = item.getFloor() + "/" + item.getTotalFloors();
        holder.floor.setText(floorTXT);
        holder.thumb.removeAllSliders();

        for (int i = 0; i < item.getAllPictures().size(); i++) {
            if (item.getAllPictures().get(i).getPictureSize() == 1) {
                DefaultSliderView dsv = new DefaultSliderView(activity.getApplicationContext());
                dsv.image("http://altynorda.kz" + item.getAllPictures().get(i).getImageUrl());
                dsv.setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                    @Override
                    public void onSliderClick(BaseSliderView slider) {
                        showListing(item.getId(), item);
                    }
                });
                holder.thumb.addSlider(dsv);
            }
        }

//        SharedPreferences loginPrefs = activity.getSharedPreferences("LoginPrefs", 0);
//        token = loginPrefs.getString("Token", "not logged in");

//        holder.favorite.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (isFavorite) {
//                    addToBookmarkRequest(holder.Id, token);
//
//                    holder.favorite.setImageDrawable(v.getResources().getDrawable(R.drawable.ic_favorite_black_24dp));
//                    isFavorite = false;
//                } else {
//
//                    removeFromBookmarkRequest(holder.Id, token);
//
//                    holder.favorite.setImageDrawable(v.getResources().getDrawable(R.drawable.ic_favorite_border_black_24dp));
//                    isFavorite = true;
//                }
//
//            }
//        });
    }

    public void showListing(int id, Listings listings) {
        SaveSharedPreferences.setPrefListingId(activity.getApplicationContext(), id);
        SaveSharedPreferences.setPrefSelectedListingJson(activity.getApplicationContext(), listings);
        Listings temp = SaveSharedPreferences.getPrefSelectedListingJSON(activity.getApplicationContext());
        MainActivity mainActivity = (MainActivity) activity;
        Fragment fragment = new DetailListingFragment();
        mainActivity.getSupportFragmentManager().beginTransaction()
                .addToBackStack(fragment.getClass().getSimpleName())
                .replace(R.id.container, fragment, fragment.getClass().getSimpleName())
                .commit();
    }

    public void addToBookmarkRequest(int listingId, String token) {
        JSONObject data = new JSONObject();

        try {
            data.put("listingId", listingId);
            data.put("token", token);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        String url = "http://altynorda.kz/BookmarksAPI/AddToBookmark";


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                url,
                data,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            if (response.getBoolean("success")) {

                                Toast.makeText(activity.getApplicationContext(), "Успешно добавлено в избранное",
                                        Toast.LENGTH_LONG).show();

                            } else {
                                Toast.makeText(activity.getApplicationContext(), "Ошибка", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        String errors = error.getMessage();
                        Toast.makeText(activity.getApplicationContext(), errors, Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };
        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }

    public void removeFromBookmarkRequest(int listingId, String token) {
        JSONObject data = new JSONObject();

        try {
            data.put("listingId", listingId);
            data.put("token", token);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        String url = "http://altynorda.kz/BookmarksAPI/RemoveFromBookmark";


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                url,
                data,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getBoolean("success")) {

                                Toast.makeText(activity.getApplicationContext(), "Успешно удалено из избранных",
                                        Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(activity.getApplicationContext(), "Ошибка", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

//                        NetworkResponse response = error.networkResponse;

                        String errors = error.getMessage();
                        Toast.makeText(activity.getApplicationContext(), errors, Toast.LENGTH_LONG).show();
//                        progressView.stop();
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };
        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }


    @Override
    public int getItemCount() {
        return listings.size();
    }

    public static class ListingsRVViewHolder extends RecyclerView.ViewHolder {
        private TextView username, address, totalArea, RoomCount, Price, Kitchen, floor;
        private SliderLayout thumb;
        private ImageButton favorite;
        private CardView cardView;
        private Boolean isBookmarked;
        private int Id;

        private LinearLayout LinearSlider;

        public ListingsRVViewHolder(View itemView) {
            super(itemView);

            cardView = (CardView) itemView.findViewById(R.id.card_view_my_listing_card_layout);
            Kitchen = (TextView) itemView.findViewById(R.id.kitchenTextViewListingRow);
            thumb = (SliderLayout) itemView.findViewById(R.id.thumbnailImageViewListingRow);
            username = (TextView) itemView.findViewById(R.id.usernameTVListingRow);
            floor = (TextView) itemView.findViewById(R.id.floorTextViewListingRow);
            address = (TextView) itemView.findViewById(R.id.addressTextViewListingRow);
            totalArea = (TextView) itemView.findViewById(R.id.areaTextViewListingRow);
            RoomCount = (TextView) itemView.findViewById(R.id.roomsTextViewListingRow);
            Price = (TextView) itemView.findViewById(R.id.priceTextViewListingRow);
            favorite = (ImageButton) itemView.findViewById(R.id.favorite);

        }
    }
}
