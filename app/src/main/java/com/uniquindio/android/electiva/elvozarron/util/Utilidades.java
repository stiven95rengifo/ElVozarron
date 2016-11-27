package com.uniquindio.android.electiva.elvozarron.util;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;

import java.util.Locale;

/**
 * Clase Utilidades, la cual permite modficar el idioma de la app
 *
 * @author Stiven Alejandro Rengifo Ospina
 * @author Cristian Camilo Tellez
 * @version 1.0
 */
public class Utilidades {

    /**
     * Atributo MIS_PREFERENCIAS de la clase Utilidades
     */
    public final static String MIS_PREFERENCIAS = "MisPreferencias";

    /**
     * Atributo LENGUAJE_DE_PREFERENCIA de la clase Utilidades
     */
    public final static String LENGUAJE_DE_PREFERENCIA = "languaje_preferences";

    /**
     * Atributo LENGUAJE_ES de la clase Utilidades
     */
    public final static String LENGUAJE_ES = "es";

    /**
     * Atributo LENGUAJE_EN de la clase Utilidades
     */
    public final static String LENGUAJE_EN = "en";

    /**
     * Atributo URL_SERVICIO_EN de la clase Utilidades
     */
    public final static String URL_SERVICIO = "http://localhost:3000/api/participantes"; //10.0.2.2

    /**
     * Atributo LISTAR_EN de la clase Utilidades
     */
    public static final int LISTAR = 1;

    /**
     * Atributo AGREGAR de la clase Utilidades
     */
    public static final int AGREGAR= 2;

    /**
     * Atributo MODIFICAR  de la clase Utilidades
     */
    public static final int MODIFICAR = 3;

    /**
     * Atributo ELIMINAR de la clase Utilidades
     */
    public static final int ELIMINAR = 4;

    /**
     * Permite modificar el idioma de la app
     *
     * @param context desde donde se esta llamando al metodo.
     */
    public static void cambiarIdioma(Context context) {

        SharedPreferences prefs = context.getSharedPreferences(MIS_PREFERENCIAS, context.MODE_PRIVATE);
        //Seleccionamos el lenguaje de preferencia el español
        String language = prefs.getString(LENGUAJE_DE_PREFERENCIA, LENGUAJE_ES);

        if (language.equals(LENGUAJE_ES)) {
            language = LENGUAJE_EN;
        } else if (language.equals(LENGUAJE_EN)) {
            language = LENGUAJE_ES;
        }

        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(LENGUAJE_DE_PREFERENCIA, language);
        editor.commit();

        obtenerLenguaje(context);
    }

    /**
     * Permite obtener el lenguaje actual que tiene la app
     *
     * @param context desde donde se esta llamando el metodo
     */
    public static void obtenerLenguaje(Context context) {

        SharedPreferences prefs = context.getSharedPreferences(MIS_PREFERENCIAS, context.MODE_PRIVATE);
        String language = prefs.getString(LENGUAJE_DE_PREFERENCIA, LENGUAJE_ES);

        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Configuration config = new Configuration();
        config.locale = locale;
        context.getApplicationContext().getResources().updateConfiguration(config, null);
    }


//    public static void getKeyHash(Context context) {
//        try {
//            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES);
//            for (Signature signature : info.signatures) {
//                MessageDigest md = MessageDigest.getInstance("SHA");
//                md.update(signature.toByteArray());
//                String sign = Base64.encodeToString(md.digest(), Base64.DEFAULT);
//                Log.e("Mi clave HASH:", sign);
//            }
//        } catch (PackageManager.NameNotFoundException e) {
//            Log.d("prueba", "1 KeyHash Error: " + e.getMessage());
//        } catch (NoSuchAlgorithmException e) {
//            Log.d("prueba", "2 KeyHash Error: " + e.getMessage());
//        }
//    }



}
