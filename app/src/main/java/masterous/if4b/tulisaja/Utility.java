package masterous.if4b.tulisaja;

import android.content.Context;
import android.content.SharedPreferences;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Utility {
    private static final String PREFERENCE_FILE_KEY = Utility.class.getPackage().getName();
    private static final String BASE_URL = "https://restapi.mdp.ac.id/tulisaja/";
    public static Retrofit retrofit;

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static void setValue(Context context, String xPref, String xValue) {
        SharedPreferences sp = context.getSharedPreferences(PREFERENCE_FILE_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(xPref, xValue);
        editor.commit();
    }

    public static String getValue(Context context, String xPref) {
        SharedPreferences sp = context.getSharedPreferences(PREFERENCE_FILE_KEY, Context.MODE_PRIVATE);
        String xValue = sp.getString(xPref, null);
        return xValue;
    }

    public static boolean checkValue(Context context, String xPref) {
        SharedPreferences sp = context.getSharedPreferences(PREFERENCE_FILE_KEY, Context.MODE_PRIVATE);
        String xValue = sp.getString(xPref, null);
        return xValue != null;
    }
}
