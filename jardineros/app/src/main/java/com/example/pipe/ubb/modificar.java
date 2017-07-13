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
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pipe on 18-06-2017.
 */

public class modificar extends AppCompatActivity implements View.OnClickListener {
    TextView textclave,textNombre,textApellido,textTelefono;
    EditText eclave1,eclave2,enombre,eapellido,etelefono;
    Button mclave,mnombre,mapellido,mtelefono;
    String id_string,clave_string,resultado,mensaje,nueva_clave_string,nueva_clave1_string,nombre,apellido,telefono;
    String LOGIN_URL_MODIFICAR_CLAVE="http://34.193.208.83/jardinero/modificar/modificar_clave.php";
    String LOGIN_URL_MODIFICAR_NOMBRE="http://34.193.208.83/jardinero/modificar/modificar_nombre.php";
    String LOGIN_URL_MODIFICAR_TELEFONO="http://34.193.208.83/jardinero/modificar/modificar_telefono.php";
    String LOGIN_URL_MODIFICAR_APELLIDO="http://34.193.208.83/jardinero/modificar/modificar_apellido.php";



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
        textNombre=(TextView)findViewById(R.id.textNombre);
        textApellido=(TextView)findViewById(R.id.textApellido);
        textTelefono=(TextView)findViewById(R.id.textTelefono);

        mclave.setOnClickListener(this);
        mnombre.setOnClickListener(this);
        mapellido.setOnClickListener(this);
        mtelefono.setOnClickListener(this);
        id_string = getIntent().getExtras().getString("id");  // obtenemos el id de la actividad anterior
        clave_string = getIntent().getExtras().getString("clave"); // obtenemos la clave de la actividad anterior

    }
    public static boolean validarClave(String clave1,String clave2){
        if(clave1.equals(clave2)){
            return true;
        }
        else{
            return false;
        }
    }

    public static boolean telefonoValido(String digitos){
        return digitos.length()==9;
    }

    public static boolean verifyNumeros(String digitos){
        return digitos.matches("[-+]?\\d*\\.?\\d+");
    }
    public static boolean maximoLetrasValido(String campo,int digitosEsperados){
        return campo.length()<=digitosEsperados;
    }


    public void onClick(View v){
            switch (v.getId()){
                case R.id.mclave:
                    nueva_clave_string=eclave1.getText().toString();
                    nueva_clave1_string=eclave2.getText().toString();

                    if(maximoLetrasValido(nueva_clave_string,30) && maximoLetrasValido(nueva_clave1_string,30)){
                        if(validarClave(nueva_clave_string,nueva_clave1_string)) {
                            new modificar.modificar_contrasenha().execute();  // ejecucion de asynctask
                        }
                        else{
                            textclave.setText("La contraseña no coincide con el segundo campo");
                        }
                    }
                    else{
                        textclave.setText("Se permiten hasta 30 caracteres");
                    }



                                    break;

                case R.id.mnombre:
                    nombre = enombre.getText().toString();
                    int maximo_letras=40;
                    if(maximoLetrasValido(nombre,maximo_letras)){
                        new modificar.modificar_nombre().execute();
                    }
                    else{
                        textNombre.setText("Se permiten hasta 40 caracteres");
                    }

                                    break;
                case R.id.mapellido:
                    apellido = eapellido.getText().toString();

                    if(maximoLetrasValido(apellido,40)){
                        new modificar.modificar_apellido().execute();
                    }
                    else{
                        textApellido.setText("Se permiten hasta 40 caracteres");
                    }

                                    break;
                case R.id.mtelefono:
                    telefono = etelefono.getText().toString();
                    if(verifyNumeros(telefono)) {
                        if(telefonoValido(telefono)){
                            new modificar.modificar_telefono().execute();
                        }
                        else{
                            textTelefono.setText("El telefono debe tener 9 digitos");
                        }

                    }
                    else{
                        textTelefono.setText("El telefono debe contener numeros no letras");
                    }
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

    class modificar_nombre extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            List parametros = new ArrayList();
            parametros.add(new BasicNameValuePair("id", id_string));
            parametros.add(new BasicNameValuePair("clave", clave_string));
            parametros.add(new BasicNameValuePair("nombre", nombre));

            resultado = jsonParser.makeHttpRequest(LOGIN_URL_MODIFICAR_NOMBRE, "POST",
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
           textNombre.setText(mensaje);

        }
    }
    class modificar_apellido extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            List parametros = new ArrayList();
            parametros.add(new BasicNameValuePair("id", id_string));
            parametros.add(new BasicNameValuePair("clave", clave_string));
            parametros.add(new BasicNameValuePair("apellido", apellido));

            resultado = jsonParser.makeHttpRequest(LOGIN_URL_MODIFICAR_APELLIDO, "POST",
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
            textApellido.setText(mensaje);


        }
    }
    class modificar_telefono extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            List parametros = new ArrayList();
            parametros.add(new BasicNameValuePair("id", id_string));
            parametros.add(new BasicNameValuePair("clave", clave_string));
            parametros.add(new BasicNameValuePair("telefono", telefono));

            resultado = jsonParser.makeHttpRequest(LOGIN_URL_MODIFICAR_TELEFONO, "POST",
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
            textTelefono.setText(mensaje);


        }
    }



}
