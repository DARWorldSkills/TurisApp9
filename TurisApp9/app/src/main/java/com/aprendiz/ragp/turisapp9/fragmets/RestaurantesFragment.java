package com.aprendiz.ragp.turisapp9.fragmets;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aprendiz.ragp.turisapp9.Detalle;
import com.aprendiz.ragp.turisapp9.Mapas.MapsRestaurantes;
import com.aprendiz.ragp.turisapp9.MenuT;
import com.aprendiz.ragp.turisapp9.R;
import com.aprendiz.ragp.turisapp9.Splash;
import com.aprendiz.ragp.turisapp9.models.AdapterT;
import com.aprendiz.ragp.turisapp9.models.Lugares;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RestaurantesFragment extends Fragment {

    View view;
    RecyclerView recyclerView;
    int position;
    int modo=1;
    int item;
    FloatingActionButton btnLugares;
    List<Lugares> lugaresList;
    public RestaurantesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_restaurantes, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        btnLugares = view.findViewById(R.id.btnLugares);
        btnLugares.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MapsRestaurantes.lugaresList=lugaresList;
                Intent intent = new Intent(getContext(),MapsRestaurantes.class);
                startActivity(intent);
            }
        });
        inputAdaper();
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        if (position== Surface.ROTATION_0 || position == Surface.ROTATION_180) {
            inflater.inflate(R.menu.cambiar, menu);
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_cambiar) {
            switch (modo){
                case 1:
                    modo=2;
                    inputAdaper();
                    item.setIcon(getActivity().getDrawable(R.drawable.lista));
                    break;
                case 2:
                    modo=1;
                    item.setIcon(getActivity().getDrawable(R.drawable.cuarto));
                    inputAdaper();
                    break;

            }

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void inputAdaper() {
        position = getActivity().getWindowManager().getDefaultDisplay().getRotation();
        AdapterT adapterT;
        lugaresList = Splash.lugaresList.subList(17,24);
        if (position== Surface.ROTATION_0 || position == Surface.ROTATION_180){
            if (modo==1){
                item = R.layout.item_list;

                adapterT = new AdapterT(lugaresList,getContext(),item);
                recyclerView.setAdapter(adapterT);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
                recyclerView.setHasFixedSize(true);
                adapterT.setMlistener(new AdapterT.OnItemClickListener() {
                    @Override
                    public void itemClick(int position) {
                        MenuT.lugares = lugaresList.get(position);
                        Intent intent = new Intent(getContext(), Detalle.class);
                        startActivity(intent);
                    }
                });

            }else {
                item = R.layout.item_grid;

                adapterT = new AdapterT(lugaresList,getContext(),item);
                recyclerView.setAdapter(adapterT);
                recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false));
                recyclerView.setHasFixedSize(true);
                adapterT.setMlistener(new AdapterT.OnItemClickListener() {
                    @Override
                    public void itemClick(int position) {
                        MenuT.lugares = lugaresList.get(position);
                        Intent intent = new Intent(getContext(), Detalle.class);
                        startActivity(intent);
                    }
                });



            }
        }else {
            item = R.layout.item_land;

            adapterT = new AdapterT(lugaresList,getContext(),item);
            recyclerView.setAdapter(adapterT);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
            recyclerView.setHasFixedSize(true);
            adapterT.setMlistener(new AdapterT.OnItemClickListener() {
                @Override
                public void itemClick(int position) {
                    MenuT.lugares = lugaresList.get(position);
                    TextView txtDescripcionLand = view.findViewById(R.id.txtDescripcionLand);
                    ImageView imgLand = view.findViewById(R.id.imgLand);
                    txtDescripcionLand.setText(lugaresList.get(position).getDescripcion());
                    Glide.with(getContext()).load(MenuT.lugares.getUrl()).into(imgLand);
                }
            });
        }

    }


}
