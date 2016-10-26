package com.uniquindio.android.electiva.elvozarron.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
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
 * Esta actividad contiene los atributos de l a portada como los son los cardview
 *
 * @author Stiven Alejandro Rengifo Ospina
 * @author Cristian Camilo Tellez
 * @version 1.0
 */
public class PortadaActivity extends AppCompatActivity implements AgregarParticipanteFragment.OnParticipanteAgregadoListener {

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
     * Callbasck de la actividad, para instaciar los elementos
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.portada_activity);

        agregarToolbar();
        //Lista de entrenadores
        entrenadores = new ArrayList<>();
        entrenadores.add(new Entrenador("1", "Adele", "Femenino", R.drawable.adele_lanscape, R.string.detalles_adele));
        entrenadores.add(new Entrenador("2", "Jhonny Rivera", "Masculino", R.drawable.rivera_lanscape, R.string.detalles_jhonny));
        entrenadores.add(new Entrenador("3", "Rihana", "Femenino", R.drawable.rihanna_lanscape, R.string.detalles_rihana));

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            prepararDrawer(navigationView);
            // Seleccionar item por defecto
            seleccionarItem(navigationView.getMenu().getItem(0));
        }
    }

    /**
     * Este metodo permite agreagar el toolbar a la actividad
     */
    private void agregarToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //Seteo el toolbar a la actividad
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            // Poner ícono del drawer toggle
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    /**
     * Permite inflar el navigation menu en la portada
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nav_menu, menu);
        return true;
    }

    /**
     * Metodo que permite obtener del menu Navegation el item seleccionado
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
     * @param navigationView
     */
    private void prepararDrawer(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        seleccionarItem(menuItem);
                        drawerLayout.closeDrawers();
                        return true;
                    }
                });
    }


    /**
     * Este metodo permite segun un item seleccionador hacer una transaccion de creacion para el fragmento
     *
     * @param itemDrawer
     */
    private void seleccionarItem(MenuItem itemDrawer) {
        Fragment fragmento = null;
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaccion = fragmentManager.beginTransaction();

        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("ENTRENADORES", entrenadores);

        switch (itemDrawer.getItemId()) {
            case R.id.menu_portada:
                fragmento = new InicioFragment();
                fragmento.setArguments(bundle);
                break;

            case R.id.menu_entrenadores:
                fragmento = new EntrenadorFragment();
                fragmento.setArguments(bundle);
                break;

            case R.id.menu_participantes:
                fragmento = new ParticipanteFragment();
                fragmento.setArguments(bundle);
                break;

            case R.id.menu_agregar:
                fragmento = new AgregarParticipanteFragment();
                fragmento.setArguments(bundle);
                break;

            case R.id.menu_votacion:
                fragmento = new VotacionFragment();
                fragmento.setArguments(bundle);
                break;
        }
        if (fragmento != null) {
            transaccion.replace(R.id.contenedor_principal, fragmento);
            transaccion.addToBackStack(null);
            //Hago commit a la transaccion
            transaccion.commit();
        }
        // Setea título actual
        setTitle(itemDrawer.getTitle());
    }


    /**
     * Metodo que la actividad implmenta para comunicarse con el fragmento AgregarParticipanteFragment
     *
     * @param entrenadores
     */
    @Override
    public void onParticipanteAgregado(ArrayList<Entrenador> entrenadores) {
        this.entrenadores = entrenadores;
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaccion = fragmentManager.beginTransaction();

        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("ENTRENADORES", entrenadores);
        Fragment fragmento = new ParticipanteFragment();
        fragmento.setArguments(bundle);

        transaccion.replace(R.id.contenedor_principal, fragmento);
        transaccion.addToBackStack(null);
        //Hago commit a la transaccion
        transaccion.commit();

    }
}
