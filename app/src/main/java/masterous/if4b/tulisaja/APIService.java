package masterous.if4b.tulisaja;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIService {
    @FormUrlEncoded
    @POST("getAllPost")
    Call<ValueData<List<Post>>> getPost(@Field("key") String key);
}