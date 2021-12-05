package es.unex.goenjoy.factory;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import es.unex.goenjoy.repository.MuseoRepository;
import es.unex.goenjoy.viewmodel.DetallesViewModel;

public class DetallesFactory extends ViewModelProvider.NewInstanceFactory {
    private final MuseoRepository museoRepository;

    public DetallesFactory(MuseoRepository museoRepository){
        this.museoRepository = museoRepository;
    }

    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass){
        return (T) new DetallesViewModel(museoRepository);
    }
}
