package com.aprendiz.ragp.turisapp9;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aprendiz.ragp.turisapp9.models.Lugares;
import com.bumptech.glide.Glide;

public class Detalle extends AppCompatActivity {
    Lugares lugares;
    TextView txtNombre, txtDescripcion;
    FloatingActionButton btnDetalle;
    ImageView imgDetalle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        inizialite();
        inputData();
        btnDetalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                Intent intent = new Intent(Detalle.this, Todos.class);
                startActivity(intent);
            }
        });

    }

    private void inizialite() {
        txtNombre = findViewById(R.id.txtNombreDetalle);
        txtDescripcion = findViewById(R.id.txtDescripcionDetalle);
        imgDetalle = findViewById(R.id.imgDetalle);
        btnDetalle = findViewById(R.id.btnDetalle);
    }

    private void inputData() {
        lugares = MenuT.lugares;
        txtNombre.setText(lugares.getNombre());
        txtDescripcion.setText(lugares.getDescripcion());
        Glide.with(this).load(lugares.getUrl()).into(imgDetalle);
    }

    @Override
    protected void onResume() {
        super.onResume();
        inizialite();
        inputData();
    }

    @Override
    protected void onStart() {
        super.onStart();
        inizialite();
        inputData();
    }
}
