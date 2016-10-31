package com.uniquindio.android.electiva.elvozarron.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.uniquindio.android.electiva.elvozarron.R;

/**
 * Este DialogFragment permite mostra al usuario que su voto ha sido exitoso,
 * ademas de brinda la opcion para compartir su voto en facebool o twitter
 *
 * @author Stiven Alejandro Rengifo Ospina
 * @author Cristian Camilo Tellez
 * @version 1.0
 */
public class VotacionExitosaFragment extends DialogFragment implements View.OnClickListener {

    /**
     * Interface para la comunicacion entre el DialogFragment y el fragmento
     */
    public interface OnBotonSeleccionadoListener {
        void onBotonSeleccionado(View v);
    }

    /**
     * Atributo listener del DialogFragment VotacionExitosaFragment
     */
    private static OnBotonSeleccionadoListener listener;

    /**
     * Atributo btnFacebook del DialogFragment VotacionExitosaFragment
     */
    private ImageButton btnFacebook;

    /**
     * Atributo  btnTwitter del DialogFragment VotacionExitosaFragment
     */
    private ImageButton btnTwitter;

    /**
     * Atributo  btnSalir del DialogFragment VotacionExitosaFragment
     */
    private ImageButton btnSalir;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    /**
     * Pemite mostrar el dialogFragment
     *
     * @param savedInstanceState
     * @return
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.votacion_exitosa_fragment, new LinearLayout(getActivity()), false);

        btnFacebook = (ImageButton) view.findViewById(R.id.btnFacebook);
        btnFacebook.setOnClickListener(this);
        btnTwitter = (ImageButton) view.findViewById(R.id.btnTwitter);
        btnTwitter.setOnClickListener(this);
        btnSalir = (ImageButton) view.findViewById(R.id.imagenSalir);
        btnSalir.setOnClickListener(this);

        Dialog builder = new Dialog(getActivity());
        builder.setContentView(view);
        return builder;
    }


    /**
     * Permite realizar el evento segun el id del boton
     *
     * @param v vista la cual se va producir el evento
     */
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnFacebook:
                Toast.makeText(v.getContext(), "Compartir con facebook", Toast.LENGTH_SHORT).show();
                break;

            case R.id.btnTwitter:
                Toast.makeText(v.getContext(), "Compartir con Twitter", Toast.LENGTH_SHORT).show();
                break;

            case R.id.imagenSalir:
                getDialog().dismiss();
                break;

        }
    }
}
