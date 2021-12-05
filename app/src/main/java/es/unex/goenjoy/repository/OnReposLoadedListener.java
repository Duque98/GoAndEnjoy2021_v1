package es.unex.goenjoy.repository;

import java.util.List;

import es.unex.goenjoy.model.Museo;

public interface OnReposLoadedListener {
    public void onReposLoaded(List<Museo> reposMuseo);
}
