package es.unex.goenjoy.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import es.unex.goenjoy.model.Perfil;
import es.unex.goenjoy.repository.MuseoRepository;

public class PerfilViewModel extends ViewModel {
    private MuseoRepository mRepository;
    private LiveData<List<Perfil>> mPerfil;

    public PerfilViewModel(MuseoRepository repository) {
        mRepository = repository;
        mPerfil = mRepository.getAllProfile();
    }

    public LiveData<List<Perfil>> get() { return mPerfil; }
    public void insert(Perfil perfil) { mRepository.insertPerfil(perfil); }
    public void delete() { mRepository.deleteProfile(); }
    public void update(Perfil perfil) { mRepository.update(perfil);}
}
