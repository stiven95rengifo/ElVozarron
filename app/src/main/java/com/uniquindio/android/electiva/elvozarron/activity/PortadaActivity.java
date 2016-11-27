package com.uniquindio.android.electiva.elvozarron.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.widget.LoginButton;
import com.uniquindio.android.electiva.elvozarron.R;
import com.uniquindio.android.electiva.elvozarron.fragments.AgregarParticipanteFragment;
import com.uniquindio.android.electiva.elvozarron.fragments.EntrenadorFragment;
import com.uniquindio.android.electiva.elvozarron.fragments.InicioFragment;
import com.uniquindio.android.electiva.elvozarron.fragments.ParticipanteFragment;
import com.uniquindio.android.electiva.elvozarron.fragments.VotacionExitosaFragment;
import com.uniquindio.android.electiva.elvozarron.fragments.VotacionFragment;
import com.uniquindio.android.electiva.elvozarron.util.Utilidades;
import com.uniquindio.android.electiva.elvozarron.vo.Entrenador;
import com.uniquindio.android.electiva.elvozarron.vo.Participante;

import java.util.ArrayList;


/**
 * Actividad principal, permite contener los diferentes fragmentos de la App
 *
 * @author Stiven Alejandro Rengifo Ospina
 * @author Cristian Camilo Tellez
 * @version 1.0
 */
public class PortadaActivity extends AppCompatActivity implements AgregarParticipanteFragment.OnParticipanteAgregadoListener, NavigationView.OnNavigationItemSelectedListener, VotacionExitosaFragment.OnBotonSeleccionadoListener {


    /**
     * Atributo de la actividad PortadaActivity
     */
    private ArrayList<Entrenador> entrenadores;

    /**
     * Atributo DraweLayout de la actividad PortadaActivity
     */
    private DrawerLayout drawerLayout;

    /**
     * Atributo navigationView de la actividad PortadaActivity
     */
    private NavigationView navigationView;

    /**
     * Atributo bandera de la actividad principal
     */
    private String bandera;

    /**
     * Atributo callbackManager de la actividad principal
     */
    private CallbackManager callbackManager;


    /**
     * Constructor por defecto
     */
    public PortadaActivity() {

     }

    /**
     * Callback de la actividad, para instaciar los elementos
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utilidades.obtenerLenguaje(this);
        setContentView(R.layout.portada_activity);
        FacebookSdk.sdkInitialize(getApplicationContext());

        //Lista de entrenadores de la aplicacion.
        entrenadores = new ArrayList<>();
        entrenadores.add(new Entrenador(R.string.id_adele, R.string.nombre_adele, "Femenino", R.drawable.imagen_adele, R.string.detalles_adele));
        entrenadores.add(new Entrenador(R.string.id_jhonny, R.string.nombre_jhnonny, "Masculino", R.drawable.imagen_andy, R.string.detalles_jhonny));
        entrenadores.add(new Entrenador(R.string.id_rihana, R.string.nombre_rihana, "Femenino", R.drawable.imagen_rihana, R.string.detalles_rihana));

        //Setea el toolbar a la actividad
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        ActionBar actionBar = getSupportActionBar();

        //Agrego el icono el ActionBar
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setDisplayHomeAsUpEnabled(true);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }


//    /**
//     * Metodo callback de la actividad PortadaActivity
//     */
//    @Override
//    protected void onPostResume() {
//        super.onPostResume();
//
//        if (!verificarConexion(this)) {
//            AlertDialog.Builder builder = new AlertDialog.Builder(this);
//            builder.setMessage(R.string.mensaje_conexion_internet)
//                    .setTitle(R.string.titulo_advertencia)
//                    .setCancelable(false)
//                    .setNegativeButton(R.string.cancelar_conexion,
//                            new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int id) {
//                                    dialog.cancel();
//                                    finish();
//                                }
//                            })
//                    .setPositiveButton(R.string.aceptar_conexion, new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//
//                            startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
//                        }
//                    });
//            AlertDialog alert = builder.create();
//            alert.show();
//        }
//    }
//
//    /**
//     * Metodo que permite verificar la conexion a internet del dispositivo
//     *
//     * @param context
//     * @return true si el dispositivo esta conectado, de lo contrario false.
//     */
//    public static boolean verificarConexion(Context context) {
//        ConnectivityManager cm =
//                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//
//        NetworkInfo netInfo = cm.getActiveNetworkInfo();
//
//        if (netInfo != null && netInfo.isConnected()) {
//            return true;
//        }
//        return false;
//    }

    /**
     * Callback el cual permite mostrar la portada o menu prinicipal al usuario
     */
    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = new Intent(this, PortadaActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        if (bandera == null) {

            Fragment fragmento = new InicioFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("key_entrenadores", entrenadores);
            fragmento.setArguments(bundle);

            getSupportFragmentManager().beginTransaction().replace(R.id.contenedor_principal, fragmento).commit();
            bandera = "true";
        }
    }

    /**
     * Permite almacenar la informacion antes de destruir la actividad por el cambio de orientacion
     *
     * @param guardarEstado bundle el cual nos permite almacenar la lista de entrenadores
     */
    @Override
    public void onSaveInstanceState(Bundle guardarEstado) {
        guardarEstado.putParcelableArrayList("key_lista", entrenadores);
        guardarEstado.putString("key_bandera", bandera);
        super.onSaveInstanceState(guardarEstado);
    }


    /**
     * Permite recuperar la informacion al momento de crear la actividad por medio del bundle almacenado anteriormente
     *
     * @param estadoGuardado bundle el cual trae la informacion almacenada
     */
    @Override
    protected void onRestoreInstanceState(Bundle estadoGuardado) {
        super.onRestoreInstanceState(estadoGuardado);
        entrenadores = estadoGuardado.getParcelableArrayList("key_lista");
        bandera = estadoGuardado.getString("key_bandera");

    }

    /**
     * Metodo que permite si el menu esta desplegado y el item seleccionado correspoden en el fragmento abierto este
     * se mantiene abierto
     *
     * @param item un item del menu
     * @return true si el menu esta abierto, de lo contrario false
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.agregarParticipante:
                reemplazarFragmento(new AgregarParticipanteFragment());
                return true;

            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Permite obtener el item seleccionado del menu y con este llamar al metodo reemplazarFragment
     *
     * @param item del menu seleccionado
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        item.setChecked(true);
        switch (item.getItemId()) {

            case R.id.menu_portada:
                reemplazarFragmento(new InicioFragment());
                break;

            case R.id.menu_entrenadores:
                reemplazarFragmento(new EntrenadorFragment());
                break;

            case R.id.menu_participantes:
                reemplazarFragmento(new ParticipanteFragment());
                break;

            case R.id.menu_votacion:
                reemplazarFragmento(new VotacionFragment());
                break;

            case R.id.menu_idioma:
                Utilidades.cambiarIdioma(this);
                Intent refresh = new Intent(this, PortadaActivity.class);
                startActivity(refresh);
                finish();
                break;
        }
        // Setea título actual
        setTitle(item.getTitle());
        drawerLayout.closeDrawers();
        return true;
    }

    /**
     * Permite inflar el overflow o mejor dicho permite mostrarlo al usuario
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_overflow, menu);
        return true;
    }

    /**
     * Metodo que la actividad implmenta para comunicarse con el fragmento AgregarParticipanteFragment
     *
     * @param participante
     */
    @Override
    public void onParticipanteAgregado(Participante participante) {

        FragmentTransaction transaccion = getSupportFragmentManager().beginTransaction();

        Bundle bundle = new Bundle();
        bundle.putParcelable("key_participante", participante);

        Fragment fragmento = new ParticipanteFragment();
        fragmento.setArguments(bundle);
        transaccion.replace(R.id.contenedor_principal, fragmento);
        transaccion.addToBackStack(null);

        //Hago commit a la transaccion
        transaccion.commit();
    }


    /**
     * Permite reemplazar el layout principal por un fragmento
     *
     * @param fragmento el cual va ser puesto encima del layout principal
     */
    public void reemplazarFragmento(Fragment fragmento) {

        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("key_entrenadores", entrenadores);
        fragmento.setArguments(bundle);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contenedor_principal, fragmento)
                .addToBackStack(null)
                .commit();
    }

    /**
     * Permite obtener el evento del boton seleccionado, ya sea el botn de facebook o Twitter
     *
     * @param v la vista, o boton el cual fue presionado
     */
    @Override
    public void onBotonSeleccionado(LoginButton v) {
        final LoginButton btnFacebook = v;
    }


    private void obtenerDatosUsuarioFacebook(Profile perfil) {

        if (perfil != null) {
            String nombre = perfil.getName();
        }
    }




    public void setEntrenadores(ArrayList<Entrenador> entrenadores) {
        this.entrenadores = entrenadores;
    }
}
