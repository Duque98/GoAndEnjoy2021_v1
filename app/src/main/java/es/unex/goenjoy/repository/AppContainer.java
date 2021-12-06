package es.unex.goenjoy.repository;

import android.content.Context;

import es.unex.goenjoy.factory.MuseosFactory;
import es.unex.goenjoy.factory.PerfilFactory;
import es.unex.goenjoy.room.MuseoDatabase;

public class AppContainer {
    private MuseoDatabase db;
    private RepoNetworkDataSource networkDataSource;
    public MuseoRepository repository;
    public MuseosFactory museosFactory;
    public PerfilFactory perfilFactory;

    public AppContainer(Context context){
        db = MuseoDatabase.getDatabase(context);
        networkDataSource = RepoNetworkDataSource.getInstance();
        repository = MuseoRepository.getInstance(db.museoDao(),db.perfilDao(),networkDataSource);
        museosFactory = new MuseosFactory(repository);
        perfilFactory = new PerfilFactory(repository);
    }

}
