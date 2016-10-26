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
 * Fragmento el cual contiene un recycler view para mostrar la lista de participantes
 *
 * @author Stiven Alejandro Rengifo Ospina
 * @author Cristian Camilo Tellez
 * @version 1.0
 */
public class ParticipanteFragment extends Fragment {

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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.participante_fragment, container, false);

        Bundle bundle = this.getArguments();
        ArrayList<Entrenador> entrenadores = bundle.getParcelableArrayList("ENTRENADORES");

        for (Entrenador e : entrenadores) {
            for (Participante p : e.getParticipantes()) {
                participantes.add(p);
            }
        }


        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerParticipante);
        adaptador = new AdaptadorDeParticipante(participantes);
        recyclerView.setAdapter(adaptador);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        return view;
    }
}
