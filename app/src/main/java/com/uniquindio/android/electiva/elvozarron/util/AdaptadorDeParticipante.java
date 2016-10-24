package com.uniquindio.android.electiva.elvozarron.util;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.uniquindio.android.electiva.elvozarron.R;
import com.uniquindio.android.electiva.elvozarron.fragments.ListaParticipantesFragment;
import com.uniquindio.android.electiva.elvozarron.vo.Participante;

import java.util.ArrayList;

/**
 * Adaptador que referencia a un item de la lista participantes
 *
 * @author Stiven Alejandro Rengifo Ospina
 * @author Cristian Camilo Tellez
 * @version 1.0
 */
public class AdaptadorDeParticipante extends RecyclerView.Adapter<AdaptadorDeParticipante.AdaptadorViewHolder> {

    /**
     * Atributo participantes del AdaptadorDeParticipante
     */
    private ArrayList<Participante> participantes;


    /**
     * Atributo listener del  AdaptadorDeParticipante
     */
    private static OnClickAdaptadorParticipante listener;

    /**
     * Constructor del AdaptadorDeParticipante
     *
     * @param participantes lista de participantes
     */
    public AdaptadorDeParticipante(ArrayList<Participante> participantes, ListaParticipantesFragment listaParticipantesFragment) {
        this.participantes = participantes;
        listener = (OnClickAdaptadorParticipante) listaParticipantesFragment;
    }

    /**
     * Este metodo infla el contenido de un nuevo ítem para la lista.
     *
     * @param parent
     * @param viewType
     * @return un item de la lista adaptadorViewHolder
     */

    @Override
    public AdaptadorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.participante_cardview, parent, false);
        return new AdaptadorViewHolder(itemView);
    }

    /**
     * Realiza las modificaciones en cada item de la lista
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(AdaptadorViewHolder holder, int position) {
        holder.actualizarItemParticipante(participantes.get(position));
    }


    /**
     * Este metodo permite obtener el tamaño de la lista
     *
     * @return el tamaño de la lista
     */
    @Override
    public int getItemCount() {
        return participantes.size();
    }


    /**
     * Esta interface permite compartir el evento de un fragmento con otro
     */
    public interface OnClickAdaptadorParticipante {
        void onClickParticipante(int posicion);
    }

    /**
     * Clase AdaptadorViewHolder
     * Esta clase representa un Item de la lista de participante el cual
     * almacena las referencias de los views dentro del layout con propósitos de acceso rápido.
     */
    public static class AdaptadorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        /**
         * Atributo imagen de la clase AdaptadorViewHolder
         */
        private ImageView imagen;

        /**
         * Atributo txtNombre de la clase AdaptadorViewHolder
         */
        private TextView txtNombre;

        /**
         * Atributo numVotacion de la clase AdaptadorViewHolder
         */
        private TextView txtNumVotacion;

        /**
         * Atributo imageButton de la clase  AdaptadorDeParticipante
         */
        private ImageButton imageButton;

        /**
         * Atributo detalles de la clase  AdaptadorDeParticipante
         */
        private ImageButton detalles;

        /**
         * Constructor de la clase AdaptadorViewHolder
         *
         * @param itemView vista la cual sera inflada
         */
        public AdaptadorViewHolder(View itemView) {
            super(itemView);
            imagen = (ImageView) itemView.findViewById(R.id.imagenParticipante);
            txtNombre = (TextView) itemView.findViewById(R.id.txtNombre);
            txtNumVotacion = (TextView) itemView.findViewById(R.id.txtNumVotacion);
            imageButton=(ImageButton) itemView.findViewById(R.id.imageVotar);

            detalles=(ImageButton)itemView.findViewById(R.id.imageMas);
            detalles.setOnClickListener(this);
        }

        /**
         * Permite actualizar el item de la lista de participantes en el RecyclerView
         *
         * @param participante el cual se agregaran sus datos al CardView
         */
        public void actualizarItemParticipante(Participante participante) {

            txtNombre.setText(participante.getNombre());
            txtNumVotacion.setText(String.valueOf(participante.getNumeroDeVotos()));
        }

        /**
         * @param v
         */
        @Override
        public void onClick(View v) {
            listener.onClickParticipante(getAdapterPosition());
        }
    }

}
