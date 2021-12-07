package es.unex.goenjoy.activity;


import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import es.unex.goenjoy.R;
import es.unex.goenjoy.model.Perfil;
import es.unex.goenjoy.repository.AppContainer;
import es.unex.goenjoy.repository.MyApplication;
import es.unex.goenjoy.room.MuseoDatabase;
import es.unex.goenjoy.room.PerfilDao;
import es.unex.goenjoy.viewmodel.PerfilViewModel;

public class CrearPerfilActivity extends AppCompatActivity {
    PerfilViewModel perfilViewModel;
    EditText et_nombreUsuario, et_correo;
    ImageView bAtras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_perfil);

        et_nombreUsuario = findViewById(R.id.nombreUsuario);
        et_correo = findViewById(R.id.email);

        AppContainer appContainer = ((MyApplication)getApplication()).appContainer;
        perfilViewModel = new ViewModelProvider(this, appContainer.perfilFactory).get(PerfilViewModel.class);

        Button bCrear = findViewById(R.id.bCrear);
        bCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CrearPerfilActivity.this, LugaresActivity.class);
                String nombreUsuario = et_nombreUsuario.getText().toString().trim();
                String correo = et_correo.getText().toString().trim();
                perfilViewModel.insert(new Perfil(1,nombreUsuario,correo));
                startActivity(intent);
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