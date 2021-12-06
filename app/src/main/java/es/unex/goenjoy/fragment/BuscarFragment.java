package es.unex.goenjoy.fragment;

import static es.unex.goenjoy.utils.Constantes.EXTRA_ACCESIBILITY;
import static es.unex.goenjoy.utils.Constantes.EXTRA_DESC;
import static es.unex.goenjoy.utils.Constantes.EXTRA_DESEO;
import static es.unex.goenjoy.utils.Constantes.EXTRA_FAVORITO;
import static es.unex.goenjoy.utils.Constantes.EXTRA_ID;
import static es.unex.goenjoy.utils.Constantes.EXTRA_LATITUDE;
import static es.unex.goenjoy.utils.Constantes.EXTRA_LOCALIDAD;
import static es.unex.goenjoy.utils.Constantes.EXTRA_LONGITUDE;
import static es.unex.goenjoy.utils.Constantes.EXTRA_POSTALCODE;
import static es.unex.goenjoy.utils.Constantes.EXTRA_RELATION;
import static es.unex.goenjoy.utils.Constantes.EXTRA_RUTA;
import static es.unex.goenjoy.utils.Constantes.EXTRA_SCHEDULE;
import static es.unex.goenjoy.utils.Constantes.EXTRA_STREETADRESS;
import static es.unex.goenjoy.utils.Constantes.EXTRA_TITLE;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import es.unex.goenjoy.R;
import es.unex.goenjoy.activity.DetalleLugarActivity;
import es.unex.goenjoy.adapter.MuseoAdapter;
import es.unex.goenjoy.api.ApiInterfaz;
import es.unex.goenjoy.model.Museo;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import es.unex.goenjoy.repository.AppContainer;
import es.unex.goenjoy.repository.MyApplication;
import es.unex.goenjoy.room.MuseoDao;
import es.unex.goenjoy.room.MuseoDatabase;
import es.unex.goenjoy.viewmodel.MuseosViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuscarFragment extends Fragment implements MuseoAdapter.OnItemClickListener {

    private RecyclerView recycler;
    private MuseoAdapter adapter;
    private RecyclerView.LayoutManager lManager;
    private Context context;
    private List<Museo> museosList = new ArrayList<>();
    Button btnBusq;
    EditText etBusq;
    String texto;
    private Museo museo;
    MuseosViewModel museosViewModel;

    public BuscarFragment() {
        // Required empty public constructor
    }

    public static BuscarFragment newInstance(String param1, String param2) {
        BuscarFragment fragment = new BuscarFragment();
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
        View view= inflater.inflate(R.layout.fragment_buscar, container, false);
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
                detailIntent.putExtra(EXTRA_LOCALIDAD, clickedItem.getAddress().getLocality());
                detailIntent.putExtra(EXTRA_POSTALCODE, clickedItem.getAddress().getPostalCode());
                detailIntent.putExtra(EXTRA_STREETADRESS, clickedItem.getAddress().getStreetAddress());
                detailIntent.putExtra(EXTRA_LATITUDE, clickedItem.getLocation().getLatitude());
                detailIntent.putExtra(EXTRA_LONGITUDE, clickedItem.getLocation().getLongitude());
                detailIntent.putExtra(EXTRA_DESC, clickedItem.getOrganization().getOrganizationDesc());
                detailIntent.putExtra(EXTRA_ACCESIBILITY, clickedItem.getOrganization().getAccesibility());
                detailIntent.putExtra(EXTRA_SCHEDULE, clickedItem.getOrganization().getSchedule());
                detailIntent.putExtra(EXTRA_RUTA, clickedItem.getRuta());
                detailIntent.putExtra(EXTRA_FAVORITO, clickedItem.getFav());
                detailIntent.putExtra(EXTRA_DESEO, clickedItem.getDeseo());
                startActivity(detailIntent);
            }
        });
        recycler.setAdapter(adapter);
        //Inicialización de la estructura del buscador
        etBusq = (EditText) view.findViewById(R.id.et_busq);
        btnBusq = (Button) view.findViewById(R.id.btn_busq);
        btnBusq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                texto = etBusq.getText().toString().trim();
                mostrarDatos(texto);
            }
        });
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

    private void mostrarDatos(String texto){
        List <Museo> museoAux = new ArrayList<>();
        AppContainer appContainer = ((MyApplication) this.getActivity().getApplication()).appContainer;
        museosViewModel = new ViewModelProvider(this, appContainer.museosFactory).get(MuseosViewModel.class);
        museosViewModel.getAllMuseos().observe(getViewLifecycleOwner(), new Observer<List<Museo>>() {
            @Override
            public void onChanged(List<Museo> museos) {
                for(Museo m: museos){
                    if(m.getTitle().toLowerCase().contains(texto.toLowerCase())){
                        museoAux.add(m);
                    }
                }
                adapter.load(museos);
            }
        });
        if(museoAux!=null){
            adapter.load(museoAux);
            recycler.setAdapter(adapter);
        }
    }

    @Override
    public void onItemClick(int position) {
    }
}