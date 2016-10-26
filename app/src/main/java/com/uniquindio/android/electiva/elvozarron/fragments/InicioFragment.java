package com.uniquindio.android.electiva.elvozarron.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uniquindio.android.electiva.elvozarron.R;

/**
 * Fragmento InicioFragment el cual permite el menu de navegacion de laApp
 *
 * @author Stiven Alejandro Rengifo Ospina
 * @author Cristian Camilo Tellez
 * @version 1.0
 */
public class InicioFragment extends Fragment implements View.OnClickListener {

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
     * Constructor
     */
    public InicioFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.inicio_fragment, container, false);
        cardView1 = (CardView) view.findViewById(R.id.cardview1);
        cardView1.setOnClickListener(this);
        cardView2 = (CardView) view.findViewById(R.id.cardview2);
        cardView2.setOnClickListener(this);
        cardView3 = (CardView) view.findViewById(R.id.cardview3);
        cardView3.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        Fragment fragmentoGenerico = null;
        FragmentManager fragmentManager = getFragmentManager();

        switch (v.getId()) {
            case R.id.cardview1:
                fragmentoGenerico = new EntrenadorFragment();
                break;

            case R.id.cardview2:
                fragmentoGenerico = new ParticipanteFragment();
                break;

            case R.id.cardview3:
                fragmentoGenerico = new VotacionFragment();
                break;
        }

        if (fragmentoGenerico != null) {
            fragmentManager
                    .beginTransaction().replace(R.id.contenedor_principal, fragmentoGenerico)
                    .commit();
        }


        // Setear título actual
       // setTitle(itemDrawer.getTitle());
    }
}
