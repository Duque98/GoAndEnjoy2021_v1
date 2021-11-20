package es.unex.goenjoy.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import es.unex.goenjoy.R;
import es.unex.goenjoy.api.ApiInterfaz;
import es.unex.goenjoy.model.Museo;

import java.util.List;

import es.unex.goenjoy.room.MuseoDao;
import es.unex.goenjoy.room.MuseoDatabase;
import retrofit2.Callback;
import retrofit2.Call;
import retrofit2.Response;

public class MuseoAdapter extends RecyclerView.Adapter<MuseoAdapter.MuseoViewHolder> implements View.OnClickListener {

    private List<Museo> items;
    private View.OnClickListener listener;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public static class MuseoViewHolder extends RecyclerView.ViewHolder {

        //context
        Context context;

        public TextView nombre;
        public TextView direccion;
        public TextView horario;


        public MuseoViewHolder(View v){
            super(v);
            context = v.getContext();
            nombre = (TextView) v.findViewById(R.id.nombre);
            direccion = (TextView) v.findViewById(R.id.direccion);
            horario = (TextView) v.findViewById(R.id.horario);
        }


    }

    public MuseoAdapter(List<Museo> items) {
        this.items = items;
        for(Museo i : items){
            Log.i(i.getTitle(), "@@");
        }
    }

    public List<Museo> getmMuseo() {
        return items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @NonNull
    @Override
    public MuseoAdapter.MuseoViewHolder onCreateViewHolder(@NonNull ViewGroup viewgroup, int i) {
        View v = LayoutInflater.from(viewgroup.getContext()).inflate(R.layout.card_view,viewgroup,false);
        v.setOnClickListener(this);
        return new MuseoAdapter.MuseoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MuseoAdapter.MuseoViewHolder viewholder, int i) {
        viewholder.nombre.setText(items.get(i).getTitle());
        viewholder.direccion.setText(items.get(i).getStreetAdress());
        viewholder.horario.setText(items.get(i).getSchedule());

    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;

    }

    @Override
    public void onClick(View v) {
        if(listener != null){
            listener.onClick(v);
        }
    }

    public void setmMuseos(List<Museo> mMuseos){
        this.items = mMuseos;
    }

    public void load(List<Museo> museos){
        this.items = museos;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }
}
