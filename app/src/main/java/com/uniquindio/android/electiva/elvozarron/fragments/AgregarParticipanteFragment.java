package com.uniquindio.android.electiva.elvozarron.fragments;


import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.uniquindio.android.electiva.elvozarron.R;

import java.util.regex.Pattern;

/**
 * Fragmento AgregarParticipanteFragmento agregar un participante
 *
 * @author Stiven Alejandro Rengifo Ospina
 * @author Cristian Camilo Tellez
 * @version 1.0
 */
public class AgregarParticipanteFragment extends Fragment implements View.OnClickListener {

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
     * Contructor del fragmento
     */
    public AgregarParticipanteFragment() {
        // Required empty public constructor
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
        tilCedula = (TextInputLayout) view.findViewById(R.id.til_cedula);
        tilNombre = (TextInputLayout) view.findViewById(R.id.til_nombre);
        tilEdad = (TextInputLayout) view.findViewById(R.id.til_edad);
        tilTipo = (TextInputLayout) view.findViewById(R.id.til_tipo);
        btnAceptar = (Button) view.findViewById(R.id.boton_aceptar);
        spinner = (Spinner) view.findViewById(R.id.spinner);
        btnAceptar.setOnClickListener(this);
        btnCancelar = (Button) view.findViewById(R.id.boton_cancelar);
        btnCancelar.setOnClickListener(this);
        return view;
    }


    /**
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        if (v.getId() == btnAceptar.getId()) {
            validarDatos();
        }
    }

    /**
     * Metodo que permite validar si los datos ingresados por un participante son correctos
     */
    private void validarDatos() {
        String cedula = tilCedula.getEditText().getText().toString();
        String nombre = tilNombre.getEditText().getText().toString();
        String edad = tilEdad.getEditText().getText().toString();
        String tipo = tilTipo.getEditText().getText().toString();

        boolean a = esCedulaValido(cedula);
        boolean b = esNombreValido(nombre);
        boolean c = esEdadValido(edad);
        boolean d = esTipoValido(tipo);

        if (a && b && c && d) {
            // OK, se pasa a la siguiente acción

            Toast.makeText(getContext(), "Se guardo el registro", Toast.LENGTH_LONG).show();
            //Empezar las tranferencias entre los fragmentos
        }
    }

    /**
     * Permite validar que el campo cedula solo se escriban digitos
     *
     * @param cedula
     * @return true si es valido, false de lo contrario
     */

    private boolean esCedulaValido(String cedula) {
        Pattern patron = Pattern.compile("^[0-9]");
        if (!patron.matcher(cedula).matches() || cedula.length() > 10) {
            tilEdad.setError("Cedula inválido");
            return false;
        } else {
            tilCedula.setError(null);
        }
        return true;
    }

    /**
     * Permite validar el campo nombre, solo letras
     *
     * @param nombre
     * @return true si es valido, false de lo contrario
     */
    private boolean esNombreValido(String nombre) {
        Pattern patron = Pattern.compile("^[a-zA-Z ]+$");
        if (!patron.matcher(nombre).matches() || nombre.length() > 30) {
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
        Pattern patron = Pattern.compile("^[0-9]");
        if (!patron.matcher(edad).matches() || edad.length() > 3) {
            tilEdad.setError("Edad inválido");
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

        Pattern patron = Pattern.compile("^[0-9]");
        if (!patron.matcher(tipo).matches() || tipo.length() > 90) {
            tilTipo.setError("Relacion inválida");
            return false;
        } else {
            tilTipo.setError(null);
        }
        return true;
    }

}
