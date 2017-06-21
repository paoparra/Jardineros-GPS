package com.example.pipe.ubb;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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


public class UBB extends AppCompatActivity implements View.OnClickListener {
    ArrayList nombreA=new ArrayList();
    private Button verp;
    private Button ingresarp;
    private Button registrar_usuario;
    private EditText parametrov;
    private TextView vistap;
    private Button registrar_jardinero;
    String resultado;
    String parametro;
    String message; // mensaje del server sobre insercion

    String  LOGIN_URL="http://34.193.208.83/insertar.php";
    String  LOGIN_URL2="http://34.193.208.83/ver.php";
    JSONParser jsonParser = new JSONParser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubb);
        verp=(Button)findViewById(R.id.verp);
        ingresarp=(Button)findViewById(R.id.ingresarp);
        parametrov=(EditText)findViewById(R.id.parametrov);
        vistap=(TextView)findViewById(R.id.vistap);
        registrar_usuario=(Button)findViewById(R.id.registrar_usuario);
        registrar_jardinero=(Button)findViewById(R.id.registrar_jardinero);

        verp.setOnClickListener(this);
        ingresarp.setOnClickListener(this);
        registrar_usuario.setOnClickListener(this);
        registrar_jardinero.setOnClickListener(this);




    }
    public void onClick(View v){
        switch (v.getId()){
            case R.id.verp:
                new UBB.ver().execute();
                   vistap.setText("");
                             break;
            case R.id.ingresarp:
                parametro=parametrov.getText().toString();
                new UBB.ingresar().execute();

                            break;
            case R.id.registrar_usuario:
                Intent a = new Intent(this,RegistrarUsuario.class);
                startActivity(a);
                break;
            case R.id.registrar_jardinero:
                Intent b = new Intent(this,RegistrarUsuarioJardinero.class);
                startActivity(b);
                break;
        }
    }

    class ingresar extends AsyncTask<String, String, String> {
        protected void onPreExecute() {

        }
        @Override
        protected String doInBackground(String... params) {
            List parametros = new ArrayList();
            parametros.add(new BasicNameValuePair("parametro",parametro));
            // hacer peticion por post con los parametros ingresados
            resultado = jsonParser.makeHttpRequest(LOGIN_URL, "POST",
                    parametros).toString();
            JSONObject object = null;
            try {
                object = new JSONObject(resultado);
                JSONArray json_array = object.optJSONArray("respuesta");
              message = json_array.getJSONObject(0).getString("message");
            } catch (JSONException e) {
                e.printStackTrace();
            }



            return null;
        }

        protected void onPostExecute(String file_url) {

            vistap.setText(message);
            vistap.setTextSize(15);

        }
    }

    class ver extends AsyncTask<String, String, String> {
        protected void onPreExecute() {

        }
        @Override
        protected String doInBackground(String... params) {
            List parametros = new ArrayList();
            parametros.add(new BasicNameValuePair("parametro",parametro));
            resultado = jsonParser.makeHttpRequest(LOGIN_URL2, "POST",
                    parametros).toString();
            JSONObject object = null;
            try {
                object = new JSONObject(resultado);
                JSONArray json_array = object.optJSONArray("respuesta");
                nombreA.clear();
                for (int i = 0; i < json_array.length(); i++) {
                    nombreA.add(json_array.getJSONObject(i).getString("parametro"));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }


            return null;
        }

        protected void onPostExecute(String file_url) {
            String valores="";
            for(int i=0;i<nombreA.size();i++){
                valores=valores+nombreA.get(i).toString()+"\n";
            }
            vistap.setText(valores);
        }
    }


}
