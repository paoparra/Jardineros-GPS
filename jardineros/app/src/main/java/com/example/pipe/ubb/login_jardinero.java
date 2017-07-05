package com.example.pipe.ubb;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class login_jardinero extends AppCompatActivity implements View.OnClickListener {
    Button entrar;
    Button btnregistrar;
    EditText id, clave;
    TextView loginmensaje;
    String id_string, clave_string, resultado;
    JSONParser jsonParser = new JSONParser();
    String LOGIN_URL = "http://34.193.208.83/jardinero/login/login_jardinero.php";
    String mensaje;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_jardinero);
        entrar = (Button) findViewById(R.id.entrar);
        btnregistrar = (Button) findViewById(R.id.btnregistrar);
        id = (EditText) findViewById(R.id.id);
        clave = (EditText) findViewById(R.id.clave);
        loginmensaje = (TextView) findViewById(R.id.loginmensaje);
        entrar.setOnClickListener(this);
        btnregistrar.setOnClickListener(this);


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.

    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.entrar:id_string = id.getText().toString();
                            clave_string = clave.getText().toString();
                            new login().execute();  // ejecucion de asynctask
                            break;
            case R.id.btnregistrar: Intent a = new Intent(this, RegistrarUsuario.class);
                                startActivity(a);
                                break;
        }

    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */


    class login extends AsyncTask<String, String, String> {

        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(String... params) {

            List parametros = new ArrayList();
            parametros.add(new BasicNameValuePair("id", id_string));
            parametros.add(new BasicNameValuePair("clave", clave_string));
            resultado = jsonParser.makeHttpRequest(LOGIN_URL, "POST",
                    parametros).toString();

            try {
                JSONObject object = new JSONObject(resultado);
                JSONArray json_array = object.optJSONArray("respuesta");
                mensaje = json_array.getJSONObject(0).getString("mensaje"); // obtener el contenido del mensaje
            } catch (JSONException e) {
                e.printStackTrace();
            }


            return null;
        }

        protected void onPostExecute(String file_url) {
            if (mensaje.equals("login correcto")) {
                Intent a = new Intent(getApplicationContext(),panel_jardinero.class);
                a.putExtra("id", id_string);
                a.putExtra("clave",clave_string);

                startActivity(a);
                finish();

            } else { // login incorrecto
                loginmensaje.setText(mensaje);
            }


        }


    }

}
