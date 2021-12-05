package es.unex.goenjoy.repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import es.unex.goenjoy.api.ApiInterfaz;
import es.unex.goenjoy.model.Museo;
import retrofit2.Call;
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
        Call<List<Museo>> callMuseo = service.getMuseos();
        try {
            Response<List<Museo>> responseMuseo = callMuseo.execute();
            List<Museo> reposMuseo = (responseMuseo.body() == null ? new ArrayList<>() : responseMuseo.body());
            AppExecutors.getInstance().mainThread().execute(() -> mOnReposLoadedListener.onReposLoaded(reposMuseo));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
