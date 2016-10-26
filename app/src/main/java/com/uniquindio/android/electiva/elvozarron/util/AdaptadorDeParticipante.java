package com.uniquindio.android.electiva.elvozarron.util;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.uniquindio.android.electiva.elvozarron.R;
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
    public AdaptadorDeParticipante(ArrayList<Participante> participantes) {
        this.participantes = participantes;

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
         * Atributo txtNombreDetalle de la clase AdaptadorViewHolder
         */
        private TextView txtNombreDtalle;
        /**
         * Atributo txtEdad de la clase AdaptadorViewHolder
         */
        private TextView txtEdad;


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
         * Atributo descropcion de la clase AdaptadorDeParticipante
         */
        private LinearLayout descripcion;

        /**
         * Atributo edad de la clase  AdaptadorDeParticipante
         */
        private TextView edad;

        /**
         * Atributo nombreEntrenador de la clase  AdaptadorDeParticipante
         */
        private TextView nombreEntrenador;

        /**
         * Atributo relacionU de la clase  AdaptadorDeParticipante
         */
        private TextView relacionU;

        /**
         * Atributo estado de la clase  AdaptadorDeParticipante
         */
        private TextView estado;


        /**
         * Atributo btnUrl de la clase AdaptadorViewHolder
         */
        private Button btnUrl;

        /**
         * Constructor de la clase AdaptadorViewHolder
         *
         * @param itemView vista la cual sera inflada
         */
        public AdaptadorViewHolder(final View itemView) {
            super(itemView);
            imagen = (ImageView) itemView.findViewById(R.id.imagenParticipante);
            txtNombre = (TextView) itemView.findViewById(R.id.txtNombre);
            edad = (TextView) itemView.findViewById(R.id.edad);
            nombreEntrenador = (TextView) itemView.findViewById(R.id.nombreEntrenador);
            relacionU = (TextView) itemView.findViewById(R.id.relacionUniversidad);
            estado = (TextView) itemView.findViewById(R.id.estado);
            btnUrl = (Button) itemView.findViewById(R.id.boton_url);
            btnUrl.setOnClickListener(this);
            txtNumVotacion = (TextView) itemView.findViewById(R.id.txtNumVotacion);
            imageButton = (ImageButton) itemView.findViewById(R.id.imageVotar);
            descripcion = (LinearLayout) itemView.findViewById(R.id.linearDescripcion);
            detalles = (ImageButton) itemView.findViewById(R.id.imagenExpandir);
            detalles.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    expandir(itemView);
                }
            });
        }

        /**
         * Metodo que permite expandir la descripcion de cada personaje
         *
         * @param view
         */
        public void expandir(View view) {

            if (descripcion.getVisibility() == View.GONE) {
                Animacion.expand(descripcion, 250);
                detalles.setImageResource(R.drawable.ic_menos_participante);
                rotate(-180.0f);
            } else {
                Animacion.collapse(descripcion, 250);
                detalles.setImageResource(R.drawable.ic_detalles);
                rotate(180.0f);
            }
        }

        /**
         * Metodo que permite animar los componentes con respecto a un angulo
         *
         * @param angle
         */
        private void rotate(float angle) {
            Animation animation = new RotateAnimation(0.0f, angle, Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f);
            animation.setFillAfter(true);
            animation.setDuration(250);
            detalles.startAnimation(animation);
        }

        /**
         * Permite actualizar el item de la lista de participantes en el RecyclerView
         *
         * @param participante el cual se agregaran sus datos al CardView
         */
        public void actualizarItemParticipante(Participante participante) {
            txtNombre.setText(participante.getNombre());
            edad.setText(String.valueOf(participante.getEdad()));
            nombreEntrenador.setText("Adele");
            relacionU.setText(participante.getTipoParticipante());
            estado.setText(participante.isEstado() ? "Activo" : "Eliminado");
            txtNumVotacion.setText(String.valueOf(participante.getNumeroDeVotos()));

        }

        /**
         * Metodo onClick el cual permite el evento de mostrar el video de un participante
         *
         * @param v
         */
        @Override
        public void onClick(View v) {
            if (v.getId() == btnUrl.getId()) {
                Toast.makeText(v.getContext(), "Mostrar video", Toast.LENGTH_SHORT).show();
                // startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(participante.getUrl())));
            }
        }
    }
}
