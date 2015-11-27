package kz.growit.altynorda.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Талгат on 27.11.2015.
 */
public class SaveSharedPreferences {
    static final String PREF_CITY_ID = "cityId";

    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static void setPrefCityId(Context ctx, int selectedCityId) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putInt(PREF_CITY_ID, selectedCityId);
        editor.apply();
    }

    public static int getPrefCityId(Context ctx) {
        return getSharedPreferences(ctx).getInt(PREF_CITY_ID, 1);
    }

}
