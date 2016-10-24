package com.uniquindio.android.electiva.elvozarron.fragments;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
 * Fragmento ListaDeParticipanteFragment el cual permite mostrar los participantes en una lista, por medio del recyclerview
 *
 * @author Stiven Alejandro Rengifo Ospina
 * @author Cristian Camilo Tellez
 * @version 1.0
 */
public class ListaParticipantesFragment extends Fragment implements AdaptadorDeParticipante.OnClickAdaptadorParticipante {

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

    /**
     * Atributo listener del fragmento ListaParticipanteFragment
     */
    private OnParticipanteSeleccionadoListener listener;

    /**
     * Constructor de la ListaParticipantesFragment
     */
    public ListaParticipantesFragment() {
        // Required empty public constructor
    }


    /**
     * Este metodo es llamado  cuando se añade el fragmento a la actividad
     * en el se asegura de que la actividad principal implemente la interfaz OnParticipanteSeleccionadoListener
     *
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Activity activity;
        if (context instanceof Activity) {
            activity = (Activity) context;
            try {
                listener = (OnParticipanteSeleccionadoListener) activity;
            } catch (ClassCastException e) {
                throw new ClassCastException(activity.toString() + " debe implementar la interfaz " +
                        "OnPeliculaSeleccionadaListener");
            }
        }
    }

    /**
     * Permite crear el fragmento
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return la vista del fragmento
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.lista_participantes_fragment, container, false);
    }


    /**
     * es llamado cuando el método <<onCreate>> de la actividad
     * termina de ejecutarse.
     *
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView = (RecyclerView) getView().findViewById(R.id.idReciclerViewFragment);

        adaptador = new AdaptadorDeParticipante(participantes, this);
        recyclerView.setAdapter(adaptador);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }


    /**
     * Permite al dar click sobre un item del recyclerview en un fragmentos obtener su posicion
     * para ser compartido con el otro evento
     *
     * @param posicion
     */
    @Override
    public void onClickParticipante(int posicion) {
        listener.onParticipanteSeleccionado(posicion);
    }


    /**
     * Esta interface permite compartir eventos de los fragmentos con las actividad
     * se implementa en el fragmento anfitrion (ListaParticipantesFragment)
     */
    public interface OnParticipanteSeleccionadoListener {
        void onParticipanteSeleccionado(int posicion);
    }

    public ArrayList<Participante> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(ArrayList<Participante> participantes) {
        this.participantes = participantes;
    }

}
