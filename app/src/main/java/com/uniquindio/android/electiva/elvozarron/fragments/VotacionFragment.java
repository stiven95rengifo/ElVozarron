package com.uniquindio.android.electiva.elvozarron.fragments;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
 * Fragmento VotacionFragmento en este si las votaciones esta activas, permitira hacer la votacion al usuario
 *
 * @author Stiven Alejandro Rengifo Ospina
 * @author Cristian Camilo Tellez
 * @version 1.0
 */
public class VotacionFragment extends Fragment implements AdaptadorDeParticipante.OnClickVotacionParticipante {

    /**
     * Atributo listenerOnClick del VotacionFragment
     */
    private static AdaptadorDeParticipante.OnClickVotacionParticipante listenerOnClick;

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

        View view = inflater.inflate(R.layout.participante_fragment, container, false);

        participantes = new ArrayList<>();
        Participante participante = new Participante("1", "stiven rengifo", 21, "Estudiante", 1, "https://www.youtube.com/watch?v=cRzc1PVehlg");
        participantes.add(participante);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerParticipante);
        adaptador = new AdaptadorDeParticipante(this, participantes);

        recyclerView.setAdapter(adaptador);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        return view;
    }


    /**
     * Metodo de la interface implementada para abrir el DialogFragment VotacionExitosaFragment
     *
     * @param participante por el cual ha sido votado
     */
    @Override
    public void onClickVotacion(Participante participante) {

        if (verificarConexion(getContext())) {
            Bundle bundle = new Bundle();
            bundle.putParcelable("key_participante", participante);

            VotacionExitosaFragment votacionExitosaFragment = new VotacionExitosaFragment();
            votacionExitosaFragment.setArguments(bundle);
            //votacionExitosaFragment.setStyle(VotacionExitosaFragment.STYLE_NO_TITLE,R.style.MiDialogo);
            votacionExitosaFragment.show(getFragmentManager(), "key_votacion");
        } else {
            VotacionFallidaDialogFragment votacionFallidaDialogFragment= new VotacionFallidaDialogFragment();
            votacionFallidaDialogFragment.show(getFragmentManager(), "Votacion Fallida");
        }
    }


    /**
     * Metodo que permite verificar la conexion a internet del dispositivo
     *
     * @param context
     * @return true si el dispositivo esta conectado, de lo contrario false.
     */
    public static boolean verificarConexion(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        if (netInfo != null && netInfo.isConnected()) {
            return true;
        }
        return false;
    }
}
