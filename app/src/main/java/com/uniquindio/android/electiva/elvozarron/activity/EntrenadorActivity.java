package com.uniquindio.android.electiva.elvozarron.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.uniquindio.android.electiva.elvozarron.R;
import com.uniquindio.android.electiva.elvozarron.vo.Entrenador;

import java.util.ArrayList;

/**
 * @author Stiven Alejandro Rengifo Ospina
 * @author Cristian Camilo Tellez
 * @version 1.0
 */

public class EntrenadorActivity extends AppCompatActivity {


    private ArrayList<Entrenador>entrenadores;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entrenador_activity);
    }
}
