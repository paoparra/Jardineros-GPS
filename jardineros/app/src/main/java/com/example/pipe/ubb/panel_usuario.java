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

import static android.R.string.no;
//import static com.example.pipe.ubb.R.id.Hora;
import static com.example.pipe.ubb.R.id.csesion;

/**
 * Created by Pipe on 18-06-2017.
 */

public class panel_usuario extends AppCompatActivity implements View.OnClickListener  {
    ArrayList correo=new ArrayList();
    ArrayList nombres=new ArrayList();
    ArrayList notas=new ArrayList();

    ArrayList HoraA=new ArrayList();
    ArrayList LugarA=new ArrayList();

    String id_string,clave_string;
    String resultado;
    String parametro;
    String nota="";
    String nombre="";
    TextView informacion;
    Button modificar;
    private Button Puntuar;
    private Button Reclamar;
    private Button VReclamar;
    private Button Solicitar;
    private Button  Agenda;
    String opcion="";
    String Estado="nada";

    String  LOGIN_URL3="http://34.193.208.83/jardinero/ver_prueba.php";
    String  LOGIN_URL4="http://34.193.208.83/jardinero/ver_agenda_usuario.php";
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
        Agenda=(Button)findViewById(R.id.Agenda);

        modificar.setOnClickListener(this); // boton modificar activado
        csesion.setOnClickListener(this); // boton se cierre de sesion activado
        Puntuar.setOnClickListener(this);
        Reclamar.setOnClickListener(this);
        Solicitar.setOnClickListener(this);
        VReclamar.setOnClickListener(this);
        Agenda.setOnClickListener(this);
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

            case R.id.Agenda:
                Estado="Aprobado";
                new panel_usuario.mostrarAgenda().execute();
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
            parametros.add(new BasicNameValuePair("Nombre",nombre));
            parametros.add(new BasicNameValuePair("Calificacion",nota));
            resultado = jsonParser.makeHttpRequest(LOGIN_URL3, "POST",
                    parametros).toString();
            JSONObject object = null;
            try {
                object = new JSONObject(resultado);
                JSONArray json_array = object.optJSONArray("respuesta");
                correo.clear();
                nombres.clear();
                notas.clear();
                for (int i = 0; i < json_array.length(); i++) {
                    correo.add(json_array.getJSONObject(i).getString("correo"));
                    nombres.add(json_array.getJSONObject(i).getString("Nombre"));
                    notas.add(json_array.getJSONObject(i).getString("Calificacion"));

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
            c.putExtra("micorreo", correo); //correos del jardinero
            c.putExtra("misnombres", nombres);
            c.putExtra("misnotas", notas);
            c.putExtra("op",opcion);
            c.putExtra("usuario",id_string);
            startActivity(c);

            Log.d("mensaje","hola: "+valores);


        }
    }


    class mostrarAgenda extends AsyncTask<String, String, String> {
        protected void onPreExecute() {

        }
        @Override
        protected String doInBackground(String... params) {
            List parametros = new ArrayList();
            parametros.add(new BasicNameValuePair("estado",Estado));
            parametros.add(new BasicNameValuePair("correo",id_string));
            resultado = jsonParser.makeHttpRequest(LOGIN_URL4, "POST",
                    parametros).toString();
            JSONObject object = null;
            try {
                object = new JSONObject(resultado);
                JSONArray json_array = object.optJSONArray("respuesta");
                correo.clear();
                HoraA.clear();
                LugarA.clear();
                for (int i = 0; i < json_array.length(); i++) {
                    correo.add(json_array.getJSONObject(i).getString("correo_jardinero"));
                    HoraA.add(json_array.getJSONObject(i).getString("Hora"));
                    LugarA.add(json_array.getJSONObject(i).getString("Lugar"));

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
            Intent c = new Intent(panel_usuario.this,Agenda_Lista.class);
            c.putExtra("micorreo", correo); //correos del jardinero
            c.putExtra("mihora", HoraA);
            c.putExtra("milugar",LugarA);
            c.putExtra("usuario",id_string);
            startActivity(c);

            Log.d("mensaje","hola: "+valores);


        }
    }

}
