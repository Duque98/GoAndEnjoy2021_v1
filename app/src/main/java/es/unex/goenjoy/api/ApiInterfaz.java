package es.unex.goenjoy.api;

import com.google.gson.JsonObject;

import java.util.List;

import es.unex.goenjoy.model.Museo;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface ApiInterfaz {

    String API_ROUTE_Museos = "201132-0-museos.json";

    @GET(API_ROUTE_Museos)
    Call<List<Museo>> getMuseos();

}
