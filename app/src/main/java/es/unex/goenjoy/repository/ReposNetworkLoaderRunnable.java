package es.unex.goenjoy.repository;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import es.unex.goenjoy.api.ApiInterfaz;
import es.unex.goenjoy.model.Museo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReposNetworkLoaderRunnable implements Runnable{
    private final OnReposLoadedListener mOnReposLoadedListener;

    public ReposNetworkLoaderRunnable(OnReposLoadedListener onReposLoadedListener){
        mOnReposLoadedListener = onReposLoadedListener;
    }

    @Override
    public void run() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://datos.madrid.es/egob/catalogo/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterfaz service = retrofit.create(ApiInterfaz.class);
       // Call<List<Museo>> callMuseo = null;
        service.getMuseos().enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                JsonArray lMuseosArray = response.body().getAsJsonArray("@graph");
                List<Museo> lMuseos = new ArrayList<>();
                Type listType = new TypeToken<List<Museo>>(){}.getType();
                Gson gson = new Gson();
                lMuseos = gson.fromJson(lMuseosArray.toString(), listType);

                Log.d("@@",lMuseos.toString());
                List<Museo> finalLMuseos = lMuseos;
                AppExecutors.getInstance().mainThread().execute(() -> mOnReposLoadedListener.onReposLoaded(finalLMuseos));
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                t.printStackTrace();
            }
        });
        /*try {
            Response<List<Museo>> responseMuseo = callMuseo.execute();
            List<Museo> reposMuseo = responseMuseo.body() == null ? new ArrayList<>() : responseMuseo.body();
            AppExecutors.getInstance().mainThread().execute(() -> mOnReposLoadedListener.onReposLoaded(reposMuseo));
        }
        catch (IOException e){
            e.printStackTrace();
        }*/
    }
}
