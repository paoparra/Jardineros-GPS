package com.example.pipe.ubb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Pipe on 18-06-2017.
 */

public class panel_jardinero extends AppCompatActivity implements View.OnClickListener  {
    ArrayList correo=new ArrayList();
    String id_string,clave_string;
    String resultado;
    String parametro;
    TextView informacion;
    Button modificar;

    String opcion="";


    Button csesion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.panel_jardinero);
        id_string = getIntent().getExtras().getString("id"); // obtenemos el id de la ventana anterior
        clave_string = getIntent().getExtras().getString("clave"); // obtenemos la clave de la ventana anterior
        informacion = (TextView) findViewById(R.id.informacion);
        modificar=(Button)findViewById(R.id.mdatos); // encontramos el boton modificar en el xml por id
        csesion=(Button)findViewById(R.id.csesion); // encontramos el boton cerrar sesion en el xml por id
        informacion.setText("Bienvenido: "+id_string);




        modificar.setOnClickListener(this); // boton modificar activado
        csesion.setOnClickListener(this); // boton se cierre de sesion activado

    }
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.mdatos:
                Intent a = new Intent(this, modificar_jardinero.class);
                a.putExtra("id", id_string);
                a.putExtra("clave",clave_string);
                startActivity(a);

                break;



            case R.id.csesion:
                Intent b = new Intent(this,UBB.class);
                finish();
                break;

        }
    }






}
