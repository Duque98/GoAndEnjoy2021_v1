package es.unex.goenjoy.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import es.unex.goenjoy.model.Museo;
import es.unex.goenjoy.repository.MuseoRepository;

public class DetallesViewModel extends ViewModel {
    private MuseoRepository mRepository;


    public DetallesViewModel(MuseoRepository repository) {
        mRepository = repository;
    }

    public void insert(Museo museo) { mRepository.insert(museo); }
    public void update(Museo museo) { mRepository.update(museo); }
    public void delete(Museo museo) { mRepository.delete(museo); }

}
