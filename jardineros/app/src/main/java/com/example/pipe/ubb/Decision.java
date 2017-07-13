package com.example.pipe.ubb;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import static com.example.pipe.ubb.R.id.mSolicitud;

public class Decision extends AppCompatActivity implements View.OnClickListener  {

    String CorreoU,Hora,Lugar,CorreoJ,ID;
    String Estado="Pendiente";
    String resultado;
    private TextView correo;
    private TextView hora;
    private TextView lugar;
    private Button Aceptar;
    private Button Rechazar;

    String  LOGIN_URL="http://34.193.208.83/jardinero/Atender_Solicitud.php";
    JSONParser jsonParser = new JSONParser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decision);

        correo = (TextView) findViewById(R.id.correo);
        hora = (TextView) findViewById(R.id.hora);
        lugar = (TextView) findViewById(R.id.lugar);

        Aceptar = (Button) findViewById(R.id.Aceptar);
        Rechazar = (Button) findViewById(R.id.Rechazar);

        Aceptar.setOnClickListener(this);
        Rechazar.setOnClickListener(this);


        Bundle b = this.getIntent().getExtras();
        CorreoJ=b.getString("usuario");
        CorreoU=b.getString("solicitante");
        Hora=b.getString("hora");
        Lugar=b.getString("lugar");
        ID=b.getString("ID");

        correo.setText("Usuario: "+CorreoU);
        hora.setText("hora: "+Hora);
        lugar.setText("lugar: "+Lugar);

    }
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.Aceptar:
                Estado="Aprobado";

                Context context = getApplicationContext();
                CharSequence text = " Cliente Aceptado!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                new Decision.Atender().execute();
                break;

            case R.id.Rechazar:
               Estado="Rechazado";

                Context context2 = getApplicationContext();
                CharSequence text2 = "Cliente Rechazado!";
                int duration2 = Toast.LENGTH_SHORT;

                Toast toast2 = Toast.makeText(context2, text2, duration2);
                toast2.show();
                new Decision.Atender().execute();
                break;


        }
    }
    class Atender extends AsyncTask<String, String, String> {
        protected void onPreExecute() {

        }
        @Override
        protected String doInBackground(String... params) {
            List parametros = new ArrayList();
            parametros.add(new BasicNameValuePair("estado",Estado));
            parametros.add(new BasicNameValuePair("ID",ID));
            resultado = jsonParser.makeHttpRequest(LOGIN_URL, "POST",
                    parametros).toString();
            JSONObject object = null;
            try {
                object = new JSONObject(resultado);
                JSONArray json_array = object.optJSONArray("respuesta");
            } catch (JSONException e) {
                e.printStackTrace();
            }


            return null;
        }

        protected void onPostExecute(String file_url) {


        }
    }


}
