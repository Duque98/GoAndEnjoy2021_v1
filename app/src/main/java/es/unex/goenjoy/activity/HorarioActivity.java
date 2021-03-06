package es.unex.goenjoy.activity;

import static es.unex.goenjoy.utils.Constantes.EXTRA_RELATION2;
import static es.unex.goenjoy.utils.Constantes.EXTRA_SCHEDULE2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import es.unex.goenjoy.R;


public class HorarioActivity extends AppCompatActivity {
    String schedule;
    Button bLink;
    ImageView bAtras;

    @SuppressLint({"WrongViewCast", "ClickableViewAccessibility"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horario);
        final Intent intent = getIntent();
        schedule = intent.getStringExtra(EXTRA_SCHEDULE2);
        TextView textViewHorario = findViewById(R.id.tv_horario);
        if(schedule.length() <= 2){
            textViewHorario.setText("No existe información del horario");
        }else{
            textViewHorario.setText(schedule);
        }

        bLink = (Button) findViewById(R.id.btn_web);
        bLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String aux = intent.getStringExtra(EXTRA_RELATION2);
                Uri uri = Uri.parse(aux);
                //Toast.makeText(getApplicationContext(),aux2, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
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
