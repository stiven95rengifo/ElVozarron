package com.uniquindio.android.electiva.elvozarron.fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.uniquindio.android.electiva.elvozarron.R;
import com.uniquindio.android.electiva.elvozarron.vo.Participante;

/**
 * Fragmento DetalleDeParticipanteFragment el cual permite mostrar los participantes en una lista, por medio del recyclerview
 *
 * @author Stiven Alejandro Rengifo Ospina
 * @author Cristian Camilo Tellez
 * @version 1.0
 */
public class DetalleDeParticipanteFragment extends Fragment implements View.OnClickListener {

    /**
     * Atributo participante del fragmento DetalleDeParticipanteFragment
     */
    private Participante participante;
    /**
     * Atributo imagen del fragmento DetalleDeParticipanteFragment
     */
    private ImageView imagen;

    /**
     * Atributo nombre del fragmento DetalleDeParticipanteFragment
     */
    private TextView nombre;

    /**
     * Atributo edad del fragmento DetalleDeParticipanteFragment
     */
    private TextView edad;

    /**
     * Atributo imagenEntrenador del fragmento DetalleDeParticipanteFragment
     */
    private ImageView imagenEntrenador;

    /**
     * Atributo nombreEntrenador del fragmento DetalleDeParticipanteFragment
     */
    private TextView nombreEntrenador;

    /**
     * Atributo tipoParticipante del fragmento DetalleDeParticipanteFragment
     */
    private TextView tipoParticipante;

    /**
     * Atributo estado del fragmento DetalleDeParticipanteFragment
     */
    private TextView estado;

    /**
     * Atributo url del fragmento DetalleDeParticipanteFragment
     */
    private Button btnUrl;


    /**
     * Constructor del fragmento
     */
    public DetalleDeParticipanteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.detalle_de_participante_fragment, container, false);
    }

    /**
     * Permite recibir un personaje e inicializar los atributos del fragmentos
     *
     * @param participante
     */
    public void recibirParticipante(Participante participante) {
        this.participante = participante;
        imagen = (ImageView) getView().findViewById(R.id.imagenPrincipal);
        nombre = (TextView) getView().findViewById(R.id.nombreDetalle);
        nombre.setText(participante.getNombre());
        edad = (TextView) getView().findViewById(R.id.edadDetale);
        edad.setText(String.valueOf(participante.getEdad()));
        imagenEntrenador = (ImageView) getView().findViewById(R.id.imageEntrenadorParticipante);
        //imagenEntrenador.setImageResource(participante.getEntrenador().getFoto());
        nombreEntrenador = (TextView) getView().findViewById(R.id.nombreEntrenadorParticipante);
        //nombreEntrenador.setText(participante.getEntrenador().getNombre());
        tipoParticipante = (TextView) getView().findViewById(R.id.txtRelacionUniversidad);
        tipoParticipante.setText(participante.getTipoParticipante());
        estado = (TextView) getView().findViewById(R.id.txtEstadoParticipante);
        estado.setText(participante.retornarEstado());
        btnUrl = (Button) getView().findViewById(R.id.btnAbrirUrl);
        btnUrl.setOnClickListener(this);
    }


    /**
     * Permite mostrar el video de un participante en youtube
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(participante.getUrl())));
    }
}
