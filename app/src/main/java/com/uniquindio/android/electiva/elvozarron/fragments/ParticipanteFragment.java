package com.uniquindio.android.electiva.elvozarron.fragments;


import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.uniquindio.android.electiva.elvozarron.R;
import com.uniquindio.android.electiva.elvozarron.util.AdaptadorDeParticipante;
import com.uniquindio.android.electiva.elvozarron.util.ConexionServicioWeb;
import com.uniquindio.android.electiva.elvozarron.util.Utilidades;
import com.uniquindio.android.electiva.elvozarron.vo.Participante;

import java.util.ArrayList;

/**
 * Fragmento el cual contiene un recycler view para mostrar la lista de participantes
 *
 * @author Stiven Alejandro Rengifo Ospina
 * @author Cristian Camilo Tellez
 * @version 1.0
 */
public class ParticipanteFragment extends Fragment implements AdaptadorDeParticipante.OnClickVotacionParticipante {

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
     * Atributo buscarParticipante del fragmento editTextBuscar
     */
    private TextInputEditText editTextBuscar;

    /**
     * Atributo buscar del fragmento ParticipanteFragment
     */
    private ImageButton buscar;

    /**
     * .
     * Atributo listenerOnClick del ParticipanteFragment
     */
    private static AdaptadorDeParticipante.OnClickVotacionParticipante listenerOnClick;


    /**
     * Atributo listener del fragmento ParticipanteFragment
     */
    public ParticipanteFragment() {

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


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

        View view = inflater.inflate(R.layout.participante_fragment, container, false);
        Log.i("ENTRO", "OnActivityCreated");

        participantes = new ArrayList<>();
        //HiloSecundario hiloSecundario = new HiloSecundario(this.getContext());
        // hiloSecundario.execute(Utilidades.LISTAR);
        Participante participante = new Participante("1", "stiven rengifo", 21, "Estudiante", 1, "https://www.youtube.com/watch?v=cRzc1PVehlg");
        participantes.add(participante);


//        Bundle bundle = this.getArguments();
//
//
//        Participante participante = (Participante) bundle.getParcelable("key_participante");
//        participantes.add(participante);
//        Log.i("participante", participante.getNombre());
//        Log.i("participante", participante.getId());

//
//        editTextBuscar = (TextInputEditText) view.findViewById(R.id.buscarParticipante);
//        buscar = (ImageButton) view.findViewById(R.id.imageBuscar);
//        buscar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                int cant = 0;
//                if (editTextBuscar.getText().toString().trim().equals("")) {
//                    Toast.makeText(v.getContext(), "El campo buscar no debe estar vacio", Toast.LENGTH_SHORT).show();
//                } else {
//                    for (Participante p : participantes) {
//                        if (editTextBuscar.getText().toString().contains(p.getNombre())) {
//                            cant++;
//                        }
//                    }
//
//                    if (cant == 0) {
//                        Toast.makeText(v.getContext(), "No existe un participante con ese nombre", Toast.LENGTH_SHORT).show();
//                    } else {
//                        Toast.makeText(v.getContext(), "Hay " + cant + " con el nombre buscado", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }
//        });
//
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerParticipante);
        adaptador = new AdaptadorDeParticipante(this, participantes);
        recyclerView.setAdapter(adaptador);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        return view;
    }


    /**
     * Permite mostrar el Dialog de votacionFallida
     *
     * @param participante
     */
    @Override
    public void onClickVotacion(Participante participante) {

        if (verificarConexion(getContext())) {
            Bundle bundle = new Bundle();
            bundle.putParcelable("key_participante", participante);
            VotacionExitosaFragment votacionExitosaFragment = new VotacionExitosaFragment();
            votacionExitosaFragment.setArguments(bundle);
            votacionExitosaFragment.show(getFragmentManager(), "key_votacion");
        } else {
            VotacionFallidaDialogFragment votacionFallidaDialogFragment = new VotacionFallidaDialogFragment();
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

    //-------------------------------------------------------------------->HiloSecundario

    /**
     * Este hilo permite obtener la lista de participantes del servicio RESTful
     */
    public class HiloSecundario extends AsyncTask<Integer, Integer, Integer> {

        private ProgressDialog progress;
        private Context context;
        private Participante participante;


        /**
         * Constructor del hilo
         *
         * @param context
         */
        public HiloSecundario(Context context) {
            this.context = context;
            participante = null;

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.i("RUN", "onPreExecute");
            progress = ProgressDialog.show(context, context.getString(R.string.cargando_participantes),
                    context.getString(R.string.esperar), true);
        }

        @Override
        protected Integer doInBackground(Integer... params) {
            Log.i("RUN", "doInBackground");

            if (params[0] == Utilidades.LISTAR) {
                if (ConexionServicioWeb.getListaDeParticipantes() == null) {
                    Log.v("tamañooo", "" + "nulooooooooooooooooooooo");
                }

            }
            return params[0];
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            Log.i("RUN", "PostExecute");
            progress.dismiss();

            if (integer == Utilidades.LISTAR) {

                if (adaptador == null)
                    adaptador = new AdaptadorDeParticipante(ParticipanteFragment.this, participantes);
                recyclerView.setAdapter(adaptador);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
            }
        }
    }

    public void setParticipantes(ArrayList<Participante> participantes) {
        this.participantes = participantes;
    }
}
