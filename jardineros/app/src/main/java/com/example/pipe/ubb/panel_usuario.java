package com.example.pipe.ubb;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

/**
 * Created by Pipe on 18-06-2017.
 */

public class panel_usuario extends AppCompatActivity implements View.OnClickListener  {
    ArrayList correo=new ArrayList();
    String id_string,clave_string;
    String resultado;
    String parametro;
    TextView informacion;
    Button modificar;
    private Button Puntuar;
    private Button Reclamar;
    private Button Solicitar;
    String opcion="";

    String  LOGIN_URL3="http://34.193.208.83/jardinero/ver.php";
    JSONParser jsonParser = new JSONParser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.panel_usuario);
        id_string = getIntent().getExtras().getString("id");
        clave_string = getIntent().getExtras().getString("clave");
        informacion = (TextView) findViewById(R.id.informacion);
        modificar=(Button)findViewById(R.id.mdatos);
        informacion.setText("Bienvenido: "+id_string);

        Puntuar=(Button)findViewById(R.id.evaluarJardinero);
        Reclamar=(Button)findViewById(R.id.reclamos);
        Solicitar=(Button)findViewById(R.id.solicitarJardinero);

        modificar.setOnClickListener(this);
        Puntuar.setOnClickListener(this);
        Reclamar.setOnClickListener(this);
        Solicitar.setOnClickListener(this);

    }
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.mdatos:
                Intent a = new Intent(this, com.example.pipe.ubb.modificar.class);
                a.putExtra("id", id_string);
                a.putExtra("clave",clave_string);
                startActivity(a);
                break;

            case R.id.evaluarJardinero:
                opcion="evaluar";
                new panel_usuario.mostrar().execute();
                break;

            case R.id.reclamos:
                opcion="reclamar";
                new panel_usuario.mostrar().execute();
                break;

            case R.id.solicitarJardinero:
                opcion="solicitar";
                new panel_usuario.mostrar().execute();
                break;
        }
    }


    class mostrar extends AsyncTask<String, String, String> {
        protected void onPreExecute() {

        }
        @Override
        protected String doInBackground(String... params) {
            List parametros = new ArrayList();
            parametros.add(new BasicNameValuePair("correo",parametro));
            resultado = jsonParser.makeHttpRequest(LOGIN_URL3, "POST",
                    parametros).toString();
            JSONObject object = null;
            try {
                object = new JSONObject(resultado);
                JSONArray json_array = object.optJSONArray("respuesta");
                correo.clear();
                for (int i = 0; i < json_array.length(); i++) {
                    correo.add(json_array.getJSONObject(i).getString("correo"));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }


            return null;
        }

        protected void onPostExecute(String file_url) {
            String valores="";
            for(int i=0;i<correo.size();i++){
                valores=valores+correo.get(i).toString()+" ";

            }
            Intent c = new Intent(panel_usuario.this,JardinerosLista.class);
            c.putExtra("micorreo", correo);
            c.putExtra("op",opcion);
            startActivity(c);

            Log.d("mensaje","hola: "+valores);


        }
    }



}
