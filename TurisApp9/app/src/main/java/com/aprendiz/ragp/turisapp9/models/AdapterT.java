package com.aprendiz.ragp.turisapp9.models;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.List;

public class AdapterT extends RecyclerView.Adapter<AdapterT.Holder>{
    List<Lugares> lugaresList = new ArrayList<>();
    Context context;
    int item;
    private OnItemClickListener mlistener;
    public interface OnItemClickListener{
        void itemClick (int position);
    }
    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(item,parent,false);
        Holder holder = new Holder(view,mlistener);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.connectData(lugaresList.get(position));
    }

    @Override
    public int getItemCount() {
        return lugaresList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
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

        public void connectData(Lugares lugares){

        }
    }
}
