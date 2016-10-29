package com.uniquindio.android.electiva.elvozarron.activity;

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
import android.view.MenuItem;

import com.uniquindio.android.electiva.elvozarron.R;
import com.uniquindio.android.electiva.elvozarron.fragments.AgregarParticipanteFragment;
import com.uniquindio.android.electiva.elvozarron.fragments.EntrenadorFragment;
import com.uniquindio.android.electiva.elvozarron.fragments.InicioFragment;
import com.uniquindio.android.electiva.elvozarron.fragments.ParticipanteFragment;
import com.uniquindio.android.electiva.elvozarron.fragments.VotacionFragment;
import com.uniquindio.android.electiva.elvozarron.vo.Entrenador;

import java.util.ArrayList;


/**
 * Actividad principal, permite contener los diferentes fragmentos de la App
 *
 * @author Stiven Alejandro Rengifo Ospina
 * @author Cristian Camilo Tellez
 * @version 1.0
 */
public class PortadaActivity extends AppCompatActivity implements AgregarParticipanteFragment.OnParticipanteAgregadoListener, NavigationView.OnNavigationItemSelectedListener {

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
     * Constructor por defecto
     */
    public PortadaActivity() {
        entrenadores = new ArrayList<>();

        entrenadores.add(new Entrenador("1", "Adele", "Femenino", R.drawable.imagen_adele, R.string.detalles_adele));
        entrenadores.add(new Entrenador("2", "Jhonny Rivera", "Masculino", R.drawable.imagen_andy, R.string.detalles_jhonny));
        entrenadores.add(new Entrenador("3", "Rihana", "Femenino", R.drawable.imagen_rihana, R.string.detalles_rihana));
    }

    /**
     * Callback de la actividad, para instaciar los elementos
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.portada_activity);

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


    /**
     * Permite almacenar la informacion antes de destruir la actividad por el cambio de orientacion
     *
     * @param guardarEstado bundle el cual nos permite almacenar la lista de entrenadores
     */
    @Override
    public void onSaveInstanceState(Bundle guardarEstado) {
        guardarEstado.putParcelableArrayList("lista", entrenadores);
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
        entrenadores = estadoGuardado.getParcelableArrayList("lista");
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

            case R.id.menu_agregar:
                reemplazarFragmento(new AgregarParticipanteFragment());
                break;

            case R.id.menu_votacion:
                reemplazarFragmento(new VotacionFragment());
                break;
        }

        // Setea título actual
        setTitle(item.getTitle());
        drawerLayout.closeDrawers();
        return true;
    }

    /**
     * Metodo que la actividad implmenta para comunicarse con el fragmento AgregarParticipanteFragment
     *
     * @param entrenadores
     */
    @Override
    public void onParticipanteAgregado(ArrayList<Entrenador> entrenadores) {
        this.entrenadores = entrenadores;

        FragmentTransaction transaccion =  getSupportFragmentManager().beginTransaction();

        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("ENTRENADORES", entrenadores);
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
        bundle.putParcelableArrayList("ENTRENADORES", entrenadores);
        fragmento.setArguments(bundle);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contenedor_principal, fragmento)
                .addToBackStack(null)
                .commit();


    }
}
