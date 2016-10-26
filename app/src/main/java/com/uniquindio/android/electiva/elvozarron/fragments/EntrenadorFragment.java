package com.uniquindio.android.electiva.elvozarron.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uniquindio.android.electiva.elvozarron.R;
import com.uniquindio.android.electiva.elvozarron.util.AdaptadorDeEntrenador;
import com.uniquindio.android.electiva.elvozarron.vo.Entrenador;

import java.util.ArrayList;

/**
 * Fragmento  EntrenadorFragmento el cual permite la lista de entrenadores
 *
 * @author Stiven Alejandro Rengifo Ospina
 * @author Cristian Camilo Tellez
 * @version 1.0
 */
public class EntrenadorFragment extends Fragment {

    /**
     * Atributo recyclerView de la actividad {@link EntrenadorFragment}
     */
    private RecyclerView recyclerView;

    /**
     * Atributo adaptadorDeEntrenador de la actividad EntrenadorFragment
     */
    private AdaptadorDeEntrenador adaptadorDeEntrenador;

    /**
     * Contructor por defecto
     */
    public EntrenadorFragment() {
        // Required empty public constructor
    }

    /**
     * Este metodo permite dibujar la interfaz para el  fragmento
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @returnla vista del fragmento
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.entrenador_fragment, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.idReciclerView);
        recyclerView.setHasFixedSize(true);

        Bundle bundle = getArguments();
        ArrayList<Entrenador> entrenadores = bundle.getParcelableArrayList("ENTRENADORES");
        adaptadorDeEntrenador = new AdaptadorDeEntrenador(entrenadores);
        recyclerView.setAdapter(adaptadorDeEntrenador);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        return view;
    }

}
