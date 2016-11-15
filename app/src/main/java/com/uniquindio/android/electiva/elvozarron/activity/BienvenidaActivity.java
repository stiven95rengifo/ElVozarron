package com.uniquindio.android.electiva.elvozarron.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Actividad BienvenidaActivity, la cual permite mostrar una ventana de espera por 5 milisegundos
 *
 * @author Stiven Alejandro Rengifo Ospina
 * @author Cristian Camilo Tellez
 * @version 1.0
 */
public class BienvenidaActivity extends AppCompatActivity {

    /**
     * Metodo del Callback de la actividad
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent=new Intent(this,PortadaActivity.class);
         startActivity(intent);
        finish();
    }
}
