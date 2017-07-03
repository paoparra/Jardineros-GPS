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

import static com.example.pipe.ubb.R.id.csesion;

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
    private Button VReclamar;
    private Button Solicitar;
    String opcion="";

    String  LOGIN_URL3="http://34.193.208.83/jardinero/ver.php";
    JSONParser jsonParser = new JSONParser();

    Button csesion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.panel_usuario);
        id_string = getIntent().getExtras().getString("id"); // obtenemos el id de la ventana anterior
        clave_string = getIntent().getExtras().getString("clave"); // obtenemos la clave de la ventana anterior
        informacion = (TextView) findViewById(R.id.informacion);
        modificar=(Button)findViewById(R.id.mdatos); // encontramos el boton modificar en el xml por id
        csesion=(Button)findViewById(R.id.csesion); // encontramos el boton cerrar sesion en el xml por id
        informacion.setText("Bienvenido: "+id_string);


        Puntuar=(Button)findViewById(R.id.evaluarJardinero);
        Reclamar=(Button)findViewById(R.id.reclamos);
        Solicitar=(Button)findViewById(R.id.solicitarJardinero);
        VReclamar=(Button)findViewById(R.id.Vreclamos);

        modificar.setOnClickListener(this); // boton modificar activado
        csesion.setOnClickListener(this); // boton se cierre de sesion activado
        Puntuar.setOnClickListener(this);
        Reclamar.setOnClickListener(this);
        Solicitar.setOnClickListener(this);
        VReclamar.setOnClickListener(this);
    }
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.mdatos:
                Intent a = new Intent(this, modificar.class);
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

            case R.id.Vreclamos:
                opcion="ListaReclamos";
                new panel_usuario.mostrar().execute();
                break;

            case R.id.csesion:
                Intent b = new Intent(this,UBB.class);
                finish();
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
            c.putExtra("usuario",id_string);
            startActivity(c);

            Log.d("mensaje","hola: "+valores);


        }
    }



}
