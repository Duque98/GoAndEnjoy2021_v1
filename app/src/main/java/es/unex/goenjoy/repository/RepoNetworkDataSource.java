package es.unex.goenjoy.repository;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import es.unex.goenjoy.model.Museo;

public class RepoNetworkDataSource {
    private static final String LOG_TAG = RepoNetworkDataSource.class.getSimpleName();
    private static RepoNetworkDataSource sInstance;

    // LiveData storing the latest downloaded weather forecasts
    private final MutableLiveData<Museo[]> mDownloadedRepos;

    private RepoNetworkDataSource() {
        mDownloadedRepos = new MutableLiveData<>();
    }

    public synchronized static RepoNetworkDataSource getInstance() {
        Log.d(LOG_TAG, "Getting the network data source");
        if (sInstance == null) {
            sInstance = new RepoNetworkDataSource();
            Log.d(LOG_TAG, "Made new network data source");
        }
        return sInstance;
    }

    public LiveData<Museo[]> getCurrentMuseos() {
        return mDownloadedRepos;
    }

    public void fetchMuseos() {
        Log.d(LOG_TAG, "Fetch repos started");
        // Get gata from network and pass it to LiveData
        AppExecutors.getInstance().networkIO().execute(new ReposNetworkLoaderRunnable(repos -> mDownloadedRepos.postValue(repos.toArray(new Museo[0]))));
    }

}
