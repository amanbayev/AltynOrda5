package kz.growit.altynorda.Fragments;

// 807020885211 sender id


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.rey.material.widget.Button;

import kz.growit.altynorda.Adapters.BooleansAdapter;
import kz.growit.altynorda.Adapters.CommentsRVAdapter;
import kz.growit.altynorda.MainActivity;
import kz.growit.altynorda.Models.BooleanOption;
import kz.growit.altynorda.Models.Comments;
import kz.growit.altynorda.Models.Listings;
import kz.growit.altynorda.R;
import kz.growit.altynorda.utils.SaveSharedPreferences;

import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailListingFragment extends Fragment {

    private TextView expanding;
    private TextView expanding2;
    private ArrayList<BooleanOption> booleanOptions = new ArrayList<>();
    private Listings temp;
    private Button sendCommentBTN;
    private EditText commentText;
    private LinearLayout commentsLL;
    private ListView booleans;

    public DetailListingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail_listing, container, false);
        temp = SaveSharedPreferences.getPrefSelectedListingJSON(getActivity().getApplicationContext());

        // implement favorites
//        ImageView fav = (ImageView) rootView.findViewById(R.id.favoriteListingDetailIV);

        SliderLayout slider = (SliderLayout) rootView.findViewById(R.id.sliderListingDetail);
        TextView price = (TextView) rootView.findViewById(R.id.priceDetailFragmentTV);
        TextView address = (TextView) rootView.findViewById(R.id.addressDetailFragmentTV);
        TextView username = (TextView) rootView.findViewById(R.id.usernameDetailFragmentTV);
        TextView description = (TextView) rootView.findViewById(R.id.descriptionDetailFragmentTV);
        expanding = (TextView) rootView.findViewById(R.id.expandingDetailFragmentTV);
        expanding2 = (TextView) rootView.findViewById(R.id.expanding2DetailFragmentTV);
        LinearLayout btn2 = (LinearLayout) rootView.findViewById(R.id.showCommentsDetailFragmentLL);
        commentsLL = (LinearLayout) rootView.findViewById(R.id.commentsDFLL);
        booleans = (ListView) rootView.findViewById(R.id.booleansDetailFragmentLV);

        commentText = (EditText) rootView.findViewById(R.id.commentTextDetailFragmentET);
        sendCommentBTN = (Button) rootView.findViewById(R.id.sendCommentDetailFragmentReyButton);

        price.setText(temp.getPrice());
        address.setText(temp.getAddress());
        description.setText(temp.getDescription());
        username.setText("Телефон: " + temp.getUsername());


        final MainActivity activity = (MainActivity) getActivity();
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

        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.commentsDetailFragmentRV);
        final ArrayList<Comments> comments = temp.getAllComments();

        final CommentsRVAdapter myAdapter = new CommentsRVAdapter(comments);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(myAdapter);

        DisplayMetrics displaymetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int height = displaymetrics.heightPixels;
//        int width = displaymetrics.widthPixels;
        if (comments.size() > 0)
            recyclerView.getLayoutParams().height = height;
        else
            recyclerView.getLayoutParams().height = 40;

        initBooleans(temp);
        BooleansAdapter bAdapter = new BooleansAdapter(booleanOptions);
        Toast.makeText(getActivity().getApplicationContext(), booleanOptions.size() + " is size, and height is " + String.valueOf(56 * booleanOptions.size() + 32), Toast.LENGTH_LONG).show();
        booleans.getLayoutParams().height = (56 * booleanOptions.size()) + 32;
        booleans.setAdapter(bAdapter);
        booleans.setVisibility(View.GONE);
        LinearLayout btn = (LinearLayout) rootView.findViewById(R.id.showLVDetailFragmentLL);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (booleans.getVisibility() != View.GONE) {
                    booleans.setVisibility(View.GONE);
                    expanding.setText("Удобства (развернуть)");
                } else {
                    booleans.setVisibility(View.VISIBLE);
                    expanding.setText("Удобства (свернуть)");
                }
            }
        });

        commentsLL.setVisibility(View.GONE);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (commentsLL.getVisibility() != View.GONE) {
                    commentsLL.setVisibility(View.GONE);
                    expanding2.setText("Комментарии (развернуть)");
                } else {
                    commentsLL.setVisibility(View.VISIBLE);
                    expanding2.setText("Комментарии (свернуть)");
                }
            }
        });


        sendCommentBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!SaveSharedPreferences.getPrefIsLoggedIn(getActivity().getApplicationContext())) {
                    new MaterialDialog.Builder(activity)
                            .title("Необходимо выполнить вход в систему")
                            .content("Необходимо авторизоваться на портале Алтын Орда для комментирования")
                            .positiveText("НЕТ")
                            .negativeText("НЕТ!")
                            .show();
                    String bh = "bh";
                } else {
                    String commentTxt = commentText.getText().toString().trim();
                    if (commentTxt.length() > 0) {
                        String bh = "bh";
                    }
                }
            }
        });

        return rootView;
    }

    private void initBooleans(Listings temp) {
        booleanOptions.clear();

        if (temp.isAc()) {
            BooleanOption tb = new BooleanOption("Кондиционер", true);
            booleanOptions.add(tb);
        } else {
            BooleanOption tb = new BooleanOption("Кондиционер", false);
            booleanOptions.add(tb);
        }

        if (temp.isAtm()) {
            BooleanOption tb = new BooleanOption("Банкомат", true);
            booleanOptions.add(tb);
        } else {
            BooleanOption tb = new BooleanOption("Банкомат", false);
            booleanOptions.add(tb);
        }

        if (temp.isBalcony()) {
            BooleanOption tb = new BooleanOption("Балкон", true);
            booleanOptions.add(tb);
        } else {
            BooleanOption tb = new BooleanOption("Балкон", false);
            booleanOptions.add(tb);
        }

        if (temp.isBarbers()) {
            BooleanOption tb = new BooleanOption("Парикмахерская", true);
            booleanOptions.add(tb);
        } else {
            BooleanOption tb = new BooleanOption("Парикмахерская", false);
            booleanOptions.add(tb);
        }

        if (temp.isCableTV()) {
            BooleanOption tb = new BooleanOption("Кабельное ТВ", true);
            booleanOptions.add(tb);
        } else {
            BooleanOption tb = new BooleanOption("Кабельное ТВ", false);
            booleanOptions.add(tb);
        }

        if (temp.isCoffeePot()) {
            BooleanOption tb = new BooleanOption("Чайник", true);
            booleanOptions.add(tb);
        } else {
            BooleanOption tb = new BooleanOption("Чайник", false);
            booleanOptions.add(tb);
        }

        if (temp.isDishes()) {
            BooleanOption tb = new BooleanOption("Посуда", true);
            booleanOptions.add(tb);
        } else {
            BooleanOption tb = new BooleanOption("Посуда", false);
            booleanOptions.add(tb);
        }

        if (temp.isDocumentProvided()) {
            BooleanOption tb = new BooleanOption("Педоставление документов", true);
            booleanOptions.add(tb);
        } else {
            BooleanOption tb = new BooleanOption("Педоставление документов", false);
            booleanOptions.add(tb);
        }

        if (temp.isHairDryer()) {
            BooleanOption tb = new BooleanOption("Фен", true);
            booleanOptions.add(tb);
        } else {
            BooleanOption tb = new BooleanOption("Фен", false);
            booleanOptions.add(tb);
        }

        if (temp.isHygieneItems()) {
            BooleanOption tb = new BooleanOption("Гигиенические приборы", true);
            booleanOptions.add(tb);
        } else {
            BooleanOption tb = new BooleanOption("Гигиенические приборы", false);
            booleanOptions.add(tb);
        }

        if (temp.isInternet()) {
            BooleanOption tb = new BooleanOption("Интернет", true);
            booleanOptions.add(tb);
        } else {
            BooleanOption tb = new BooleanOption("Интернет", false);
            booleanOptions.add(tb);
        }

        if (temp.isIron()) {
            BooleanOption tb = new BooleanOption("Утюг", true);
            booleanOptions.add(tb);
        } else {
            BooleanOption tb = new BooleanOption("Утюг", false);
            booleanOptions.add(tb);
        }

        if (temp.isMicrowave()) {
            BooleanOption tb = new BooleanOption("Микроволновка", true);
            booleanOptions.add(tb);
        } else {
            BooleanOption tb = new BooleanOption("Микроволновка", false);
            booleanOptions.add(tb);
        }

        if (temp.isParking()) {
            BooleanOption tb = new BooleanOption("Паркинг", true);
            booleanOptions.add(tb);
        } else {
            BooleanOption tb = new BooleanOption("Паркинг", false);
            booleanOptions.add(tb);
        }

        if (temp.isParquet()) {
            BooleanOption tb = new BooleanOption("Паркет", true);
            booleanOptions.add(tb);
        } else {
            BooleanOption tb = new BooleanOption("Паркет", false);
            booleanOptions.add(tb);
        }

        if (temp.isRefrigerator()) {
            BooleanOption tb = new BooleanOption("Холодильник", true);
            booleanOptions.add(tb);
        } else {
            BooleanOption tb = new BooleanOption("Холодильник", false);
            booleanOptions.add(tb);
        }

    }


}
