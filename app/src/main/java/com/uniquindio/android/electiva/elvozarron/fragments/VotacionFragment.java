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
public class VotacionFragment extends Fragment {

    /**
     * Atributo participantes del fragmento ParticipanteFragment
     */
    private ArrayList<Participante> participantes;

    /**
     * Atributo adaptador del fragmento ListaDeParticipanteFragment
     */
    private AdaptadorDeParticipante adaptador;

    /**
     * Atributo recyclerView del fragmento ListaDeParticipanteFragment
     */
    private RecyclerView recyclerView;

    /**
     * Contructor por defecto
     */
    public VotacionFragment() {
        // Required empty public constructor
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
        // Inflate the layout for this fragment
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
        adaptador = new AdaptadorDeParticipante(participantes);
        recyclerView.setAdapter(adaptador);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        return view;
    }
}
