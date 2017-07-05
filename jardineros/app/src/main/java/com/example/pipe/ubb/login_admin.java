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

public class login_admin extends AppCompatActivity implements View.OnClickListener {
    Button entrar;
    EditText seudonimo, clave;
    TextView loginmensaje;
    String seudonimoString, claveString, resultadoString;
    JSONParser jsonParser = new JSONParser();
    String LOGIN_URL = "http://34.193.208.83/jardinero/login/login_admin.php";
    String mensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_admin);
        entrar = (Button) findViewById(R.id.entrar);
        seudonimo = (EditText) findViewById(R.id.seudonimo);
        clave = (EditText) findViewById(R.id.clave);
        loginmensaje = (TextView) findViewById(R.id.loginmensaje);
        entrar.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.entrar:seudonimoString = seudonimo.getText().toString();
                claveString = clave.getText().toString();
                new login_admin.login().execute();  // ejecucion de asynctask
                break;
        }
    }

    class login extends AsyncTask<String, String, String> {

        protected void onPreExecute() {}

        @Override
        protected String doInBackground(String... params) {

            List parametros = new ArrayList();
            parametros.add(new BasicNameValuePair("seudonimo", seudonimoString));
            parametros.add(new BasicNameValuePair("clave", claveString));
            resultadoString = jsonParser.makeHttpRequest(LOGIN_URL, "POST", parametros).toString();

            try {
                JSONObject object = new JSONObject(resultadoString);
                JSONArray json_array = object.optJSONArray("respuesta");
                mensaje = json_array.getJSONObject(0).getString("mensaje"); // obtener el contenido del mensaje
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        protected void onPostExecute(String file_url) {
            if (mensaje.equals("login correcto")) {
                Intent a = new Intent(getApplicationContext(),panel_admin.class);
                a.putExtra("seudonimo", seudonimoString);
                a.putExtra("clave",claveString);
                startActivity(a);
                finish();

            } else { // login incorrecto
                loginmensaje.setText("Datos ingresados no son correctos");
            }


        }


    }

}
