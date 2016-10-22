package com.uniquindio.android.electiva.elvozarron.vo;

/**
 * Created by Stiven on 17/10/2016.
 * Clase que contiene los atributos de una FuncionDeLaApp
 *
 * @author Stiven Alejandro Rengifo Ospina
 * @author Cristian Camilo Tellez
 * @version 1.0
 */
public class FuncionDeLaApp {

    /**
     * Atributo imagen de la clase {@link FuncionDeLaApp}
     */
    private int imagen;

    /**
     * Atributo titulo de la clase {@link FuncionDeLaApp}
     */
    private String titulo;


    /**
     * Constructor de la clase FuncionDeLaApp
     *
     * @param imagen que tendra del objeto FuncionDeLaApp
     * @param titulo que tendra del objeto FuncionDeLaApp
     */
    public FuncionDeLaApp(int imagen, String titulo) {
        this.imagen = imagen;
        this.titulo = titulo;
    }

    /**
     * Metodo que permite obtener la imagen de la {@link FuncionDeLaApp}
     *
     * @return imagen de la {@link FuncionDeLaApp}
     */
    public int getImagen() {
        return imagen;
    }

    /**
     * Metodo que permite modificar la imagen de la {@link FuncionDeLaApp}
     *
     * @param imagen la cual va ser modificada
     */
    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    /**
     * Metodo que permite obtener el titulo de la {@link FuncionDeLaApp}
     *
     * @return titulo de la {@link FuncionDeLaApp}
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Metodo que permite modificar el titulo de la {@link FuncionDeLaApp}
     *
     * @return titulo de la {@link FuncionDeLaApp}
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
