package com.uniquindio.android.electiva.elvozarron.util;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.uniquindio.android.electiva.elvozarron.vo.Participante;

import java.lang.reflect.Type;
import java.util.ArrayList;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpGet;
import cz.msebera.android.httpclient.impl.client.HttpClientBuilder;
import cz.msebera.android.httpclient.util.EntityUtils;

/**
 * Clase ConexionServicioWeb se encarga de obtener la lista de entrenadores desde el servicio.
 *
 * @author Stiven Alejandro Rengifo Ospina
 * @author Cristian Camilo Tellez
 * @version 1.0
 */
public class ConexionServicioWeb {

    /**
     * Se encarga de consumir el listado de participantes desde el servicio
     *
     * @return Los participantes alojados en el servicio
     */
    public static ArrayList<Participante> getListaDeParticipantes() {

        Log.i("ENTRO", "getlistadeparticipantes");

        ArrayList<Participante> participantes = new ArrayList();

        HttpClient httpClient = HttpClientBuilder.create().build();
        Log.v("ENTRO","siguio1");
        HttpGet request = new HttpGet(Utilidades.URL_SERVICIO);
        Log.v("ENTRO","siguio2");
        request.setHeader("content-type","application/json");
        Log.v("ENTRO","siguio3");

        try {

            Type listType = new TypeToken<ArrayList<Participante>>(){}.getType();
            Log.v("ENTRO","siguio4");
            GsonBuilder gsonBuilder = new GsonBuilder();
            Log.v("ENTRO","siguio5");
            HttpResponse resp = httpClient.execute(request);
            Log.v("ENTRO","siguio6");
            String respStr = EntityUtils.toString(resp.getEntity());
            Log.v("ENTRO","siguio7");
            Gson gson = gsonBuilder.create();
            Log.v("ENTRO","siguio8");
            participantes = gson.fromJson(respStr, listType);
            Log.v("ENTRO","siguio9");


//            HttpResponse resp = httpClient.execute(request);
//            String respStr = EntityUtils.toString(resp.getEntity());
//            Log.v("ENTRO","siguio5");
//            Gson gson = new Gson(); //gsonBuilder.create();
//            Type tipoListaParticipantes = new TypeToken<ArrayList<Participante>>() {
//            }.getType();
//            Log.v("ENTRO","siguio6");
//            participantes = gson.fromJson(respStr,tipoListaParticipantes);
//            Log.v("ENTRO","siguio7");

        } catch (Exception e) {
            Log.v("Listar-WebService", e.getMessage());
            return null;
        }

        return participantes;
    }
}
