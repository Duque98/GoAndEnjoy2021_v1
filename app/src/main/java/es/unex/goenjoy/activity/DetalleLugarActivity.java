package es.unex.goenjoy.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import es.unex.goenjoy.adapter.MuseoAdapter;
import es.unex.goenjoy.model.Address;
import es.unex.goenjoy.model.Museo;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import es.unex.goenjoy.R;
import es.unex.goenjoy.room.MuseoDao;
import es.unex.goenjoy.room.MuseoDatabase;

import java.util.ArrayList;
import java.util.List;
import static es.unex.goenjoy.fragment.MuseosFragment.EXTRA_ACCESIBILITY;
import static es.unex.goenjoy.fragment.MuseosFragment.EXTRA_DESC;
import static es.unex.goenjoy.fragment.MuseosFragment.EXTRA_ID;
import static es.unex.goenjoy.fragment.MuseosFragment.EXTRA_LATITUDE;
import static es.unex.goenjoy.fragment.MuseosFragment.EXTRA_LOCALIDAD;
import static es.unex.goenjoy.fragment.MuseosFragment.EXTRA_LONGITUDE;
import static es.unex.goenjoy.fragment.MuseosFragment.EXTRA_POSTALCODE;
import static es.unex.goenjoy.fragment.MuseosFragment.EXTRA_RELATION;
import static es.unex.goenjoy.fragment.MuseosFragment.EXTRA_SCHEDULE;
import static es.unex.goenjoy.fragment.MuseosFragment.EXTRA_STREETADRESS;
import static es.unex.goenjoy.fragment.MuseosFragment.EXTRA_TITLE;
import static es.unex.goenjoy.fragment.MuseosFragment.EXTRA_DESEO;
import static es.unex.goenjoy.fragment.MuseosFragment.EXTRA_RUTA;
import static es.unex.goenjoy.fragment.MuseosFragment.EXTRA_FAVORITO;
import static es.unex.goenjoy.fragment.MuseosFragment.EXTRA_TIPO;

public class DetalleLugarActivity extends AppCompatActivity {
    public static final String EXTRA_SCHEDULE2 = "schedule";
    public static final String EXTRA_RELATION2 = "relation";
    ImageView bRuta;
    ImageView bFav;
    ImageView bDeseo;
    ImageView bAtras;
    Button bLink,bHorario, bLocalizacion, bComoLlegar;
    String title, relation, localidad, postalCode, streetAdress,
            desc, accesibility, schedule;
    int id;
    int fav;
    int deseo;
    int ruta;
    int tipo;
    Float latitude;
    Float longitude;
    private MuseoAdapter mMuseoAdaptador;
    private List<Museo> mMuseo;
    private MuseoDao mMuseoDao;

    @SuppressLint({"WrongViewCast", "ClickableViewAccessibility"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_lugar);
        final Intent intent = getIntent();
        //boton que nos permite visitar la pagina web del lugar.
        bLink = (Button) findViewById(R.id.btn_web);
        bLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Cogemos la url de los datos obtenidos
                String aux = intent.getStringExtra(EXTRA_RELATION);
                //Tras coger la url la pasamos al intent explicito para que se abra la aplicacion del buscador web
                Uri uri = Uri.parse(aux);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        id = intent.getIntExtra(EXTRA_ID, 0);
        title = intent.getStringExtra(EXTRA_TITLE);
        TextView tv_title = findViewById(R.id.tv_nombre);
        tv_title.setText(title);
        relation = intent.getStringExtra(EXTRA_RELATION);
        localidad = intent.getStringExtra(EXTRA_LOCALIDAD);
        TextView tv_localidad = findViewById(R.id.tv_localidad);
        tv_localidad.setText(localidad);
        postalCode = intent.getStringExtra(EXTRA_POSTALCODE);
        TextView tv_postalcode = findViewById(R.id.tv_postalcode);
        tv_postalcode.setText(postalCode);
        streetAdress = intent.getStringExtra(EXTRA_STREETADRESS);
        TextView tv_streetadress = findViewById(R.id.tv_streetadress);
        tv_streetadress.setText(streetAdress);
        latitude = intent.getFloatExtra(EXTRA_LATITUDE, 0.0f);
        longitude = intent.getFloatExtra(EXTRA_LONGITUDE, 0.0f);
        desc = intent.getStringExtra(EXTRA_DESC);
        TextView tv_desc = findViewById(R.id.tv_desc);
        tv_desc.setText(desc);
        accesibility = intent.getStringExtra(EXTRA_ACCESIBILITY);
        schedule = intent.getStringExtra(EXTRA_SCHEDULE);

        //Implementación necesaria para poder cambiar de activity y visualizar el horario
        bHorario = (Button) findViewById(R.id.btn_horario);
        bHorario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detailIntent = new Intent(v.getContext(), HorarioActivity.class);
                detailIntent.putExtra(EXTRA_SCHEDULE2, schedule);
                detailIntent.putExtra(EXTRA_RELATION2, relation);
                startActivityForResult(detailIntent, 0);
            }
        });

        //Implementación necesaria para marcar y desmarcar favorito
        fav = intent.getIntExtra(EXTRA_FAVORITO, 0);
        bFav = (ImageView) findViewById(R.id.iv_fav);
        if (fav == 1) {
            //Si existe, estrella rellena
            bFav.setImageResource(R.drawable.fav1);
        } else {
            //Si no existe, estrella vacia
            bFav.setImageResource(R.drawable.fav2);
        }
        bFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                meterFav();
            }
        });

        //Implementación necesaria para marcar y desmarcar como deseado
        deseo = intent.getIntExtra(EXTRA_DESEO, 0);
        bDeseo = (ImageView) findViewById(R.id.iv_deseo);
        //Comprobacion de si esta marcado o no
        if(deseo == 1){
            //Esta marcado por lo tanto cambiamos la imagen
            //Si existe, corazon relleno
            bDeseo.setImageResource(R.drawable.des2);
        }
        else{
            //No esta marcado por lo tanto cambiamos la imagen
            //Si no existe, corazon son relleno
            bDeseo.setImageResource(R.drawable.des1);
        }
        bDeseo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                meterDeseo();
            }
        });

        //Implementación necesaria para marcar y desmarcar en la ruta

        //Realizamos lo necesario para comprobar si la ruta esta marcada o no
        ruta = intent.getIntExtra(EXTRA_RUTA, 0);
        bRuta = (ImageView) findViewById(R.id.iv_ruta);
        if(ruta == 1){
            //Si existe, corazon relleno
            bRuta.setImageResource(R.drawable.ruta);
        }
        else{
            //Si no existe, corazon son relleno
            bRuta.setImageResource(R.drawable.ruta2);
        }
        bRuta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                meterRuta();
            }
        });

        bComoLlegar = (Button) findViewById(R.id.btn_comoLlegar);
        bComoLlegar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Esta es para abrir la navegacion en coche quitando los peajes
                Uri gmmIntentUri = Uri.parse("google.navigation:q= " + latitude + ", " + longitude+"&avoid=tf");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

        bLocalizacion = (Button) findViewById(R.id.btn_localizacion);
        bLocalizacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Esta es para mostrar el mapa
                Uri gmmIntentUri = Uri.parse("geo: " + latitude + ", " + longitude);

                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");

                startActivity(mapIntent);
            }
        });

        bAtras = (ImageView) findViewById(R.id.bAtras);
        bAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mMuseo = new ArrayList<Museo>();
        mMuseoAdaptador = new MuseoAdapter(mMuseo);
        //TODO - getContext problema al fav y tal
        MuseoDatabase db = MuseoDatabase.getDatabase(this.getBaseContext());
        mMuseoDao = db.museoDao();
    }


    private void meterRuta(){
        if(ruta == 0){
            ruta = 1;
            Toast.makeText(getApplicationContext(), "Añadido a la ruta: " + title, Toast.LENGTH_LONG).show();
            bRuta.setImageResource(R.drawable.ruta);
        }
        else{
            Toast.makeText(getApplicationContext(), "Eliminado de la lista deseo: " + title, Toast.LENGTH_LONG).show();
            bRuta.setImageResource(R.drawable.ruta2);
            ruta = 0;
        }
        Museo museo = new Museo(id, title, relation, localidad, postalCode, new Address(streetAdress),
                latitude, longitude, desc, accesibility, schedule, fav, deseo, ruta);
        mMuseoDao.update(museo);
    }
    private void meterFav() {
        if (fav == 0) { //si esta a 0, significa que está desmarcado como favorito.
            fav = 1; //Marcar como favorito
            Toast.makeText(getApplicationContext(), "Añadido a favoritos: " + title, Toast.LENGTH_LONG).show();
            bFav.setImageResource(R.drawable.fav1);
        } else { //si fav no es 0, significa que tiene valor 1, por lo tanto implica que es un lugar favorito
            Toast.makeText(getApplicationContext(), "Quitado de favoritos: " + title, Toast.LENGTH_LONG).show();
            bFav.setImageResource(R.drawable.fav2);
            fav=0; //Marcar como no favorito
        }
        //Declaración de un museo para poder actualizar la base de datos
        Museo museo = new Museo(id, title, relation, localidad, postalCode, new Address(streetAdress),
                latitude, longitude, desc, accesibility, schedule, fav, deseo, ruta);
        mMuseoDao.update(museo);
    }

    //Método necesario para poder introducir y sacar un lugar de deseados
    private void meterDeseo(){
        if(deseo == 0){ //Si el lugar no es deseado anteriormente
            deseo = 1; //Marcar un lugar como deseado
            Toast.makeText(getApplicationContext(), "Añadido a lista deseos: " + title, Toast.LENGTH_LONG).show();
            bDeseo.setImageResource(R.drawable.des2);
        }
        else{
            Toast.makeText(getApplicationContext(), "Eliminado de la lista deseo: " + title, Toast.LENGTH_LONG).show();
            bDeseo.setImageResource(R.drawable.des1);
            deseo = 0; //Marcar un lugar como no deseado
        }
        //Creación de un nuevo museo para actualizarlo en la base de datos
        Museo museo = new Museo(id, title, relation, localidad, postalCode, new Address(streetAdress),
                latitude, longitude, desc, accesibility, schedule, fav, deseo, ruta);
        mMuseoDao.update(museo);
    }
}