package es.unex.goenjoy.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import es.unex.goenjoy.R;
import es.unex.goenjoy.fragment.MuseosFragment;
import es.unex.goenjoy.model.Museo;
import es.unex.goenjoy.model.Perfil;
import es.unex.goenjoy.repository.AppContainer;
import es.unex.goenjoy.repository.MyApplication;
import es.unex.goenjoy.room.MuseoDatabase;
import es.unex.goenjoy.room.PerfilDao;
import es.unex.goenjoy.viewmodel.PerfilViewModel;

import java.util.List;

public class EditarPerfilActivity extends AppCompatActivity {
    PerfilViewModel perfilViewModel;
    Button bModificar, bBorrar;
    EditText etNombreUsuario, etCorreo;
    ImageView bAtras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        bModificar = findViewById(R.id.bModificar);
        bBorrar = findViewById(R.id.bBorrar);
        etNombreUsuario = findViewById(R.id.etNombreUsuario);
        etCorreo = findViewById(R.id.etCorreo);

        AppContainer appContainer = ((MyApplication)getApplication()).appContainer;
        perfilViewModel = new ViewModelProvider(this, appContainer.perfilFactory).get(PerfilViewModel.class);

        bModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditarPerfilActivity.this, LugaresActivity.class);
                String nombreUsuario = etNombreUsuario.getText().toString().trim();
                String correo = etCorreo.getText().toString().trim();
                Perfil p = new Perfil(1,nombreUsuario, correo);
                perfilViewModel.insert(p);
                startActivity(intent);
            }
        });

        bBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditarPerfilActivity.this, LugaresActivity.class);
                perfilViewModel.delete();
                startActivity(intent);
            }
        });


        //LiveData<List<Perfil>> p = perfilViewModel.get();
        perfilViewModel.get().observe(this, new Observer<List<Perfil>>() {
            @Override
            public void onChanged(List<Perfil> perfils) {
                if(perfils.isEmpty()){ // si no existe ningún perfil creado, nos lleva a crear uno.
                    Intent intent = new Intent(EditarPerfilActivity.this, CrearPerfilActivity.class);
                    startActivity(intent);
                    finish();
                }else { // aquí se edita el perfil.
                    etNombreUsuario.setText(perfils.get(0).getNombre());
                    etCorreo.setText(perfils.get(0).getEmail());
                }
            }
        });

        bAtras = (ImageView) findViewById(R.id.bAtras);
        bAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
