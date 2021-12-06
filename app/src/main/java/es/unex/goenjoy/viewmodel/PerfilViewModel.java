package es.unex.goenjoy.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import es.unex.goenjoy.model.Perfil;
import es.unex.goenjoy.repository.MuseoRepository;

public class PerfilViewModel extends ViewModel {
    private MuseoRepository mRepository;

    public PerfilViewModel(MuseoRepository repository) {
        mRepository = repository;
    }

    public LiveData<List<Perfil>> get() { return mRepository.getAllProfile();}
    public void insert(Perfil perfil) { mRepository.insertPerfil(perfil); }
    public void delete() { mRepository.deleteProfile(); }
    public void update(Perfil perfil) { mRepository.update(perfil);}
}
