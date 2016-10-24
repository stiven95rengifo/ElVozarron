package com.uniquindio.android.electiva.elvozarron.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

import com.uniquindio.android.electiva.elvozarron.R;


/**
 * @author Stiven Alejandro Rengifo Ospina
 * @author Cristian Camilo Tellez
 * @version 1.0
 */
public class PortadaActivity extends AppCompatActivity implements View.OnClickListener{

  //  private DrawerLayout drawerLayout;
//    private ActionBarDrawerToggle toggle;

    private CardView cardView1;
    private CardView cardView2;
    private CardView cardView3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ventana_principal);

        cardView1=(CardView)findViewById(R.id.cardview1);
        cardView1.setOnClickListener(this);

        cardView2=(CardView)findViewById(R.id.cardview2);
        cardView2.setOnClickListener(this);

        cardView3=(CardView)findViewById(R.id.cardview3);
        cardView3.setOnClickListener(this);




    }

    @Override
    public void onClick(View v) {


        if(v.getId()==cardView1.getId()){
            startActivity(new Intent(this,EntrenadorActivity.class));
        }else if(v.getId()==cardView2.getId()){
            startActivity(new Intent(this,ParticipanteActivity.class));
        }else{

            //Falta voation

        }

    }
}
