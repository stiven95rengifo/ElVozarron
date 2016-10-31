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

import com.uniquindio.android.electiva.elvozarron.R;

/**
 * Este DialogFragment permite mostra al usuario que su voto  no sera posible, ya que las
 * votaciones estan cerradas
 *
 * @author Stiven Alejandro Rengifo Ospina
 * @author Cristian Camilo Tellez
 * @version 1.0
 */
public class VotacionFallidaDialogFragment extends DialogFragment implements View.OnClickListener {

    /**
     * Atributo  btnSalir del DialogFragment VotacionFallidaDialogFragment
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
        View view = getActivity().getLayoutInflater().inflate(R.layout.votacion_fallida_dialog, new LinearLayout(getActivity()), false);

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
            case R.id.imagenSalir:
                getDialog().dismiss();
                break;

        }
    }
}
