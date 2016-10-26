package com.uniquindio.android.electiva.elvozarron.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uniquindio.android.electiva.elvozarron.R;
import com.uniquindio.android.electiva.elvozarron.activity.EntrenadorActivity;
import com.uniquindio.android.electiva.elvozarron.util.AdaptadorDeEntrenador;

/**
 * A simple {@link Fragment} subclass.
 */
public class EntrenadorFragment extends Fragment {

    /**
     * Atributo recyclerView de la actividad {@link EntrenadorActivity}
     */
    private RecyclerView recyclerView;

    /**
     * Atributo adaptadorDeEntrenador de la actividad {@link EntrenadorActivity}
     */
    private AdaptadorDeEntrenador adaptadorDeEntrenador;

    public EntrenadorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.entrenador_fragment, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.idReciclerView);
        recyclerView.setHasFixedSize(true);

        adaptadorDeEntrenador = new AdaptadorDeEntrenador();
        recyclerView.setAdapter(adaptadorDeEntrenador);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        return view;
    }

}
