package com.example.pipe.ubb;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class mostrarUsuarios extends AppCompatActivity implements View.OnClickListener{
    String seudonimoString, claveString;
    TextView mensaje;
    Button btnEliminar;
    EditText etCorreo;
    EditText etCausa;
    String correo, causa;

    String  LOGIN_URL="http://34.193.208.83/jardinero/admin/eliminarUsuario.php";
    JSONParser jsonParser = new JSONParser();

    String respuestajson, mensajePantalla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mostrar_usuarios);
        seudonimoString = getIntent().getExtras().getString("seudonimo");
        claveString = getIntent().getExtras().getString("clave");
        mensaje = (TextView) findViewById(R.id.tvMensaje);
        etCorreo = (EditText) findViewById(R.id.etCorreo);
        etCausa = (EditText) findViewById(R.id.etCausa);
        btnEliminar = (Button)findViewById(R.id.btnEliminar);
        btnEliminar.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnEliminar:  correo = etCorreo.getText().toString();
                causa = etCausa.getText().toString();
                if(!correo.isEmpty()&&!causa.isEmpty()){
                    new mostrarUsuarios.Eliminar().execute();
                }
                else{
                    if(correo.isEmpty()) {
                        findViewById(R.id.etCorreo).requestFocus();
                        etCorreo.setHintTextColor(getResources().getColor(R.color.colorAccent));
                        mensaje.setText("No ha ingresado Correo a Eliminar");
                        mensaje.setTextSize(18);
                    }
                    else if(causa.isEmpty()){
                        findViewById(R.id.etCausa).requestFocus();
                        etCausa.setHintTextColor(getResources().getColor(R.color.colorAccent));
                        mensaje.setText("No ha ingresado causa a Eliminar");
                        mensaje.setTextSize(18);
                    }
                }
                break;
        }
    }

    public class Eliminar extends AsyncTask<String, String, String> {
        protected void onPreExecute() {

        }
        @Override
        protected String doInBackground(String... params) {
            List registro = new ArrayList();
            registro.add(new BasicNameValuePair("correo",correo));
            registro.add(new BasicNameValuePair("seudonimo",seudonimoString));
            registro.add(new BasicNameValuePair("clave",claveString));
            registro.add(new BasicNameValuePair("causa",causa));
            // hacer peticion por post con los parametros ingresados
            respuestajson = jsonParser.makeHttpRequest(LOGIN_URL, "POST",
                    registro).toString();
            JSONObject object = null;
            try {
                object = new JSONObject(respuestajson);
                JSONArray json_array = object.optJSONArray("respuesta");
                mensajePantalla = json_array.getJSONObject(0).getString("mensaje");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        protected void onPostExecute(String file_url) {
            if(mensajePantalla.equals("El usuario se ha eliminado correctamente")){
                Context context = getApplicationContext();
                Toast toast = Toast.makeText(context, mensajePantalla, Toast.LENGTH_SHORT);
                toast.show();
            }
            mensaje.setText(mensajePantalla);
            mensaje.setTextSize(18);

        }
    }

}
