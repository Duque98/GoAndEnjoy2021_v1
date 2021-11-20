package es.unex.goenjoy.api;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface ApiInterfaz {

    String API_ROUTE_Museos = "201132-0-museos.json";
    String API_ROUTE_Parques = "200761-0-parques-jardines.json";

    @GET(API_ROUTE_Museos)
    Call<JsonObject> getMuseos();

    @GET(API_ROUTE_Parques)
    Call<JsonObject> getParques();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://datos.madrid.es/egob/catalogo/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
