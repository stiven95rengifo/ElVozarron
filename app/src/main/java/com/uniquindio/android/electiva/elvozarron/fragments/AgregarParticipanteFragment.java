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
     * Atributo spinnerRelacion del fragmento agregarParticipante
     */
    private Spinner spinnerRelacion;

    /**
     * Atributo listener del fragmento
     */
    private OnParticipanteAgregadoListener listener;

    /**
     * Atributo posicion del fragmento
     */
    private int posicion;

    /**
     * Atributo relacion del fragmento
     */
    private String relacion;

    /**
     * Contructor del fragmento
     */
    public AgregarParticipanteFragment() {

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

        View view = inflater.inflate(R.layout.agregar_participante_fragment, container, false);
        posicion = 0;

        //Lista de entrenadores de la aplicacion
        Bundle bundle = getArguments();
        entrenadores = bundle.getParcelableArrayList("key_entrenadores");

        //Inicializo las variables
        tilCedula = (TextInputLayout) view.findViewById(R.id.til_cedula);
        tilNombre = (TextInputLayout) view.findViewById(R.id.til_nombre);
        tilEdad = (TextInputLayout) view.findViewById(R.id.til_edad);
        tilUrl = (TextInputLayout) view.findViewById(R.id.til_url);

        //Permite inicializar los spinner
        inicializarSpinner(view);

        //Botones
        btnAceptar = (Button) view.findViewById(R.id.boton_aceptar);
        btnAceptar.setOnClickListener(this);
        btnCancelar = (Button) view.findViewById(R.id.boton_cancelar);
        btnCancelar.setOnClickListener(this);
        return view;
    }


    /**
     * Permite inicializar y mostrar el spinner en el fragmento
     *
     * @param view vista la cual va ser donde se mostraran los spinne
     */
    private void inicializarSpinner(View view) {

        //Spinner nombre de los entrenadores
        spinner = (Spinner) view.findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adaptadorEntrenadores = ArrayAdapter.createFromResource(getContext(),
                R.array.nombre_entrenadores, android.R.layout.simple_spinner_item);

        spinner.setAdapter(adaptadorEntrenadores);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                posicion = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getContext(), R.string.mensaje_alerta, Toast.LENGTH_SHORT).show();
            }
        });

        //Spinner de relaciones con la Universidad
        spinnerRelacion = (Spinner) view.findViewById(R.id.spinnerRelacion);
        ArrayAdapter<CharSequence> adaptadorRelaciones = ArrayAdapter.createFromResource(getContext(),
                R.array.relaciones, android.R.layout.simple_spinner_item);

        spinnerRelacion.setAdapter(adaptadorRelaciones);
        spinnerRelacion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                relacion = String.valueOf(parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getContext(), R.string.mensaje_alertaU, Toast.LENGTH_SHORT).show();
            }
        });
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

        //Boton el cual permite agregar un participante
        if (v.getId() == btnAceptar.getId()) {
            if (tilCedula.getEditText().getText().toString().equals("") ||
                    tilNombre.getEditText().getText().toString().equals("") ||
                    tilEdad.getEditText().getText().toString().equals("") ||
                    tilUrl.getEditText().getText().toString().equals("")) {

                Toast.makeText(getContext(), R.string.mensaje1, Toast.LENGTH_SHORT).show();

            } else {
                agregarParticipante();
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
     * Permite validar si los datos ingresados por un participante son correctos
     */
    private void agregarParticipante() {

        if (esCedulaValido(tilCedula.getEditText().getText().toString())
                && esNombreValido(tilNombre.getEditText().getText().toString())
                && esEdadValido(tilEdad.getEditText().getText().toString())) {

            Participante participante = new Participante(tilCedula.getEditText().getText().toString(),
                    tilNombre.getEditText().getText().toString(), Integer.parseInt(tilEdad.getEditText().getText().toString()),
                    relacion, entrenadores.get(posicion).getId(), tilUrl.getEditText().getText().toString());

            Toast.makeText(getContext(), R.string.mensaje_felicitaciones, Toast.LENGTH_LONG).show();
            listener.onParticipanteAgregado(participante);
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
        Pattern patron = Pattern.compile("[a-zA-Z ]*$");
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
     * Este metodo permite validar si una URL es correcta
     *
     * @param url
     * @return true si es valido, false de lo contrario
     */
//    private boolean esUrlValido(String url) {
//        if (URLUtil.isValidUrl(url)) {
//            Uri uri = Uri.parse(url);
//            if (url.startsWith("https://m.youtube.com/") || url.startsWith("https://www.youtube.com/")) {
//                tilUrl.setError(null);
//                return true;
//            }
//        }
//        tilUrl.setError("URL Invalido");
//        return false;
//    }


    /**
     * Interface la cual esta implementada por la actividad Portada, para agregar un participante con ayuda de esta
     */
    public interface OnParticipanteAgregadoListener {
        void onParticipanteAgregado(Participante participante);
    }
}