package es.unex.goenjoy.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.unex.goenjoy.api.ApiInterfaz;
import es.unex.goenjoy.model.Museo;
import es.unex.goenjoy.model.Perfil;
import es.unex.goenjoy.room.MuseoDao;
import es.unex.goenjoy.room.MuseoDatabase;
import es.unex.goenjoy.room.PerfilDao;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MuseoRepository {
    private MuseoDao mMuseoDao;
    private PerfilDao mPerfilDao;
    private LiveData<List<Museo>> mAllLugares;
    private LiveData<List<Museo>> mAllMuseos;
    private LiveData<List<Museo>> mAllMuseosDes;
    private LiveData<List<Museo>> mAllMuseosRuta;
    private LiveData<List<Perfil>> mPerfil;
    private LiveData<List<Museo>> mAllMuseosFav;

    private static final String LOG_TAG = MuseoRepository.class.getSimpleName();
    private static MuseoRepository sInstance;
    private final RepoNetworkDataSource mRepoNetworkDataSource;
    private final AppExecutors mExecutors = AppExecutors.getInstance();
    private final Map<String, Long> lastUpdateTimeMillisMap = new HashMap<>();
    private static final long MIN_TIME_FROM_LAST_FETCH_MILLIS = 30000;

    private MuseoRepository(MuseoDao mDao, PerfilDao pDao, RepoNetworkDataSource repoNetworkDataSource){
        this.mMuseoDao = mDao;
        this.mPerfilDao = pDao;
        this.mRepoNetworkDataSource = repoNetworkDataSource;
        doFetch();
        LiveData<Museo[]>liveData = this.mRepoNetworkDataSource.getCurrentMuseos();
        liveData.observeForever(newReposFromNetwork ->{
            mExecutors.diskIO().execute(() -> {
                mMuseoDao.bulkInsert(Arrays.asList(newReposFromNetwork));
                Log.d(LOG_TAG, "New values inserted in Room");
            });
        });
        mAllLugares = mMuseoDao.getAll();
        mAllMuseos = mMuseoDao.getAllMuseo();
        mAllMuseosDes = mMuseoDao.getAllDes();
        mAllMuseosRuta = mMuseoDao.getAllRuta();
        mAllMuseosFav = mMuseoDao.getAllFav();
        mPerfil = mPerfilDao.get();
    }

    public synchronized static MuseoRepository getInstance(MuseoDao museoDao, PerfilDao perfilDao, RepoNetworkDataSource nds) {
        Log.d(LOG_TAG, "Getting the repository");
        if (sInstance == null) {
            sInstance = new MuseoRepository(museoDao, perfilDao, nds);
            Log.d(LOG_TAG, "Made new repository");
        }
        return sInstance;
    }

    public void doFetch(){
        Log.d(LOG_TAG, "Cargando de la api en repository");
        AppExecutors.getInstance().diskIO().execute(()->{
            //mMuseoDao.deleteAll();
            mRepoNetworkDataSource.fetchMuseos();
        });
    }

    public LiveData<List<Museo>> getAllLugares() {
        return mAllLugares;
    }
    public LiveData<List<Museo>> getAllMuseos() {return mAllMuseos;}
    public LiveData<List<Museo>> getAllDes() {
        return mAllMuseosDes;
    }
    public LiveData<List<Museo>> getAllRuta() {
        return mAllMuseosRuta;
    }
    public LiveData<List<Museo>> getAllFav() {
        return mAllMuseosFav;
    }

    public LiveData<List<Perfil>> getAllProfile() {
        return mPerfil;
    }

    public void insert(final Museo museo) {
        MuseoDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mMuseoDao.insert(museo);
            }
        });
    }
    public void insertPerfil(final Perfil perfil) {
        MuseoDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mPerfilDao.insert(perfil);
            }
        });
    }

    public void update(final Perfil perfil) {
        MuseoDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mPerfilDao.update(perfil);
            }
        });
    }


    public void update(final Museo museo) {
        MuseoDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mMuseoDao.update(museo);
            }
        });
    }

    public void delete(final Museo museo) {
        MuseoDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mMuseoDao.delete(museo);
            }
        });
    }

    public void deleteAll() {
        MuseoDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mMuseoDao.deleteAll();
            }
        });
    }

    public void deleteProfile() {
        MuseoDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mPerfilDao.delete();
            }
        });
    }

}
