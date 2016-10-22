package com.uniquindio.android.electiva.elvozarron.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.uniquindio.android.electiva.elvozarron.R;
import com.uniquindio.android.electiva.elvozarron.util.AdaptadorDePortada;
import com.uniquindio.android.electiva.elvozarron.vo.FuncionDeLaApp;



/**
 * @author Stiven Alejandro Rengifo Ospina
 * @author Cristian Camilo Tellez
 * @version 1.0
 */
public class PortadaActivity extends AppCompatActivity {

    /**
     * Atributo adaptadorDeEntrenador de la actividad {@link PortadaActivity}
     */
    private AdaptadorDePortada adaptadorDePortada;

    /**
     * Atributo funcionaes de la actividad {@link PortadaActivity}
     */
    private FuncionDeLaApp [] funciones;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.portada_activity);

        funciones=new FuncionDeLaApp[2];

        //funciones[0]=new FuncionDeLaApp(R.drawable.imagen_entrenadores,"Entrenadores");
        funciones[0]=new FuncionDeLaApp(R.drawable.imagen_participantes,"Participantes");
        funciones[1]=new FuncionDeLaApp(R.drawable.imagen_votacion,"Votacion");

        adaptadorDePortada=new AdaptadorDePortada(funciones);

    }
}
