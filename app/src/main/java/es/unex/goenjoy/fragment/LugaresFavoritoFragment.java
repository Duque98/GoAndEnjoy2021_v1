//*
package es.unex.goenjoy.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import es.unex.goenjoy.R;
import es.unex.goenjoy.activity.DetalleLugarActivity;
import es.unex.goenjoy.adapter.MuseoAdapter;
import es.unex.goenjoy.model.Museo;
import es.unex.goenjoy.repository.AppContainer;
import es.unex.goenjoy.repository.MyApplication;
import es.unex.goenjoy.room.MuseoDao;
import es.unex.goenjoy.room.MuseoDatabase;
import es.unex.goenjoy.viewmodel.MuseosViewModel;

import java.util.ArrayList;
import java.util.List;

public class LugaresFavoritoFragment extends Fragment implements MuseoAdapter.OnItemClickListener {
    public static final String EXTRA_ID = "id";
    public static final String EXTRA_TITLE = "title";
    public static final String EXTRA_RELATION = "relation";
    public static final String EXTRA_LOCALIDAD = "localidad";
    public static final String EXTRA_POSTALCODE = "postalCode";
    public static final String EXTRA_STREETADRESS = "streetAdrres";
    public static final String EXTRA_LATITUDE = "latitude";
    public static final String EXTRA_LONGITUDE = "longitude";
    public static final String EXTRA_DESC = "desc";
    public static final String EXTRA_ACCESIBILITY = "accesibility";
    public static final String EXTRA_SCHEDULE = "schedule";
    public static final String EXTRA_FAVORITO = "favorito";
    public static final String EXTRA_DESEO = "deseo";
    public static final String EXTRA_RUTA = "ruta";
    private RecyclerView recycler;
    private MuseoAdapter adapter;
    private RecyclerView.LayoutManager lManager;
    private Context context;
    private List<Museo> museosList = new ArrayList<>();
    private MuseoDao mMuseoDao;
    private MuseosViewModel museosViewModel;

    public LugaresFavoritoFragment() {

    }

    public static LugaresFavoritoFragment newInstance(String param1, String param2) {
        LugaresFavoritoFragment fragment = new LugaresFavoritoFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_museos, container, false);
        recycler = (RecyclerView) view.findViewById(R.id.reciclador);
        recycler.setHasFixedSize(true);
        lManager = new LinearLayoutManager(getContext());
        recycler.setLayoutManager(lManager);

        inicio();

        adapter = new MuseoAdapter(museosList);
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                museosList= adapter.getmMuseo();
                Intent detailIntent = new Intent(getActivity(), DetalleLugarActivity.class);
                Museo clickedItem = adapter.getmMuseo().get(recycler.getChildAdapterPosition(v));

                detailIntent.putExtra(EXTRA_ID, clickedItem.getId());
                detailIntent.putExtra(EXTRA_TITLE, clickedItem.getTitle());
                detailIntent.putExtra(EXTRA_RELATION, clickedItem.getRelation());
                detailIntent.putExtra(EXTRA_LOCALIDAD, clickedItem.getLocalidad());
                detailIntent.putExtra(EXTRA_POSTALCODE, clickedItem.getPostalCode());
                detailIntent.putExtra(EXTRA_STREETADRESS, clickedItem.getStreetAdress());
                detailIntent.putExtra(EXTRA_LATITUDE, clickedItem.getLatitude());
                detailIntent.putExtra(EXTRA_LONGITUDE, clickedItem.getLongitude());
                detailIntent.putExtra(EXTRA_DESC, clickedItem.getDesc());
                detailIntent.putExtra(EXTRA_ACCESIBILITY, clickedItem.getAccesibility());
                detailIntent.putExtra(EXTRA_SCHEDULE, clickedItem.getSchedule());
                detailIntent.putExtra(EXTRA_FAVORITO, clickedItem.getFav());
                detailIntent.putExtra(EXTRA_DESEO,clickedItem.getDeseo());
                detailIntent.putExtra(EXTRA_RUTA, clickedItem.getRuta());

                startActivity(detailIntent);
            }
        });
        recycler.setAdapter(adapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
    }
    public void inicio(){
        AppContainer appContainer = ((MyApplication) this.getActivity().getApplication()).appContainer;
        museosViewModel = new ViewModelProvider(this, appContainer.museosFactory).get(MuseosViewModel.class);
        museosViewModel.getAllMuseoFav().observe(getViewLifecycleOwner(), new Observer<List<Museo>>() {
            @Override
            public void onChanged(List<Museo> museos) {
                adapter.load(museos);
            }
        });
    }



    @Override
    public void onItemClick(int position) {

    }
}