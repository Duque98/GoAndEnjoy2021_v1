package es.unex.goenjoy.factory;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import es.unex.goenjoy.repository.MuseoRepository;
import es.unex.goenjoy.viewmodel.PerfilViewModel;

public class PerfilFactory extends ViewModelProvider.NewInstanceFactory {
    private final MuseoRepository museoRepository;

    public PerfilFactory(MuseoRepository museoRepository){
        this.museoRepository = museoRepository;
    }

    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass){
        return (T) new PerfilViewModel(museoRepository);
    }
}
