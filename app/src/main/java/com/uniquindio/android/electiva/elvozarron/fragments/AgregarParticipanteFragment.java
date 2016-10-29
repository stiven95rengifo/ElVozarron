package com.uniquindio.android.electiva.elvozarron.fragments;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.uniquindio.android.electiva.elvozarron.R;
import com.uniquindio.android.electiva.elvozarron.vo.Entrenador;
import com.uniquindio.android.electiva.elvozarron.vo.Participante;

import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Fragmento AgregarParticipanteFragmento  el cual permite agregar un participante
 *
 * @author Stiven Alejandro Rengifo Ospina
 * @author Cristian Camilo Tellez
 * @version 1.0
 */
public class AgregarParticipanteFragment extends Fragment implements View.OnClickListener {

    /**
     * Atributo entrenadores del framento
     */
    private ArrayList<Entrenador> entrenadores;

    /**
     * Atributo posicion del fragmento
     */
    private int posicion;

    /**
     * Atributo tilCedula del fragmento
     */
    private TextInputLayout tilCedula;

    /**
     * Atributo tilNombre del fragmento
     */
    private TextInputLayout tilNombre;

    /**
     * Atributo tilEdad del fragmento
     */
    private TextInputLayout tilEdad;

    /**
     * Atributo tilTipo del fragmento
     */
    private TextInputLayout tilTipo;


    /**
     * Atributo tilUrlo del fragmento
     */
    private TextInputLayout tilUrl;

    /**
     * Atributo btnAceptar del fragmento
     */
    private Button btnAceptar;

    /**
     * Atributo btnCancelar del fragmento
     */
    private Button btnCancelar;

    /**
     * Atributo spinner del fragmento
     */
    private Spinner spinner;

    /**
     * Atributo listener del fragmento
     */
    private OnParticipanteAgregadoListener listener;

    /**
     * Contructor del fragmento
     */
    public AgregarParticipanteFragment() {
        // Required empty public constructor
        posicion = 0;
    }

    /**
     * Metodo Callback el cual permite instanciar y retornar la vista del fragmento
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return la vista, con los sus atributos
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.agregar_participante_fragment, container, false);
        Bundle bundle = getArguments();
        entrenadores = bundle.getParcelableArrayList("ENTRENADORES");

        tilCedula = (TextInputLayout) view.findViewById(R.id.til_cedula);
        tilNombre = (TextInputLayout) view.findViewById(R.id.til_nombre);
        tilEdad = (TextInputLayout) view.findViewById(R.id.til_edad);
        tilTipo = (TextInputLayout) view.findViewById(R.id.til_tipo);
        tilUrl = (TextInputLayout) view.findViewById(R.id.til_url);

        btnAceptar = (Button) view.findViewById(R.id.boton_aceptar);
        spinner = (Spinner) view.findViewById(R.id.spinner);

        //Spinner
        String[] nombres = {entrenadores.get(0).getNombre(), entrenadores.get(1).getNombre(), entrenadores.get(2).getNombre()};
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, nombres);

        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adaptador);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                posicion = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getContext(), "Debe seleccionar algun entrenador", Toast.LENGTH_SHORT).show();
            }
        });

        btnAceptar.setOnClickListener(this);
        btnCancelar = (Button) view.findViewById(R.id.boton_cancelar);
        btnCancelar.setOnClickListener(this);
        return view;
    }


    /**
     * Callbaks que es llamado cuando una actividad es asocidad al fragmento
     *
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity;
        if (context instanceof Activity) {
            activity = (Activity) context;
            try {
                listener = (OnParticipanteAgregadoListener) activity;
            } catch (ClassCastException e) {
                throw new ClassCastException(activity.toString() + " Debe implementar la interfaz OnPersonajeSeleccionadoListener");
            }
        }
    }

    /**
     * Metodo onClicl, para el cual permite almanacenar un participante
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        if (v.getId() == btnAceptar.getId()) {

            String cedula = tilCedula.getEditText().getText().toString();
            String nombre = tilNombre.getEditText().getText().toString();
            String edad = tilEdad.getEditText().getText().toString();
            String tipo = tilTipo.getEditText().getText().toString();
            String url = tilUrl.getEditText().getText().toString();

            if (cedula.equals("") || nombre.equals("") || edad.equals("") || tipo.equals("") || url.equals("")) {
                Toast.makeText(getContext(), "No deben existir campos vacios", Toast.LENGTH_SHORT).show();

            } else {
                validarDatos(cedula, nombre, edad, tipo, url);
            }
        }

        if (v.getId() == btnCancelar.getId()) {
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("ENTRENADORES", entrenadores);

            Fragment fragmentoInicio = new InicioFragment();
            fragmentoInicio.setArguments(bundle);

            FragmentTransaction transaccion = getFragmentManager().beginTransaction();
            transaccion.replace(R.id.contenedor_principal, fragmentoInicio);
            transaccion.addToBackStack(null);
            //Hago commit a la transaccion
            transaccion.commit();
        }
    }

    /**
     * Metodo que permite validar si los datos ingresados por un participante son correctos
     */
    private void validarDatos(String cedula, String nombre, String edad, String tipo, String url) {

        boolean a = esCedulaValido(cedula);
        boolean b = esNombreValido(nombre);
        boolean c = esEdadValido(edad);
        boolean d = esTipoValido(tipo);
        //  boolean e = esUrlValido(url);

        if (a && b && c && d) {

            Participante participante = new Participante(cedula, nombre, Integer.parseInt(edad), tipo, url);
            participante.setEntrenador(entrenadores.get(posicion));
            if (entrenadores.get(posicion).agregarParticipante(participante)) {
                Toast.makeText(getContext(), "Participante Registrado", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getContext(), "Ya existe un participante con este id", Toast.LENGTH_LONG).show();
            }
        }
    }

    /**
     * Permite validar que el campo cedula solo digitos
     *
     * @param cedula
     * @return true si es valido, false de lo contrario
     */

    private boolean esCedulaValido(String cedula) {
        Pattern patron = Pattern.compile("[0-9]*");
        if (!patron.matcher(cedula).matches() || cedula.length() > 10) {
            tilEdad.setError("Cedula inválida");
            return false;
        } else {
            tilCedula.setError(null);
        }
        return true;
    }

    /**
     * Permite validar el campo nombre, solo caracteres
     *
     * @param nombre
     * @return true si es valido, false de lo contrario
     */
    private boolean esNombreValido(String nombre) {
        Pattern patron = Pattern.compile("^[a-zA-Z ]*$");
        if (!patron.matcher(nombre).matches() || nombre.length() > 50) {
            tilNombre.setError("Nombre inválido");
            return false;
        } else {
            tilNombre.setError(null);
        }
        return true;
    }

    /**
     * Permite validar el campo edad, solo digitos
     *
     * @param edad del participante
     * @return true si es valido, false de lo contrario
     */
    private boolean esEdadValido(String edad) {
        Pattern patron = Pattern.compile("[0-9]*");
        if (!patron.matcher(edad).matches() || Integer.parseInt(edad) > 105) {
            tilEdad.setError("Edad inválida");
            return false;
        } else {
            tilEdad.setError(null);
        }
        return true;
    }

    /**
     * Permite verificar si el campo tipo esta correcto
     *
     * @param tipo
     * @return true si es valido, false de lo contrario
     */
    private boolean esTipoValido(String tipo) {
        Pattern patron = Pattern.compile("[a-zA-Z]*");
        if (!patron.matcher(tipo).matches() || tipo.length() > 100) {
            tilTipo.setError("Relacion inválida");
            return false;
        } else {
            tilTipo.setError(null);
        }
        return true;
    }


    /**
     * Este metodo permite validar si una URL es correcta
     *
     * @param url
     * @return true si es valido, false de lo contrario
     */
    private boolean esUrlValido(String url) {

        Pattern patron = Pattern.compile("");
        if (!patron.matcher(url).matches()) {
            tilUrl.setError("URL Invalido");
            return false;
        } else {
            tilUrl.setError(null);
        }
        return true;
    }

    /**
     * Interface la cual esta implementada por la actividad Portada, para agregar un participante con ayuda de esta
     */
    public interface OnParticipanteAgregadoListener {
        void onParticipanteAgregado(ArrayList<Entrenador> entrenadores);
    }
}