package com.example.pipe.ubb;

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

public class modificar extends AppCompatActivity implements View.OnClickListener {
    TextView textclave;
    EditText eclave1,eclave2,enombre,eapellido,etelefono;
    Button mclave,mnombre,mapellido,mtelefono;
    String id_string,clave_string,resultado,mensaje,nueva_clave_string,nueva_clave1_string,nombre;
    String LOGIN_URL_MODIFICAR_CLAVE="http://34.193.208.83/jardinero/modificar/modificar_clave.php";

    JSONParser jsonParser = new JSONParser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modificar);
        textclave=(TextView)findViewById(R.id.textclave);
        eclave1=(EditText)findViewById(R.id.eclave1);
        eclave2=(EditText)findViewById(R.id.eclave2);
        enombre=(EditText)findViewById(R.id.enombre);
        eapellido=(EditText)findViewById(R.id.eapellido);
        etelefono=(EditText)findViewById(R.id.etelefono);
        mclave=(Button)findViewById(R.id.mclave);
        mnombre=(Button)findViewById(R.id.mnombre);
        mapellido=(Button)findViewById(R.id.mapellido);
        mtelefono=(Button)findViewById(R.id.mtelefono);
        mclave.setOnClickListener(this);
        mnombre.setOnClickListener(this);
        mapellido.setOnClickListener(this);
        mtelefono.setOnClickListener(this);
        id_string = getIntent().getExtras().getString("id");  // obtenemos el id de la actividad anterior
        clave_string = getIntent().getExtras().getString("clave"); // obtenemos la clave de la actividad anterior

    }
    public boolean validarClave(String clave1,String clave2){
        if(clave1.equals(clave2)){
            return true;
        }
        else{
            return false;
        }
    }
    public void onClick(View v){
            switch (v.getId()){
                case R.id.mclave:
                    nueva_clave_string=eclave1.getText().toString();
                    nueva_clave1_string=eclave2.getText().toString();
                    if(validarClave(nueva_clave_string,nueva_clave1_string)) {
                        new modificar.modificar_contrasenha().execute();  // ejecucion de asynctask
                    }
                    else{
                        textclave.setText("La contrase�a no coincide con el segundo campo");
                    }

                                    break;

                case R.id.mnombre:
                    enombre.getText().toString();
                                    break;
                case R.id.mapellido:
                                    break;
                case R.id.mtelefono:
                                    break;


            }
    }

    class modificar_contrasenha extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            List parametros = new ArrayList();
            parametros.add(new BasicNameValuePair("id", id_string));
            parametros.add(new BasicNameValuePair("clave", clave_string));
            parametros.add(new BasicNameValuePair("nueva_clave", nueva_clave_string));
            resultado = jsonParser.makeHttpRequest(LOGIN_URL_MODIFICAR_CLAVE, "POST",
                    parametros).toString();
            try {
                JSONObject object = new JSONObject(resultado);
                JSONArray json_array = object.optJSONArray("respuesta");
                mensaje = json_array.getJSONObject(0).getString("mensaje");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(String file_url) {
            textclave.setText(mensaje);
            clave_string=nueva_clave_string; // actualizacion de clave

        }
    }



}