package com.uniquindio.android.electiva.elvozarron.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uniquindio.android.electiva.elvozarron.R;
import com.uniquindio.android.electiva.elvozarron.util.AdaptadorDeParticipante;
import com.uniquindio.android.electiva.elvozarron.vo.Entrenador;
import com.uniquindio.android.electiva.elvozarron.vo.Participante;

import java.util.ArrayList;

/**
 * Fragmento VotacionFragmento en este si las votaciones esta activas, permitira hacer la votacion al usuario
 *
 * @author Stiven Alejandro Rengifo Ospina
 * @author Cristian Camilo Tellez
 * @version 1.0
 */
public class VotacionFragment extends Fragment implements AdaptadorDeParticipante.OnClickVotacionParticipante {

    /**
     * .
     * Atributo listenerOnClick del VotacionFragment
     */
    private static AdaptadorDeParticipante.OnClickVotacionParticipante listenerOnClick;


    private static OnEstadoVotanteListener onEstadoVotanteListener;


    public interface OnEstadoVotanteListener {
        void onEstadoVotante(String estado);
    }


    /**
     * Atributo participantes del fragmento VotacionFragment
     */
    private ArrayList<Participante> participantes;

    /**
     * Atributo adaptador del fragmento VotacionFragment
     */
    private AdaptadorDeParticipante adaptador;

    /**
     * Atributo recyclerView del fragmento VotacionFragment
     */
    private RecyclerView recyclerView;

    /**
     * Atributo estadoVotante del fragmento
     */
    private String estadoVotante;

    /**
     * Contructor por defecto
     */
    public VotacionFragment() {
        participantes = new ArrayList<>();

    }


    /**
     * Metodo Callback del fragmento
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.votacion_fragment, container, false);

        //Obtengo la lista de Entrenadores con sus participantes
        Bundle bundle = getArguments();
        ArrayList<Entrenador> entrenadores = bundle.getParcelableArrayList("ENTRENADORES");

        for (Entrenador e : entrenadores) {
            for (Participante p : e.getParticipantes()) {
                participantes.add(p);
            }
        }

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerVotacion);
        adaptador = new AdaptadorDeParticipante(this, participantes, "votacion");

        recyclerView.setAdapter(adaptador);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        return view;
    }


    /**
     * Metodo de la interface implementada para abrir el DialogFragment VotacionExitosaFragment
     *
     * @param v
     */
    @Override
    public void onClickVotacion(View v) {
        new VotacionExitosaFragment().show(getFragmentManager(), "Votacion");
    }
}
