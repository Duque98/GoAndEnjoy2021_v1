package es.unex.goenjoy.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import es.unex.goenjoy.model.Museo;
import es.unex.goenjoy.repository.MuseoRepository;

public class MuseosViewModel extends ViewModel {
    private MuseoRepository mRepository;

    private LiveData<List<Museo>> mAllLugares;
    private LiveData<List<Museo>> mAllMuseo;
    private LiveData<List<Museo>> mAllMuseoFav;
    private LiveData<List<Museo>> mAllMuseoDes;
    private LiveData<List<Museo>> mAllMuseoRuta;

    public MuseosViewModel(MuseoRepository repository) {
        mRepository = repository;
        mAllLugares = mRepository.getAllLugares();
        mAllMuseo = mRepository.getAllMuseos();
        mAllMuseoFav = mRepository.getAllFav();
        mAllMuseoRuta = mRepository.getAllRuta();
        mAllMuseoDes = mRepository.getAllDes();
    }

    public LiveData<List<Museo>> getAllLugares() { return mAllLugares; }
    public LiveData<List<Museo>> getAllMuseos(){ return mAllMuseo;}
    public LiveData<List<Museo>> getAllMuseoFav() {return mAllMuseoFav;}
    public LiveData<List<Museo>> getAllMuseoRuta() { return mAllMuseoRuta; }
    public LiveData<List<Museo>> getmAllMuseosDes() {return mAllMuseoDes;}
    public void insert(Museo museo) { mRepository.insert(museo); }
    public void update(Museo museo) { mRepository.update(museo); }
    public void delete(Museo museo) { mRepository.delete(museo); }
    public void deleteAll() { mRepository.deleteAll(); }

}
