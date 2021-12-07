package es.unex.goenjoy.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

import es.unex.goenjoy.R;
import es.unex.goenjoy.adapter.MuseoAdapter;
import es.unex.goenjoy.model.Perfil;
import es.unex.goenjoy.repository.AppContainer;
import es.unex.goenjoy.repository.MyApplication;
import es.unex.goenjoy.viewmodel.PerfilViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditarPerfilFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditarPerfilFragment extends Fragment implements MuseoAdapter.OnItemClickListener {

    Context context;
    PerfilViewModel perfilViewModel;
    Button bModificar, bBorrar;
    EditText etNombreUsuario, etCorreo;
    ImageView bAtras;


    public EditarPerfilFragment() {

    }

    public static EditarPerfilFragment newInstance(String param1, String param2) {
        EditarPerfilFragment fragment = new EditarPerfilFragment();
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
        View v = inflater.inflate(R.layout.fragment_editar_perfil, container, false);
        bModificar = v.findViewById(R.id.bModificar);
        bBorrar = v.findViewById(R.id.bBorrar);
        etNombreUsuario = v.findViewById(R.id.etNombreUsuario);
        etCorreo = v.findViewById(R.id.etCorreo);

        AppContainer appContainer = ((MyApplication)getActivity().getApplication()).appContainer;
        perfilViewModel = new ViewModelProvider(this, appContainer.perfilFactory).get(PerfilViewModel.class);
        perfilViewModel.get().observe(getViewLifecycleOwner(), new Observer<List<Perfil>>() {
            @Override
            public void onChanged(List<Perfil> perfils) {
                if(perfils.isEmpty()){ //No hay perfil creado
                    etNombreUsuario.setText("");
                    etCorreo.setText("");
                    bModificar.setText("Crear");
                }else { // aquí se edita el perfil.
                    etNombreUsuario.setText(perfils.get(0).getNombre());
                    etCorreo.setText(perfils.get(0).getEmail());
                    bModificar.setText("Modificar");
                }
            }
        });

        bModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombreUsuario = etNombreUsuario.getText().toString().trim();
                String correo = etCorreo.getText().toString().trim();
                Perfil p = new Perfil(1,nombreUsuario, correo);
                perfilViewModel.insert(p);
                Toast.makeText(context,"Se han actualizado los datos de su perfil", Toast.LENGTH_SHORT).show();
            }
        });

        bBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                perfilViewModel.delete();
                bModificar.setText("Crear");
                etNombreUsuario.setText("");
                etCorreo.setText("");
                Toast.makeText(context,"Se ha eliminado el perfil", Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){

    }

    @Override
    public void onStart(){
        super.onStart();
    }
    @Override
    public void onResume(){
        super.onResume();
    }

    @Override
    public void onItemClick(int position) {
    }
}