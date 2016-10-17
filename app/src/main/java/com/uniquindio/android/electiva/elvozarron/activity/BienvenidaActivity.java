package com.uniquindio.android.electiva.elvozarron.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import com.uniquindio.android.electiva.elvozarron.R;

/**
 * Created by Stiven on 17/10/2016.
 *
 * @author Stiven Alejandro Rengifo Ospina
 * @author Cristian Camilo Tellez
 * @version 1.0
 */
public class BienvenidaActivity extends AppCompatActivity {

    /**
     * Atributo progressBar de la clase BienvenidaActivity
     */
    private ProgressBar progressBar;
    /**
     * Atributo progreso de la clase BienvenidaActivity
     */
    private int progreso;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bienvenida_activity);

        //Inicializar atributos de la clase
        progressBar = (ProgressBar) findViewById(R.id.idProgressBar);
        progreso = 0;
        iniciarProgreso(progreso);
    }

    /**
     * Metodo que permite animar un ProgressBar
     *
     * @param progreso sera lo que avance el ProgressBar
     */
    private void iniciarProgreso(final int progreso) {

        if (progreso == 110) {
            finish();
        } else {

            progressBar.setProgress(progreso);
            //Hilo el cual va ir modificando el progreso del ProgressBar
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    iniciarProgreso(progreso + 10);
                }
            });
            thread.start();
        }
    }
}
