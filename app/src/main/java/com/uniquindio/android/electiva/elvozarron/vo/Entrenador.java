package com.uniquindio.android.electiva.elvozarron.vo;

import java.util.ArrayList;

/**
 * Created by Stiven on 17/10/2016.
 * Clase que contiene los atributos del Entrenador
 *
 * @author Stiven Alejandro Rengifo Ospina
 * @author Cristian Camilo Tellez
 * @version 1.0
 */

public class Entrenador {

    /**
     * Atributo id de la clase Entrenador
     */
    private String id;
    /**
     * Atributo nombre de la clase Entrenador
     */
    private String nombre;
    /**
     * Atributo genero de la clase Entrenador
     */
    private String genero;
    /**
     * Atributo historial de la clase Entrenador
     */
    private String historial;
    /**
     * Atributo foto de la clase Entrenador
     */
    private int foto;

    /**
     * Atributo parcipantes de la clase Entrenador
     */
    private ArrayList<Participante> participantes;

    /**
     * Constructor de la clase
     *
     * @param id        del entrenador
     * @param nombre    del entrenador
     * @param genero    del entrenador
     * @param historial del entrenador
     */
    public Entrenador(String id, String nombre, String genero, String historial) {
        this.id = id;
        this.nombre = nombre;
        this.genero = genero;
        this.historial = historial;
    }


    /**
     * Metodo que permite obtener el id del Entrenador
     *
     * @return id del entrenador
     */
    public String getId() {
        return id;
    }

    /**
     * Metodo que permite Modificar el id del Entrenador
     *
     * @param id del entrenador
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Metodo que permite obtener el nombre del Entrenador
     *
     * @return nombre del entrenador
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Metodo que permite modificar el nombre del Entrenador
     *
     * @param nombre del entrenador
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Metodo que permite obtener el genero del Entrenador
     *
     * @return genero del entrenador
     */
    public String getGenero() {
        return genero;
    }

    /**
     * Metodo que permite moficiar el genero del Entrenador
     *
     * @param genero del entrenador
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }

    /**
     * Metodo que permite obtener el historial del Entrenador
     *
     * @return historial del entrenador
     */
    public String getHistorial() {
        return historial;
    }

    /**
     * Metodo que permite moficiar el historial del Entrenador
     *
     * @param historial del entrenador
     */
    public void setHistorial(String historial) {
        this.historial = historial;
    }

    /**
     * Metodo que permite obtener el id de la foto del Entrenador
     *
     * @return id de la foto del entrenador
     */
    public int getFoto() {
        return foto;
    }

    /**
     * Metodo que permite modificar el id de la foto del Entrenador
     *
     * @param foto, id de la foto del entrenador
     */
    public void setFoto(int foto) {
        this.foto = foto;
    }

    /**
     * Metodo que permite obtener la lista de participantes del Entrenador
     *
     * @return lista de participantes del entrenador
     */
    public ArrayList<Participante> getParticipantes() {
        return participantes;
    }

    /**
     * Metodo que permite modificar la lista de participantes del Entrenador
     *
     * @param participantes lista de participantes del entrenador
     */
    public void setParticipantes(ArrayList<Participante> participantes) {
        this.participantes = participantes;
    }
}
