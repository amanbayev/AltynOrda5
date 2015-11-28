package kz.growit.altynorda.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

import org.json.JSONObject;

import kz.growit.altynorda.Models.Listings;

/**
 * Created by Талгат on 27.11.2015.
 */
public class SaveSharedPreferences {
    static final String PREF_IS_LOGGED_IN = "isLoggedIn";

    static final String PREF_LASTUSERNAME = "lastUsername";
    static final String PREF_USERNAME = "username";
    static final String PREF_PASSWORD = "password";
    static final String PREF_TOKEN = "token";

    static final String PREF_CITY_ID = "cityId";
    static final String PREF_LISTING_ID = "listingId";
    static final String PREF_SELECTED_LISTING_JSON = "selectedListingJSON";

    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static boolean getPrefIsLoggedIn(Context ctx) {
        return getSharedPreferences(ctx).getBoolean(PREF_IS_LOGGED_IN, false);
    }

    public static void setPrefIsLoggedIn(Context ctx, boolean temp) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putBoolean(PREF_IS_LOGGED_IN, temp);
        editor.apply();
    }

    public static String getPrefUsername(Context ctx) {
        return getSharedPreferences(ctx).getString(PREF_USERNAME, null);
    }

    public static void setPrefUsername(Context ctx, String temp) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USERNAME, temp);
        editor.apply();
    }

    public static String getPrefLastusername(Context ctx) {
        return getSharedPreferences(ctx).getString(PREF_LASTUSERNAME, null);
    }

    public static void setPrefLastUsername(Context ctx, String temp) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_LASTUSERNAME, temp);
        editor.apply();
    }

    public static String getPrefPassword(Context ctx) {
        return getSharedPreferences(ctx).getString(PREF_PASSWORD, null);
    }

    public static void setPrefPassword(Context ctx, String temp) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_PASSWORD, temp);
        editor.apply();
    }

    public static String getPrefToken(Context ctx) {
        return getSharedPreferences(ctx).getString(PREF_TOKEN, null);
    }

    public static void setPrefToken(Context ctx, String temp) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_TOKEN, temp);
        editor.apply();
    }

    public static void setPrefSelectedListingJson(Context ctx, Listings listings) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        Gson gson = new Gson();
        String strJSON = gson.toJson(listings);
        editor.putString(PREF_SELECTED_LISTING_JSON, strJSON);
        editor.apply();
    }

    public static Listings getPrefSelectedListingJSON(Context ctx) {
        String str = getSharedPreferences(ctx).getString(PREF_SELECTED_LISTING_JSON, "null");
        Gson gson = new Gson();
        Listings listings = gson.fromJson(str, Listings.class);
        return listings;
    }

    public static void setPrefCityId(Context ctx, int selectedCityId) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putInt(PREF_CITY_ID, selectedCityId);
        editor.apply();
    }

    public static int getPrefCityId(Context ctx) {
        return getSharedPreferences(ctx).getInt(PREF_CITY_ID, 1);
    }

    public static void setPrefListingId(Context ctx, int selectedCityId) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putInt(PREF_LISTING_ID, selectedCityId);
        editor.apply();
    }

    public static int getPrefListingId(Context ctx) {
        return getSharedPreferences(ctx).getInt(PREF_LISTING_ID, 29);
    }

}
