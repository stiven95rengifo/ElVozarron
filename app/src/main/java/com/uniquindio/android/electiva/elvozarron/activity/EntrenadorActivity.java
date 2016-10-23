package com.uniquindio.android.electiva.elvozarron.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.uniquindio.android.electiva.elvozarron.R;
import com.uniquindio.android.electiva.elvozarron.util.AdaptadorDeEntrenador;

/**
 * Actividad EntrenadorActivity la cual permite mostrar los entrenadores por medio del recyclerview
 *
 * @author Stiven Alejandro Rengifo Ospina
 * @author Cristian Camilo Tellez
 * @version 1.0
 */
public class EntrenadorActivity extends AppCompatActivity {

    /**
     * Atributo recyclerView de la actividad {@link EntrenadorActivity}
     */
    private RecyclerView recyclerView;

    /**
     * Atributo adaptadorDeEntrenador de la actividad {@link EntrenadorActivity}
     */
    private AdaptadorDeEntrenador adaptadorDeEntrenador;

    /**
     * Metodo Callbacks que permite instanciar los atributos de la actividad
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entrenador_activity);

        recyclerView = (RecyclerView) findViewById(R.id.idReciclerView);
        recyclerView.setHasFixedSize(true);

        adaptadorDeEntrenador = new AdaptadorDeEntrenador();
        recyclerView.setAdapter(adaptadorDeEntrenador);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }
}
