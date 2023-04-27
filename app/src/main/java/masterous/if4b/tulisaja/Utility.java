package masterous.if4b.tulisaja;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Utility {
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
}
