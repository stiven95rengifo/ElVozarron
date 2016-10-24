package com.uniquindio.android.electiva.elvozarron.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.uniquindio.android.electiva.elvozarron.R;
import com.uniquindio.android.electiva.elvozarron.fragments.ListaParticipantesFragment;
import com.uniquindio.android.electiva.elvozarron.vo.Participante;

import java.util.ArrayList;

/**
 * Actividad Participante la cual permite mostrar los participantes por medio de un Fragmento
 *
 * @author Stiven Alejandro Rengifo Ospina
 * @author Cristian Camilo Tellez
 * @version 1.0
 */
public class ParticipanteActivity extends AppCompatActivity implements ListaParticipantesFragment.OnParticipanteSeleccionadoListener {

    /**
     * Atributo participantes de la actividad ParticipanteActivity
     */
    private ArrayList<Participante> participantes;

    /**
     * Callback para crear la create la actividad
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.participante_activity);
        participantes = new ArrayList<>();

        participantes.add(new Participante("1", "Stiven Ospina", 21, "Estudiante", "Con sueños de ser el mejor cantante", "https://www.youtube.com/watch?v=kffacxfA7G4"));

        //Transaccion crear
        ListaParticipantesFragment listaParticipantesFragment = (ListaParticipantesFragment) getSupportFragmentManager().findFragmentById(R.id.fragmento_lista_participantes);
        listaParticipantesFragment.setParticipantes(participantes);

    }

    @Override
    public void onParticipanteSeleccionado(int posicion) {
        Intent intent = new Intent(this, DetalleParticipanteActivity.class);
        intent.putExtra("Par", participantes.get(posicion));
        startActivity(intent);
    }
}
