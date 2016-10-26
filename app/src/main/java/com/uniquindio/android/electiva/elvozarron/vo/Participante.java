package com.uniquindio.android.electiva.elvozarron.vo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Stiven on 17/10/2016.
 * Clase Participante con sus atributos
 *
 * @author Stiven Alejandro Rengifo Ospina
 * @author Cristian Camilo Tellez
 * @version 1.0
 */
public class Participante implements Parcelable {

    /**
     * Atributo id de la clase Participante
     */
    private String id;

    /**
     * Atributo nombre de la clase Participante
     */
    private String nombre;

        /**
     * Atributo edad de la clase Participante
     */
    private int edad;

    /**
     * Atributo entrenador de la clase Participante
     */
    private Entrenador entrenador;

    /**
     * Atributo foto de la clase Entrenador
     */
    private int foto;

    /**
     * Atributo tipoParticipante de la clase Participante
     */
    private String tipoParticipante;

    /**
     * Atributo numeroDeVotos de la clase Participante
     */
    private int numeroDeVotos;
    /**
     * Atributo url de la clase Participante
     */
    private String url;

    /**
     * Atributo estado de la clase Participante
     * <p>
     * El estado puede ser True=activo, False=Eliminado
     */
    private boolean estado;

    /**
     * Constructor de la clase Participante
     *
     * @param id               o cedula del participante
     * @param nombre           del participante
     * @param edad             del participante
     * @param tipoParticipante la relacion se hace con la universidad, puede ser estudiante, profesor, administrativo, otros
     * @param url              video en youtube de la persona cantando
     */
    public Participante(String id, String nombre, int edad, String tipoParticipante, String url) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.tipoParticipante = tipoParticipante;
        this.numeroDeVotos = 0;
        this.url = url;
        this.estado = true;
    }

    /**
     * Constructor parcelable para recuperar los datos
     *
     * @param in
     */
    protected Participante(Parcel in) {
        numeroDeVotos = 0;
        estado = true;
        readToParcel(in);
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
     * Permite obtener el id del participante
     *
     * @return id del participante
     */
    public String getId() {
        return id;
    }

    /**
     * Permite modificar el id del participante
     *
     * @param id por el cual va ser modificado el actual
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Permite obtener el nombre del participante
     *
     * @return nombre del participante
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Permite modificar el nombre del participante
     *
     * @param nombre por el cual va ser modificado el actual
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Permite obtener la edad del participante
     *
     * @return edad del participante
     */
    public int getEdad() {
        return edad;
    }

    /**
     * Permite modificar la edad del participante
     *
     * @param edad por el cual va ser modificado el actual
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }

    /**
     * Permite obtener el entrenador con el que esta relacionado el participante
     *
     * @return entrenador relacionado con el  participante
     */
    public Entrenador getEntrenador() {
        return entrenador;
    }

    /**
     * Permite modificar el entrenador con el que esta relacionado el participante
     *
     * @param entrenador relacionado con el  participante
     */
    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }

    /**
     * Permite obtener el tipo de participante
     *
     * @return edad del participante
     */
    public String getTipoParticipante() {
        return tipoParticipante;
    }

    /**
     * Permite modificar el tipo de participante
     *
     * @param tipoParticipante del participante
     */
    public void setTipoParticipante(String tipoParticipante) {
        this.tipoParticipante = tipoParticipante;
    }

    /**
     * Permite obtener el numero de votos del participante
     *
     * @return numeroDeVotos del participante
     */
    public int getNumeroDeVotos() {
        return numeroDeVotos;
    }

    /**
     * Permite modificar el numero de votos del participante
     *
     * @param numeroDeVotos del participante
     */
    public void setNumeroDeVotos(int numeroDeVotos) {
        this.numeroDeVotos = numeroDeVotos;
    }

    /**
     * Permite obtener el video del participante
     *
     * @return url o el video del participante
     */
    public String getUrl() {
        return url;
    }

    /**
     * Permite modificar el video del participante
     *
     * @param url o el video del participante
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Permite obtener estado del participante
     *
     * @return estado del participante
     */
    public boolean isEstado() {
        return estado;
    }

    /**
     * Permite modificar estado del participante
     *
     * @param estado del participante
     */
    public void setEstado(boolean estado) {
        this.estado = estado;
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
        dest.writeString(id);
        dest.writeString(nombre);
        dest.writeInt(edad);
        dest.writeInt(foto);
        dest.writeString(tipoParticipante);
        dest.writeInt(numeroDeVotos);
        dest.writeString(url);
        dest.writeString(String.valueOf(estado));
        dest.writeParcelable(entrenador, flags);
    }

    /**
     * Método para recuperar los datos de un Parcel, se deben leer en el
     * mismos que se escribieron.
     *
     * @param in Parcel con los datos a leer
     */
    public void readToParcel(Parcel in) {
        id = in.readString();
        nombre = in.readString();
        edad = in.readInt();
        foto = in.readInt();
        tipoParticipante = in.readString();
        numeroDeVotos = in.readInt();
        url = in.readString();
        estado = in.readString().equals("true") ? true : false;
        entrenador = in.readParcelable(Entrenador.class.getClassLoader());
    }

    /**
     * Es el encargado de crear el participante con base al Parcel
     * recibido, tambien es necesario para
     * enviar array para la lectura de arrays enviadas por medio del
     * parcel
     */
    public static final Creator<Participante> CREATOR = new Creator<Participante>() {
        @Override
        public Participante createFromParcel(Parcel in) {
            return new Participante(in);
        }

        @Override
        public Participante[] newArray(int size) {
            return new Participante[size];
        }
    };

    /**
     * Permite retornar el estado de un participante
     *
     * @return una cadena
     */
    public String retornarEstado() {
        return estado == true ? "Activo" : "Eliminado";
    }
}
