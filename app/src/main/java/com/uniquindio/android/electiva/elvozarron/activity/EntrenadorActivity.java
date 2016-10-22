package com.uniquindio.android.electiva.elvozarron.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.uniquindio.android.electiva.elvozarron.R;
import com.uniquindio.android.electiva.elvozarron.util.AdaptadorDeEntrenador;
import com.uniquindio.android.electiva.elvozarron.vo.Entrenador;

import java.util.ArrayList;

/**
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
     * Atributo entrenadores de la actividad {@link EntrenadorActivity}
     */
    private ArrayList<Entrenador> entrenadores;


    /**
     * Metodo Callbacks que permite instanciar los atributos de la actividad
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entrenador_activity);

        entrenadores = new ArrayList<>();
        entrenadores.add(new Entrenador("1", "Adele", "Femenino", R.drawable.adele));
        entrenadores.add(new Entrenador("2", "Jhonny Rivera", "Masculino", R.drawable.jhonny));
        entrenadores.add(new Entrenador("3", "Rihana", "Femenino", R.drawable.rihana));

        recyclerView = (RecyclerView) findViewById(R.id.idReciclerView);
        recyclerView.setHasFixedSize(true);

        adaptadorDeEntrenador = new AdaptadorDeEntrenador(entrenadores);
        recyclerView.setAdapter(adaptadorDeEntrenador);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }
}
