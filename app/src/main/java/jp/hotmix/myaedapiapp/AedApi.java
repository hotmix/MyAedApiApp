package jp.hotmix.myaedapiapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by hotmix on 2017/06/14.
 */

public interface AedApi {
    @GET("api/aedinfo/{perfecture}/{city}/")
    Call<List<AedModel>> apiGetAedList(
            @Path("perfecture") String perfecture,
            @Path("city") String city
    );
}
