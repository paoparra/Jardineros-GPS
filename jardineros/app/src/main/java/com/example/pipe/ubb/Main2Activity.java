package com.example.pipe.ubb;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
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

import static java.lang.System.currentTimeMillis;

public class Main2Activity extends Activity {

    private RatingBar ratingBar;
    private TextView txtRatingValue;
    private TextView Evaluacion;
    private ImageView gardinero;
    private Button btnSubmit;
    String resultado;
    String Jardinero;
    String Usuario="Prueba@gmail.com";
    String nota;
    String ID="";
    String  LOGIN_URL="http://34.193.208.83/jardinero/calificar.php";
    JSONParser jsonParser = new JSONParser();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);



        Evaluacion = (TextView) findViewById(R.id.lblRateMe);
        gardinero=(ImageView) findViewById(R.id.imageView2);
        gardinero.setImageResource(R.drawable.gardener);
        Bundle b = this.getIntent().getExtras();

        Evaluacion.setText("" + b.getString("NOMBRE"));
        Usuario=b.getString("usuario");
        Jardinero=Evaluacion.getText().toString();
        Log.d("mensaje","gardiner: "+Jardinero);

        addListenerOnRatingBar();
        addListenerOnButton();

    }

    public void addListenerOnRatingBar() {

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        txtRatingValue = (TextView) findViewById(R.id.txtRatingValue);

        //if rating value is changed,
        //display the current rating value in the result (textview) automatically
        ratingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {

                txtRatingValue.setText(String.valueOf(rating));
               // nota=Integer.parseInt(txtRatingValue.getText().toString());
                nota=txtRatingValue.getText().toString();

            }
        });
    }

    public void addListenerOnButton() {

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        //if click on me, then display the current rating value.
        btnSubmit.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                ID=Long.toString(currentTimeMillis());
                Log.d("mensaje"," id = "+ID);

                Toast.makeText(Main2Activity.this,
                        String.valueOf(ratingBar.getRating()),
                        Toast.LENGTH_SHORT).show();
               new Main2Activity.notaJ().execute();
            }

        });

    }


    class notaJ extends AsyncTask<String, String, String> {
        protected void onPreExecute() {

        }
        @Override
        protected String doInBackground(String... params) {
            List parametros = new ArrayList();
            parametros.add(new BasicNameValuePair("correo",Jardinero));
            parametros.add(new BasicNameValuePair("correoU",Usuario));
            parametros.add(new BasicNameValuePair("calificacion",nota));
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
