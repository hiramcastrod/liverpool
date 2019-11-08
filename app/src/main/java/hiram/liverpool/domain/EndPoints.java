package hiram.liverpool.domain;

import hiram.liverpool.model.PlpResults;
import hiram.liverpool.model.Response;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface EndPoints {

    @GET("plp?")
    Call<Response> getFindItems(@Query("search-string") String search);

}
