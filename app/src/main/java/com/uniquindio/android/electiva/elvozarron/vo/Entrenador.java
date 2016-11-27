package com.uniquindio.android.electiva.elvozarron.vo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Stiven on 17/10/2016.
 * Clase que contiene los atributos del Entrenador
 *
 * @author Stiven Alejandro Rengifo Ospina
 * @author Cristian Camilo Tellez
 * @version 1.0
 */

public class Entrenador implements Parcelable {

    /**
     * Atributo id de la clase Entrenador
     */
    private int id;
    /**
     * Atributo nombre de la clase Entrenador
     */
    private int nombre;
    /**
     * Atributo genero de la clase Entrenador
     */
    private String genero;

    /**
     * Atributo foto de la clase Entrenador
     */
    private int foto;

    /**
     * Atributo descripcion de la clase Entrenador
     */
    private int descripcion;


    /**
     * Constructor de la clase
     *
     * @param id     del entrenador
     * @param nombre del entrenador
     * @param genero del entrenador
     */
    public Entrenador(int id, int nombre, String genero, int imagen, int descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.genero = genero;
        this.foto = imagen;
        this.descripcion = descripcion;
    }


    protected Entrenador(Parcel in) {
        reatToParcel(in);
    }


    /**
     * Metodo que permite obtener el id del Entrenador
     *
     * @return id del entrenador
     */
    public int getId() {
        return id;
    }

    /**
     * Metodo que permite Modificar el id del Entrenador
     *
     * @param id del entrenador
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Metodo que permite obtener el nombre del Entrenador
     *
     * @return nombre del entrenador
     */
    public int getNombre() {
        return nombre;
    }

    /**
     * Metodo que permite modificar el nombre del Entrenador
     *
     * @param nombre del entrenador
     */
    public void setNombre(int nombre) {
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
     * Metodo que permite obtener la descripcion del Entrenador
     *
     * @return descripcion del entrenador
     */
    public int getDescripcion() {
        return descripcion;
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
     * * Se usa cuando existen parcelables hijos
     *
     * @return un entero
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Metodo para guardar el objeto en un parcelable
     *
     * @param dest
     * @param flags
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(nombre);
        dest.writeString(genero);
        dest.writeInt(foto);
        dest.writeInt(descripcion);
    }

    /**
     * Método para recuperar los datos de un Parcel, se deben leer en el
     * mismos que se escribieron.
     *
     * @param in Parcel con los datos a leer
     */
    public void reatToParcel(Parcel in) {
        id = in.readInt();
        nombre = in.readInt();
        genero = in.readString();
        foto = in.readInt();
        descripcion = in.readInt();
    }


    /**
     * Es el encargado de crear el Entrenador con base al Parcel
     * recibido, tambien es necesario para
     * enviar array para la lectura de arrays enviadas por medio del
     * parcel
     */
    public static final Creator<Entrenador> CREATOR = new Creator<Entrenador>() {
        @Override
        public Entrenador createFromParcel(Parcel in) {
            return new Entrenador(in);
        }

        @Override
        public Entrenador[] newArray(int size) {
            return new Entrenador[size];
        }
    };

//    /**
//     * Metodo que permite agregar un participante a un entrenador
//     *
//     * @param participante
//     * @return
//     */
//    public boolean agregarParticipante(Participante participante) {
//
//        for (Participante p : participantes) {
//            if (p.getId().equals(participante.getId())) {
//                return false;
//            }
//        }
//        participantes.add(participante);
//        return true;
//    }


}
