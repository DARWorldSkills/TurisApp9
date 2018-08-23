package com.aprendiz.ragp.turisapp9.models;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.aprendiz.ragp.turisapp9.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class AdapterT extends RecyclerView.Adapter<AdapterT.Holder>{
    //Declaración de varaibles
    List<Lugares> lugaresList = new ArrayList<>();
    Context context;
    int item;
    private OnItemClickListener mlistener;
    public interface OnItemClickListener{
        void itemClick (int position);
    }

    public AdapterT(List<Lugares> lugaresList, Context context, int item) {
        this.lugaresList = lugaresList;
        this.context = context;
        this.item = item;
    }

    public void setMlistener(OnItemClickListener mlistener) {
        this.mlistener = mlistener;
    }

    //Método para la creación del holder
    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(item,parent,false);
        Holder holder = new Holder(view,mlistener);
        return holder;
    }

    //Método para el llamado del método connectData
    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.connectData(lugaresList.get(position));
    }

    //Método para saber cuantos items mostar
    @Override
    public int getItemCount() {
        return lugaresList.size();
    }

    //Clase holder la cual controla los items
    public class Holder extends RecyclerView.ViewHolder {
        ImageView imgItem = itemView.findViewById(R.id.imgItem);
        TextView txtNombre = itemView.findViewById(R.id.txtNombreItem);
        public Holder(View itemView, final OnItemClickListener mlistener) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mlistener!=null){
                        int position = getLayoutPosition();
                        if (position!=RecyclerView.NO_POSITION){
                            mlistener.itemClick(position);
                        }
                    }
                }
            });
        }

        //Método para el ingreso de datos en el item del recycler
        public void connectData(Lugares lugares){
            if (item==R.layout.item_list){
                TextView txtDescripcion = itemView.findViewById(R.id.txtDescripcionItem);
                TextView txtUbicacion = itemView.findViewById(R.id.txtUbicacionItem);
                txtDescripcion.setText(lugares.getDescripcionC());
                txtUbicacion.setText(lugares.getUbicacion());
            }

            if (item==R.layout.item_grid){
                TextView txtUbicacion = itemView.findViewById(R.id.txtUbicacionItem);
                txtUbicacion.setText(lugares.getUbicacion());
            }

            txtNombre.setText(lugares.getNombre());
            Glide.with(context).load(lugares.getUrl()).into(imgItem);

        }
    }
}
