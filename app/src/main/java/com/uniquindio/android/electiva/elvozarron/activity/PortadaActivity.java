package com.uniquindio.android.electiva.elvozarron.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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


/**
 * Esta actividad contiene los atributos de l a portada como los son los cardview
 *
 * @author Stiven Alejandro Rengifo Ospina
 * @author Cristian Camilo Tellez
 * @version 1.0
 */
public class PortadaActivity extends AppCompatActivity {

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
     * Permite crear el navegationView
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
     * Metodo que permite
     *
     * @param item
     * @return
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



    private void seleccionarItem(MenuItem itemDrawer) {
        Fragment fragmentoGenerico = null;
        FragmentManager fragmentManager = getSupportFragmentManager();

        switch (itemDrawer.getItemId()) {
            case R.id.menu_portada:
                fragmentoGenerico = new InicioFragment();
                break;

            case R.id.menu_entrenadores:
                fragmentoGenerico = new EntrenadorFragment();
                break;

            case R.id.menu_participantes:
                fragmentoGenerico = new ParticipanteFragment();
                break;

            case R.id.menu_agregar:
                fragmentoGenerico = new AgregarParticipanteFragment();
                break;

            case R.id.menu_votacion:
                fragmentoGenerico = new VotacionFragment();
                break;
        }
        if (fragmentoGenerico != null) {
            fragmentManager
                    .beginTransaction().replace(R.id.contenedor_principal, fragmentoGenerico)
                    .addToBackStack(null)
                    .commit();
        }
        // Setear título actual
        setTitle(itemDrawer.getTitle());
    }

}
