package com.example.pipe.ubb;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.pipe.ubb.R.id.mAgenda;

/**
 * Created by Pipe on 18-06-2017.
 */

public class panel_jardinero extends AppCompatActivity implements View.OnClickListener  {
    ArrayList correoU=new ArrayList();
    ArrayList HoraU=new ArrayList();
    ArrayList LugarU=new ArrayList();
    ArrayList ID=new ArrayList();

    String id_string,clave_string;
    String resultado;
    String parametro;
    String Estado="Pendiente";
    TextView informacion;
    Button modificar;
    private Button mAgenda;
    private Button mSolicitud;

    String  LOGIN_URL3="http://34.193.208.83/jardinero/ver_usuario_Solicitud.php";
    JSONParser jsonParser = new JSONParser();

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

        mAgenda=(Button)findViewById(R.id.mAgenda); // boton para revisar la agenda
        mSolicitud=(Button)findViewById(R.id.mSolicitud); // boton para aceptar solicitudes


        modificar.setOnClickListener(this); // boton modificar activado
        csesion.setOnClickListener(this); // boton se cierre de sesion activado

        mAgenda.setOnClickListener(this);
        mSolicitud.setOnClickListener(this);

    }
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.mdatos:
                Intent a = new Intent(this, modificar_jardinero.class);
                a.putExtra("id", id_string);
                a.putExtra("clave",clave_string);
                startActivity(a);

                break;

            case R.id.mAgenda:
                opcion="Agenda";
                Estado="Aprobado";
                new panel_jardinero.mostrarU().execute();
                break;

            case R.id.mSolicitud:
                opcion="Solicitud";
                Estado="Pendiente";
                new panel_jardinero.mostrarU().execute();
                break;

            case R.id.csesion:
                Intent b = new Intent(this,UBB.class);
                finish();
                break;

        }
    }

    class mostrarU extends AsyncTask<String, String, String> {
        protected void onPreExecute() {

        }
        @Override
        protected String doInBackground(String... params) {

            String hora="null";
            String lugar="null";
            List parametros = new ArrayList();
            parametros.add(new BasicNameValuePair("estado",Estado));
            parametros.add(new BasicNameValuePair("correo",id_string));
            parametros.add(new BasicNameValuePair("correo_usuario",parametro));
            parametros.add(new BasicNameValuePair("Hora",hora));
            parametros.add(new BasicNameValuePair("Lugar",lugar));
            resultado = jsonParser.makeHttpRequest(LOGIN_URL3, "POST",
                    parametros).toString();
            JSONObject object = null;
            try {
                object = new JSONObject(resultado);
                JSONArray json_array = object.optJSONArray("respuesta");
                correoU.clear();
                HoraU.clear();
                LugarU.clear();
                ID.clear();
                for (int i = 0; i < json_array.length(); i++) {
                    correoU.add(json_array.getJSONObject(i).getString("correo_usuario"));
                    HoraU.add(json_array.getJSONObject(i).getString("Hora"));
                    LugarU.add(json_array.getJSONObject(i).getString("Lugar"));
                    ID.add(json_array.getJSONObject(i).getString("id_solicitud"));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        protected void onPostExecute(String file_url) {
            String valores="";
            for(int i=0;i<correoU.size();i++){
                valores=valores+correoU.get(i).toString()+" ";

            }

            Intent c = new Intent(panel_jardinero.this,UsuariosLista.class);
            c.putExtra("correoU", correoU);
            c.putExtra("horaU", HoraU);
            c.putExtra("LugarU", LugarU);

            c.putExtra("ID", ID);
            c.putExtra("op",opcion);
            c.putExtra("usuario",id_string);
            startActivity(c);

            Log.d("mensaje","hola: "+valores);


        }
    }






}
