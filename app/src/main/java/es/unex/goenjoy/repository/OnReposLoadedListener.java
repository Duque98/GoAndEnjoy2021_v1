package es.unex.goenjoy.repository;

import com.google.gson.JsonArray;

import java.util.List;

import es.unex.goenjoy.model.Museo;

public interface OnReposLoadedListener {
    public void onReposLoaded(List<Museo> reposMuseo);
}
