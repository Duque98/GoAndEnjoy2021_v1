package es.unex.goenjoy.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import es.unex.goenjoy.R;
import es.unex.goenjoy.model.Perfil;
import es.unex.goenjoy.room.MuseoDatabase;
import es.unex.goenjoy.room.PerfilDao;

public class CrearPerfilActivity extends AppCompatActivity {
    EditText et_nombreUsuario, et_correo;
    ImageView bAtras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_perfil);

        et_nombreUsuario = findViewById(R.id.nombreUsuario);
        et_correo = findViewById(R.id.email);
        Button bCrear = findViewById(R.id.bCrear);
        bCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CrearPerfilActivity.this, LugaresActivity.class);
                String nombreUsuario = et_nombreUsuario.getText().toString().trim();
                String correo = et_correo.getText().toString().trim();
                MuseoDatabase db = MuseoDatabase.getDatabase(getBaseContext());
                PerfilDao perfilDao = db.perfilDao();
                perfilDao.insert(new Perfil(1,nombreUsuario,correo));
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