package com.example.pipe.ubb;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class panel_admin extends AppCompatActivity implements View.OnClickListener{

    String seudonimoString, claveString;
    TextView informacion;
    Button mostrarEliminarUsuario, mostrarEliminarJardinero, csesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.panel_admin);
        seudonimoString = getIntent().getExtras().getString("seudonimo"); // obtenemos el id de la ventana anterior
        claveString = getIntent().getExtras().getString("clave"); // obtenemos la clave de la ventana anterior
        informacion = (TextView) findViewById(R.id.informacion);

        mostrarEliminarUsuario = (Button)findViewById(R.id.btnMostrarUsuarios);
        mostrarEliminarJardinero = (Button)findViewById(R.id.btnMostrarJardineros);
        csesion = (Button)findViewById(R.id.csesion);

        informacion.setText("Bienvenido: "+seudonimoString);

        mostrarEliminarUsuario.setOnClickListener(this);
        mostrarEliminarJardinero.setOnClickListener(this);
        csesion.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnMostrarUsuarios:   Intent a = new Intent(panel_admin.this,mostrarUsuarios.class);
                                            a.putExtra("seudonimo", seudonimoString);
                                            a.putExtra("clave", claveString);
                                            startActivity(a);
                                            break;

            case R.id.btnMostrarJardineros: Intent b = new Intent(panel_admin.this,mostrarJardineros.class);
                                            b.putExtra("seudonimo", seudonimoString);
                                            b.putExtra("clave", claveString);
                                            startActivity(b);
                                            break;

            case R.id.csesion:  Intent c = new Intent(this,UBB.class);
                                finish();
                                break;
        }
    }
}
