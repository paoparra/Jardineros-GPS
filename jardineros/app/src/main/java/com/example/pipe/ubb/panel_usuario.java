package com.example.pipe.ubb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Pipe on 18-06-2017.
 */

public class panel_usuario extends AppCompatActivity implements View.OnClickListener  {
    String id_string,clave_string;
    TextView informacion;
    Button modificar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.panel_usuario);
        id_string = getIntent().getExtras().getString("id");
        clave_string = getIntent().getExtras().getString("clave");
        informacion = (TextView) findViewById(R.id.informacion);
        modificar=(Button)findViewById(R.id.mdatos);
        informacion.setText("Bienvenido: "+id_string);
        modificar.setOnClickListener(this);


    }
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.mdatos:
                Intent a = new Intent(this, com.example.pipe.ubb.modificar.class);
                a.putExtra("id", id_string);
                a.putExtra("clave",clave_string);
                startActivity(a);
                                break;
        }
    }






}
