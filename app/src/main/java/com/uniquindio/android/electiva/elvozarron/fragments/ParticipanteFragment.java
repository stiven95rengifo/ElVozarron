package com.uniquindio.android.electiva.elvozarron.fragments;


import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.uniquindio.android.electiva.elvozarron.R;
import com.uniquindio.android.electiva.elvozarron.util.AdaptadorDeParticipante;
import com.uniquindio.android.electiva.elvozarron.vo.Entrenador;
import com.uniquindio.android.electiva.elvozarron.vo.Participante;

import java.util.ArrayList;

/**
 * Fragmento el cual contiene un recycler view para mostrar la lista de participantes
 *
 * @author Stiven Alejandro Rengifo Ospina
 * @author Cristian Camilo Tellez
 * @version 1.0
 */
public class ParticipanteFragment extends Fragment implements View.OnClickListener, AdaptadorDeParticipante.OnClickVotacionParticipante {

    /**
     * Atributo participantes del fragmento ParticipanteFragment
     */
    private ArrayList<Participante> participantes;

    /**
     * Atributo adaptador del fragmento ParticipanteFragment
     */
    private AdaptadorDeParticipante adaptador;

    /**
     * Atributo recyclerView del fragmento ParticipanteFragment
     */
    private RecyclerView recyclerView;

    /**
     * Atributo buscarParticipante del fragmento editTextBuscar
     */
    private TextInputEditText editTextBuscar;

    /**
     * Atributo buscar del fragmento ParticipanteFragment
     */
    private ImageButton buscar;

    /**
     * .
     * Atributo listenerOnClick del ParticipanteFragment
     */
    private static AdaptadorDeParticipante.OnClickVotacionParticipante listenerOnClick;

    /**
     * Atributo listener del fragmento ParticipanteFragment
     */
    public ParticipanteFragment() {
        participantes = new ArrayList<>();
    }

    /**
     * Metodo Callback del fragmento, el cual permite instanciar sus atributos y devolver la vista
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.participante_fragment, container, false);
        Bundle bundle = this.getArguments();
        ArrayList<Entrenador> entrenadores = bundle.getParcelableArrayList("ENTRENADORES");


        //Agregamos a la lista general, todo los participantes que contienen los entrenadores
        for (Entrenador e : entrenadores) {
            for (Participante p : e.getParticipantes()) {
                participantes.add(p);
            }
        }

        editTextBuscar = (TextInputEditText) view.findViewById(R.id.buscarParticipante);
        buscar = (ImageButton) view.findViewById(R.id.imageBuscar);
        buscar.setOnClickListener(this);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerParticipante);
        adaptador = new AdaptadorDeParticipante(this, participantes);
        recyclerView.setAdapter(adaptador);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        return view;
    }

    /**
     * Metodo para buscar un participante de la lista
     *
     * @param v
     */
    @Override
    public void onClick(View v) {

        String pb = editTextBuscar.getText().toString();

        for (Participante p : participantes) {

        }
    }

    /**
     * Permite mostrar el Dialog de votacionFallida
     *
     * @param v
     */
    @Override
    public void onClickVotacion(View v) {

            new VotacionFallidaDialogFragment().show(getFragmentManager(), "Votacion Fallida");

    }
}
