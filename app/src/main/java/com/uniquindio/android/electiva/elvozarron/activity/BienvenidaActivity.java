package com.uniquindio.android.electiva.elvozarron.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import io.fabric.sdk.android.Fabric;

/**
 * Actividad BienvenidaActivity, la cual permite mostrar una ventana de espera por 5 milisegundos
 *
 * @author Stiven Alejandro Rengifo Ospina
 * @author Cristian Camilo Tellez
 * @version 1.0
 */
public class BienvenidaActivity extends AppCompatActivity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "lctHdDflILmDyj453wzmgKanC";
    private static final String TWITTER_SECRET = "QiKjYDRgIXhY5viUqDHed4ThCZ7KBe01PWu6rKuMQkNsqZkyl4";


    /**
     * Metodo del Callback de la actividad
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));

        Intent intent=new Intent(this,PortadaActivity.class);
         startActivity(intent);
        finish();
    }
}
