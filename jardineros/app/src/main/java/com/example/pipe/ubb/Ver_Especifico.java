package com.example.pipe.ubb;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Ver_Especifico extends AppCompatActivity {
    ArrayList reclamos=new ArrayList();

    private TextView Caja;
    private Button Ver;
    String resultado;
    String Jardinero;

    String  LOGIN_URL="http://34.193.208.83/jardinero/ver_reclamos.php";
    JSONParser jsonParser = new JSONParser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver__especifico);

        Ver = (Button) findViewById(R.id.VerLista);
        Caja = (TextView) findViewById(R.id.caja);

        Bundle b = this.getIntent().getExtras();

        Jardinero=b.getString("jardinero");

        Ver.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Context context = getApplicationContext();
                CharSequence text = " Reclamos hacia jardinero!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

                new Ver_Especifico.verR().execute();
            }
        });
    }



    class verR extends AsyncTask<String, String, String> {
        protected void onPreExecute() {

        }
        @Override
        protected String doInBackground(String... params) {
            String hola="nada";
            List parametros = new ArrayList();
            parametros.add(new BasicNameValuePair("correo",Jardinero));
            parametros.add(new BasicNameValuePair("Motivo",hola));
            resultado = jsonParser.makeHttpRequest(LOGIN_URL, "POST",
                    parametros).toString();
            JSONObject object = null;
            try {
                object = new JSONObject(resultado);
                JSONArray json_array = object.optJSONArray("respuesta");
                reclamos.clear();
                Log.d("mensaje","hola: "+json_array.toString());
                for (int i = 0; i < json_array.length(); i++) {
                    Log.d("mensaje","hola: "+json_array.getJSONObject(i).toString()); // muestra el seleccionado
                    reclamos.add(json_array.getJSONObject(i).getString("Motivo"));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }


            return null;
        }

        protected void onPostExecute(String file_url) {
            String valores="";
            for(int i=0;i<reclamos.size();i++){
                valores=valores+reclamos.get(i).toString()+"\n";
            }
            Caja.setText(valores);
        }
    }

}
