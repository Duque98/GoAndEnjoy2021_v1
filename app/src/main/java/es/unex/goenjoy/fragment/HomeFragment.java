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

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import es.unex.goenjoy.R;
import es.unex.goenjoy.activity.DetalleLugarActivity;
import es.unex.goenjoy.adapter.MuseoAdapter;
import es.unex.goenjoy.api.ApiInterfaz;
import es.unex.goenjoy.model.Museo;
import es.unex.goenjoy.room.MuseoDao;
import es.unex.goenjoy.room.MuseoDatabase;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements MuseoAdapter.OnItemClickListener {

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

    private Context context;
    private RecyclerView recycler;
    private MuseoAdapter adapter;
    private MuseoDao mMuseoDao;
    private RecyclerView.LayoutManager lManager;
    private List<Museo> museosList = new ArrayList<>();

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        View view= inflater.inflate(R.layout.fragment_home, container, false);
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
                detailIntent.putExtra(EXTRA_STREETADRESS, clickedItem.getStreetAdress());
                detailIntent.putExtra(EXTRA_LATITUDE, clickedItem.getLatitude());
                detailIntent.putExtra(EXTRA_LONGITUDE, clickedItem.getLongitude());
                detailIntent.putExtra(EXTRA_DESC, clickedItem.getDesc());
                detailIntent.putExtra(EXTRA_ACCESIBILITY, clickedItem.getAccesibility());
                detailIntent.putExtra(EXTRA_SCHEDULE, clickedItem.getSchedule());
                detailIntent.putExtra(EXTRA_RUTA, clickedItem.getRuta());
                detailIntent.putExtra(EXTRA_FAVORITO, clickedItem.getFav());
                detailIntent.putExtra(EXTRA_DESEO, clickedItem.getDeseo());
                detailIntent.putExtra(EXTRA_TIPO, clickedItem.getTipo());
                startActivity(detailIntent);
            }
        });
        recycler.setAdapter(adapter);
        //TODO -  meter actualizacion
        Toast.makeText(getContext(), "Cargando los datos desde la API", Toast.LENGTH_LONG).show();

        return view;
    }
    @Override
    public void onStart(){
        super.onStart();
        MuseoDatabase db = MuseoDatabase.getDatabase(context);
        mMuseoDao = db.museoDao();
        cargarDatos();
    }
    @Override
    public void onResume(){
        super.onResume();
    }

    @Override
    public void onItemClick(int position) {
    }

    public void cargarDatos(){
        ApiInterfaz service = ApiInterfaz.retrofit.create(ApiInterfaz.class);
        //-- Cargamos los museos --
        Call<JsonObject> callMuseo = service.getMuseos();
        callMuseo.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                JsonArray museoJsonArray = response.body().getAsJsonArray("@graph");
                for(int i= 0 ; i < museoJsonArray.size(); i++){
                    Museo museo = new Museo();
                    JsonObject item = museoJsonArray.get(i).getAsJsonObject();
                    if(item.get("id") != null){museo.setId(item.get("id").getAsInt());}
                    if(item.get("title") != null){museo.setTitle(item.get("title").toString().substring(1, item.get("title").toString().length()-1));}
                    if(item.get("relation") != null){ museo.setRelation(item.get("relation").toString().substring(1, item.get("relation").toString().length()-1));}
                    if(item.get("address").getAsJsonObject().get("locality") != null){museo.setLocalidad(item.get("address").getAsJsonObject().get("locality").toString().substring(1, item.get("address").getAsJsonObject().get("locality").toString().length()-1));}
                    if(item.get("address").getAsJsonObject().get("postal-code") != null){museo.setPostalCode(item.get("address").getAsJsonObject().get("postal-code").toString().substring(1, item.get("address").getAsJsonObject().get("postal-code").toString().length()-1));}
                    if(item.get("address").getAsJsonObject().get("street-address") != null){museo.setStreetAdress(item.get("address").getAsJsonObject().get("street-address").toString().substring(1, item.get("address").getAsJsonObject().get("street-address").toString().length()-1));}
                    if(item.get("location") != null){museo.setLatitude(item.get("location").getAsJsonObject().get("latitude").getAsFloat());}
                    if(item.get("location") != null){museo.setLongitude(item.get("location").getAsJsonObject().get("longitude").getAsFloat());}
                    if(item.get("organization").getAsJsonObject().get("organization-desc") != null){museo.setDesc(item.get("organization").getAsJsonObject().get("organization-desc").toString().substring(1, item.get("organization").getAsJsonObject().get("organization-desc").toString().length()-1));}
                    if(item.get("organization").getAsJsonObject().get("accesibility") != null){museo.setAccesibility(item.get("organization").getAsJsonObject().get("accesibility").toString().substring(1, item.get("organization").getAsJsonObject().get("accesibility").toString().length()-1));}
                    if(item.get("organization").getAsJsonObject().get("schedule") != null){museo.setSchedule(item.get("organization").getAsJsonObject().get("schedule").toString().substring(1, item.get("organization").getAsJsonObject().get("schedule").toString().length()-1));}
                    museo.setFav(0);
                    museo.setRuta(0);
                    museo.setDeseo(0);
                    museo.setTipo(1); //Al ser museo es 1
                    museosList.add(museo);
                    mMuseoDao.insert(museo);
                }
            }
            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.e("ErrorCargaMuseos", t.getMessage());
            }
        });

        //-- Cargamos los parques --
        Call<JsonObject> callParques = service.getParques();
        callParques.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                JsonArray museoJsonArray = response.body().getAsJsonArray("@graph");
                for(int i= 0 ; i < museoJsonArray.size(); i++){
                    Museo museo = new Museo();
                    JsonObject item = museoJsonArray.get(i).getAsJsonObject();
                    if(item.get("id") != null){museo.setId(item.get("id").getAsInt());}
                    if(item.get("title") != null){museo.setTitle(item.get("title").toString().substring(1, item.get("title").toString().length()-1));}
                    if(item.get("relation") != null){ museo.setRelation(item.get("relation").toString().substring(1, item.get("relation").toString().length()-1));}
                    if(item.get("address").getAsJsonObject().get("locality") != null){museo.setLocalidad(item.get("address").getAsJsonObject().get("locality").toString().substring(1, item.get("address").getAsJsonObject().get("locality").toString().length()-1));}
                    if(item.get("address").getAsJsonObject().get("postal-code") != null){museo.setPostalCode(item.get("address").getAsJsonObject().get("postal-code").toString().substring(1, item.get("address").getAsJsonObject().get("postal-code").toString().length()-1));}
                    if(item.get("address").getAsJsonObject().get("street-address") != null){museo.setStreetAdress(item.get("address").getAsJsonObject().get("street-address").toString().substring(1, item.get("address").getAsJsonObject().get("street-address").toString().length()-1));}
                    if(item.get("location") != null){museo.setLatitude(item.get("location").getAsJsonObject().get("latitude").getAsFloat());}
                    if(item.get("location") != null){museo.setLongitude(item.get("location").getAsJsonObject().get("longitude").getAsFloat());}
                    if(item.get("organization").getAsJsonObject().get("organization-desc") != null){museo.setDesc(item.get("organization").getAsJsonObject().get("organization-desc").toString().substring(1, item.get("organization").getAsJsonObject().get("organization-desc").toString().length()-1));}
                    if(item.get("organization").getAsJsonObject().get("accesibility") != null){museo.setAccesibility(item.get("organization").getAsJsonObject().get("accesibility").toString().substring(1, item.get("organization").getAsJsonObject().get("accesibility").toString().length()-1));}
                    if(item.get("organization").getAsJsonObject().get("schedule") != null){museo.setSchedule(item.get("organization").getAsJsonObject().get("schedule").toString().substring(1, item.get("organization").getAsJsonObject().get("schedule").toString().length()-1));}
                    museo.setFav(0);
                    museo.setRuta(0);
                    museo.setDeseo(0);
                    museo.setTipo(2); //Al ser parque es 2
                    museosList.add(museo);
                    mMuseoDao.insert(museo);
                }
                adapter.load(museosList);
            }
            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.e("ErrorCargaMuseos", t.getMessage());
            }
        });
    }

}