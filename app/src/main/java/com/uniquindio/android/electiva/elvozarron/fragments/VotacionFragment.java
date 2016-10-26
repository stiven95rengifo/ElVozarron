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
import com.uniquindio.android.electiva.elvozarron.vo.Participante;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class VotacionFragment extends Fragment {
    /**
     * Atributo participantes del fragmento ListaDeParticipanteFragment
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


    public VotacionFragment() {
        // Required empty public constructor
        participantes = new ArrayList<>();
        participantes.add(new Participante("1", "Stiven Ospina", 21, "Estudiante", "Con sueños de ser el mejor cantante", "https://www.youtube.com/watch?v=kffacxfA7G4"));

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.votacion_fragment, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerVotacion);
        adaptador = new AdaptadorDeParticipante(participantes);
        recyclerView.setAdapter(adaptador);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        return view;
    }

}
