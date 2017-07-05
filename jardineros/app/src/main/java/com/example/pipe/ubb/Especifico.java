package com.example.pipe.ubb;




import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.os.Build.ID;
import static java.lang.System.currentTimeMillis;

public class Especifico extends Activity {

    private EditText txtmensaje;
    private TextView Evaluacion;
    private ImageView gardinero;
    private Button btnSubmit;
    String resultado;
    String Jardinero;
    String Usuario="Prueba@gmail.com";
    String Texto="";
    String ID="";
    String  LOGIN_URL="http://34.193.208.83/jardinero/reclamo.php";
    JSONParser jsonParser = new JSONParser();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_especifico);
        Evaluacion = (TextView) findViewById(R.id.lblRate);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        txtmensaje = (EditText) findViewById(R.id.Mensaje);

        gardinero=(ImageView) findViewById(R.id.imageView2);
        gardinero.setImageResource(R.drawable.gardener);
        Bundle b = this.getIntent().getExtras();

        Evaluacion.setText("" + b.getString("NOMBRE"));
        Usuario=b.getString("usuario");
        Jardinero=Evaluacion.getText().toString();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                 Texto = txtmensaje.getText().toString();

                if (Texto.equals("")==true){
                    Context context = getApplicationContext();
                    CharSequence text = " No hay reclamo escrito!";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }

else{
                    //long dat= currentTimeMillis();
                    ID=Long.toString(currentTimeMillis());
                    Log.d("mensaje"," id = "+ID);
                    Log.d("mensaje",""+Texto);
                    Context context = getApplicationContext();
                    CharSequence text = " Reclamo enviado!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                    new Especifico.ReclamoJ().execute();
                }
            }
        });

    }


    class ReclamoJ extends AsyncTask<String, String, String> {
        protected void onPreExecute() {

        }
        @Override
        protected String doInBackground(String... params) {
            List parametros = new ArrayList();
            parametros.add(new BasicNameValuePair("correoU",Usuario));
            parametros.add(new BasicNameValuePair("correo",Jardinero));
            parametros.add(new BasicNameValuePair("texto",Texto));
            parametros.add(new BasicNameValuePair("id",ID));
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