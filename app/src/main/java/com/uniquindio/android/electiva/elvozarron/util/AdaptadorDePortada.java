package com.uniquindio.android.electiva.elvozarron.util;

/**
 * Created by Stiven on 21/10/2016.
 */

public class AdaptadorDePortada  {

//    /**
//     * Atributo arreglo de funciones del AdapatadoDePortada
//     */
//    private FuncionDeLaApp[] funciones;
//
//    /**
//     * Constructor del RecyclerView {@link AdaptadorDePortada}
//     *
//     * @param funciones
//     */
//    public AdaptadorDePortada(FuncionDeLaApp[] funciones) {
//        this.funciones = funciones;
//    }
//
//    /**
//     * Este metodo infla el contenido de un nuevo ítem para la lista.
//     *
//     * @param parent
//     * @param viewType
//     * @return un item de la lista adaptadorViewHolder
//     */
//    @Override
//    public AdaptadorPortadaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.portada_activity, parent, false);
//        return new AdaptadorPortadaViewHolder(itemView);
//    }
//
//    /**
//     * Metodo que permite actualizar el contenido de un item del RecyclerView
//     *
//     * @param holder
//     * @param position
//     */
//    @Override
//    public void onBindViewHolder(AdaptadorPortadaViewHolder holder, int position) {
//        holder.actualizarItem(funciones[position]);
//    }
//
//    /**
//     * Este metodo permite obtener el tamaño de la lista
//     *
//     * @return el tamaño de la lista
//     */
//    @Override
//    public int getItemCount() {
//        return funciones.length;
//    }
//
//    /**
//     * Clase AdaptadorPortadaViewHolder
//     * Esta clase representa un Item de la lista de entrenadores el cual
//     * almacena las referencias de los views dentro del layout con propósitos de acceso rápido.
//     */
//    public class AdaptadorPortadaViewHolder {
//
//        /**
//         * Atributo imagenPortada del RecyclerView.ViewHolder
//         */
//        private ImageView imagenPortada;
//
//        /**
//         * Atributo titulo del RecyclerView.ViewHolder
//         */
//        private TextView titulo;
//
//        /**
//         * Constructor del RecycleView.ViewHolder
//         *
//         * @param itemView
//         */
//        public AdaptadorPortadaViewHolder(View itemView) {
//
//            imagenPortada = (ImageView) itemView.findViewById(R.id.idImagePortada);
//            titulo = (TextView) itemView.findViewById(R.id.idTxtPortada);
//        }
//
//        /**
//         * Metodo que permite actualizar la informacion de un item del RecyclerView que tome la vista
//         *
//         * @param funcion la cual sera mostrada
//         */
//        public void actualizarItem(FuncionDeLaApp funcion) {
//            imagenPortada.setImageResource(funcion.getImagen());
//            titulo.setText(funcion.getTitulo());        }
//    }
}
