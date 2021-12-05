package es.unex.goenjoy.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import es.unex.goenjoy.R;
import es.unex.goenjoy.activity.DetalleLugarActivity;
import es.unex.goenjoy.adapter.MuseoAdapter;
import es.unex.goenjoy.api.ApiInterfaz;
import es.unex.goenjoy.model.Museo;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.List;

import es.unex.goenjoy.room.MuseoDao;
import es.unex.goenjoy.room.MuseoDatabase;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MuseosFragment extends Fragment implements MuseoAdapter.OnItemClickListener {
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
    public static final String EXTRA_RUTA = "ruta";
    public static final String EXTRA_FAVORITO = "favorito";
    public static final String EXTRA_DESEO = "deseo";
    public static final String EXTRA_TIPO = "tipo";

    private RecyclerView recycler;
    private MuseoAdapter adapter;
    private RecyclerView.LayoutManager lManager;
    private Context context;
    private List<Museo> museosList = new ArrayList<>();
    private MuseoDao mMuseoDao;


    public MuseosFragment() {
        // Required empty public constructor
    }

    public static MuseosFragment newInstance(String param1, String param2) {
        MuseosFragment fragment = new MuseosFragment();
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
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_museos, container, false);
        // Obtener el Recycler
        recycler = (RecyclerView) view.findViewById(R.id.reciclador);
        recycler.setHasFixedSize(true);
        // Usar un administrador para LinearLayout
        lManager = new LinearLayoutManager(getContext());
        recycler.setLayoutManager(lManager);
        // Crear un nuevo adaptador
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
                detailIntent.putExtra(EXTRA_STREETADRESS, clickedItem.getStreetAdress().toString());
                detailIntent.putExtra(EXTRA_LATITUDE, clickedItem.getLatitude());
                detailIntent.putExtra(EXTRA_LONGITUDE, clickedItem.getLongitude());
                detailIntent.putExtra(EXTRA_DESC, clickedItem.getDesc());
                detailIntent.putExtra(EXTRA_ACCESIBILITY, clickedItem.getAccesibility());
                detailIntent.putExtra(EXTRA_SCHEDULE, clickedItem.getSchedule());
                detailIntent.putExtra(EXTRA_RUTA, clickedItem.getRuta());
                detailIntent.putExtra(EXTRA_FAVORITO, clickedItem.getFav());
                detailIntent.putExtra(EXTRA_DESEO, clickedItem.getDeseo());
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
        mostrarDatos();
    }



    private void mostrarDatos(){
        adapter.load(museosList);
        recycler.setAdapter(adapter);
        adapter.setOnItemClickListener(MuseosFragment.this);
    }

    @Override
    public void onItemClick(int position) {
    }
}