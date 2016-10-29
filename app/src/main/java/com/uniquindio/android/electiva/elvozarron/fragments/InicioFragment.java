package com.uniquindio.android.electiva.elvozarron.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uniquindio.android.electiva.elvozarron.R;
import com.uniquindio.android.electiva.elvozarron.vo.Entrenador;

import java.util.ArrayList;

/**
 * Fragmento  InicioFragment el cual es menu el cual permite nevegar a las principales ventanas de la App
 *
 * @author Stiven Alejandro Rengifo Ospina
 * @author Cristian Camilo Tellez
 * @version 1.0
 */
public class InicioFragment extends Fragment implements View.OnClickListener {

    /**
     * Atributo entrenadores del fragmento InicioFragmento
     */
    private ArrayList<Entrenador> entrenadores;

    /**
     * Atributo cardView1 del fragmento InicioFragment
     */
    private CardView cardView1;

    /**
     * Atributo cardView2 del fragmento InicioFragment
     */
    private CardView cardView2;

    /**
     * Atributo cardView3 del fragmento InicioFragment
     */
    private CardView cardView3;

    /**
     * Constructor por defecto
     */
    public InicioFragment() {
        // Required empty public constructor
    }

    /**
     * Este metodo permite dibujar la interfaz para el  fragmento
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return la vista para dibujarla
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.inicio_fragment, container, false);

        //Obtengo la lista de Entrenadores con sus participantes
        Bundle bundle = getArguments();
        entrenadores = bundle.getParcelableArrayList("ENTRENADORES");

        cardView1 = (CardView) view.findViewById(R.id.cardview1);
        cardView1.setOnClickListener(this);
        cardView2 = (CardView) view.findViewById(R.id.cardview2);
        cardView2.setOnClickListener(this);
        cardView3 = (CardView) view.findViewById(R.id.cardview3);
        cardView3.setOnClickListener(this);
        return view;
    }

    /**
     * Metodo onClick el cual permite navegar por los diferentes fragmentos
     *
     * @param v la vista la cual va ser seleccionada
     */
    @Override
    public void onClick(View v) {

        Fragment fragmento = null;
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("ENTRENADORES", entrenadores);

        switch (v.getId()) {
            case R.id.cardview1:
                fragmento = new EntrenadorFragment();
                fragmento.setArguments(bundle);
                break;

            case R.id.cardview2:
                fragmento = new ParticipanteFragment();
                fragmento.setArguments(bundle);
                break;

            case R.id.cardview3:
                fragmento = new VotacionFragment();
                fragmento.setArguments(bundle);
                break;
        }

        if (fragmento != null) {
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction transaccion = fragmentManager.beginTransaction();

            transaccion.replace(R.id.contenedor_principal, fragmento);
            transaccion.addToBackStack(null);
            //Hago commit a la transaccion
            transaccion.commit();
        }
        // Setear título actual
        // setTitle(itemDrawer.getTitle());
    }

}
