package com.uniquindio.android.electiva.elvozarron.util;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uniquindio.android.electiva.elvozarron.R;
import com.uniquindio.android.electiva.elvozarron.vo.Entrenador;

import java.util.ArrayList;

/**
 * Created by Stiven on 17/10/2016.
 *
 * @author Stiven Alejandro Rengifo Ospina
 * @author Cristian Camilo Tellez
 * @version 1.0
 *          Esta adaptador referencia a los items con objetos del tipo {@link AdaptadorViewHolder}
 */
public class AdaptadorDeEntrenador extends RecyclerView.Adapter<AdaptadorDeEntrenador.AdaptadorViewHolder> {

    /**
     * Atributo entrenadores del AdaptadorDeEntrenador
     */
    private ArrayList<Entrenador> entrenadores;

    /**
     * Constructor de la clase AdaptadorDeEntrenador
     */
    public AdaptadorDeEntrenador() {
        entrenadores = new ArrayList<>();
        entrenadores.add(new Entrenador("1", "Adele", "Femenino", R.drawable.adele_lanscape, R.string.detalles_adele));
        entrenadores.add(new Entrenador("2", "Jhonny Rivera", "Masculino", R.drawable.rivera_lanscape, R.string.detalles_jhonny));
        entrenadores.add(new Entrenador("3", "Rihana", "Femenino", R.drawable.rihanna_lanscape, R.string.detalles_rihana));
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
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.entrenador_cardview, parent, false);
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
        holder.actualizarEntrenador(entrenadores.get(position));
    }

    /**
     * Este metodo permite obtener el tamaño de la lista
     *
     * @return el tamaño de la lista
     */
    @Override
    public int getItemCount() {
        return entrenadores.size();
    }
//---------------------------> Clase AdaptadorViewHolder

    /**
     * Clase AdaptadorViewHolder
     * Esta clase representa un Item de la lista de entrenadores el cual
     * almacena las referencias de los views dentro del layout con propósitos de acceso rápido.
     */
    public static class AdaptadorViewHolder extends RecyclerView.ViewHolder {

        //Atributos de un entrenador en el CardView
        /**
         * Atributo nombreEntrenador de la clase {@link AdaptadorDeEntrenador}
         */
        private TextView txtNombre;

        /**
         * Atribut txtDescripcion de la clase AdaptadorViewHolder
         */
        private TextView txtDescripcion;

        /**
         * Atributo imagen de la clase AdaptadorViewHolder
         */
        private ImageView imagen;


        //Atributos para la animacion ----->
        /**
         * Atributo descripcion de la clase AdaptadorViewHolder
         */
        LinearLayout linearGroupDetalles;

        /**
         * Atributo imagenExpandir de la clase AdaptadorViewHolder
         */
        ImageView imagenExpandir;

        /**
         * Atributo banner de la clase AdaptadorViewHolder
         */
        LinearLayout linearDescripcion;

        /**
         * Constructor
         *
         * @param itemView es el item del cardview el cual va inflar sus campos
         */
        public AdaptadorViewHolder(final View itemView) {
            super(itemView);
            txtNombre = (TextView) itemView.findViewById(R.id.idNombreEntrenador);
            txtDescripcion = (TextView) itemView.findViewById(R.id.descripcion);
            imagen = (ImageView) itemView.findViewById(R.id.idImagenEntrenador);

            //Esta layout contiene el evento
            linearGroupDetalles = (LinearLayout) itemView.findViewById(R.id.linearGroupDetalles);
            imagenExpandir = (ImageView) itemView.findViewById(R.id.imagenExpandir);
            linearDescripcion = (LinearLayout) itemView.findViewById(R.id.linearDescripcion);
            linearGroupDetalles.setOnClickListener(new View.OnClickListener() {
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

            if (linearDescripcion.getVisibility() == View.GONE) {
                Animacion.expand(linearDescripcion, 250);
                imagenExpandir.setImageResource(R.drawable.ic_menos);
                rotate(-180.0f);
            } else {
                Animacion.collapse(linearDescripcion, 250);
                imagenExpandir.setImageResource(R.drawable.ic_menos);
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
            imagenExpandir.startAnimation(animation);
        }


        /**
         * Este metodo permite actualizar los campos de un item del CardView entrenador_cardview
         *
         * @param entrenador al cual va ser actualizado sus campos en el cardview
         */
        public void actualizarEntrenador(Entrenador entrenador) {
            txtNombre.setText(entrenador.getNombre());
            txtDescripcion.setText(entrenador.getDescripcion());
            imagen.setImageResource(entrenador.getFoto());
        }
    }


    public void mostrarLog(String donde, String mensaje) {
        Log.i(donde, mensaje);
    }
}
