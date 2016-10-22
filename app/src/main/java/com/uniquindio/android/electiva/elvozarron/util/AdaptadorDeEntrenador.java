package com.uniquindio.android.electiva.elvozarron.util;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
 *          <p>
 *          Esta adaptador referencia a los items con objetos del tipo {@link AdaptadorViewHolder}
 */
public class AdaptadorDeEntrenador extends RecyclerView.Adapter<AdaptadorDeEntrenador.AdaptadorViewHolder> {

    private ArrayList<Entrenador> entrenadores;

    /**
     * Constructor de la clase AdaptadorDeEntrenador
     *
     * @param entrenadores lista de entrenadores
     */
    public AdaptadorDeEntrenador(ArrayList<Entrenador> entrenadores) {
        this.entrenadores = entrenadores;
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
        mostrarLog("AdaptadorOncreate", "Entro 1");
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.entrenador_cardview, parent, false);
        return new AdaptadorViewHolder(itemView);
    }

    /**
     * Metodo que permite actualizar el contenido de un item del RecyclerView
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(AdaptadorViewHolder holder, int position) {
        mostrarLog("AdaptadoronBind", "Entro 2");
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

    /**
     * Clase AdaptadorViewHolder
     * Esta clase representa un Item de la lista de entrenadores el cual
     * almacena las referencias de los views dentro del layout con propósitos de acceso rápido.
     */
    public static class AdaptadorViewHolder extends RecyclerView.ViewHolder {

        //Atributo de un entrenador en el CardView
        /**
         * Atributo nombreEntrenador de la clase {@link AdaptadorDeEntrenador}
         */
        private TextView nombreEntrenador;

        /**
         * Atributo imagen de la clase {@link AdaptadorDeEntrenador}
         */
        private ImageView imagen;

        /**
         * Constructor
         *
         * @param itemView es el item del cardview el cual va inflar sus campos
         */
        public AdaptadorViewHolder(View itemView) {
            super(itemView);
            nombreEntrenador = (TextView) itemView.findViewById(R.id.idNombreEntrenador);
            imagen = (ImageView) itemView.findViewById(R.id.idImagenEntrenador);
        }

        /**
         * Este metodo permite actualizar los campos de un item del CardView entrenador_cardview
         *
         * @param entrenador al cual va ser actualizado sus campos en el cardview
         */
        public void actualizarEntrenador(Entrenador entrenador) {
            nombreEntrenador.setText(entrenador.getNombre());
            imagen.setImageResource(entrenador.getFoto());
        }
    }




    public void mostrarLog(String donde, String mensaje) {
        Log.i(donde, mensaje);
    }
}
