package com.uniquindio.android.electiva.elvozarron.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

import com.uniquindio.android.electiva.elvozarron.R;


/**
 * Esta actividad contiene los atributos de l a portada como los son los cardview
 *
 * @author Stiven Alejandro Rengifo Ospina
 * @author Cristian Camilo Tellez
 * @version 1.0
 */
public class PortadaActivity extends AppCompatActivity implements View.OnClickListener {
    private DrawerLayout drawerLayout;
    /**
     * Atributo cardview1 de la actividad PortadaActivity
     */
    private CardView cardView1;

    /**
     * Atributo cardview2 de la actividad PortadaActivity
     */
    private CardView cardView2;

    /**
     * Atributo cardview3 de la actividad PortadaActivity
     */
    private CardView cardView3;

    /**
     * Callbasck de la actividad, para instaciar los elementos
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.portada_activity);
        /**
         * setear el toobar como action bar
         */


        // agregarToolbar();


        cardView1 = (CardView) findViewById(R.id.cardview1);
        cardView1.setOnClickListener(this);

        cardView2 = (CardView) findViewById(R.id.cardview2);
        cardView2.setOnClickListener(this);

        cardView3 = (CardView) findViewById(R.id.cardview3);
        cardView3.setOnClickListener(this);
    }

    /**
     * Este metodo permite abrir pasar de ventanas
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        if (v.getId() == cardView1.getId()) {
            startActivity(new Intent(this, EntrenadorActivity.class));
        } else if (v.getId() == cardView2.getId()) {
            startActivity(new Intent(this, ParticipanteActivity.class));
        } else {
            //Falta voation
        }
    }

//    private void agregarToolbar() {
//       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        final ActionBar ab = getSupportActionBar();
//        if (ab != null) {
//            // Poner ícono del drawer toggle
//            ab.setHomeAsUpIndicator(R.drawable.ic_menu);
//            ab.setDisplayHomeAsUpEnabled(true);
//        }
//
//    }
}
