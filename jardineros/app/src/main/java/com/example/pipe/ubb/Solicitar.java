package com.example.pipe.ubb;




import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.DatePicker;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static android.R.attr.duration;
import static android.os.Build.ID;
import static java.lang.System.currentTimeMillis;

public class Solicitar extends Activity {

    private EditText txtlugar;
    private EditText HH;
    private EditText MM;
    private TextView Evaluacion;
    private TextView muestraFecha;
    private ImageView gardinero;
    private Button btnSubmit;
    private Button btnFecha;
    private int anho;
    private int mes;
    private int dia;
    private static final int TIPP_DIALOGO = 0;
    private static DatePickerDialog.OnDateSetListener oyenteSelectorFecha;
    String resultado;
    String Jardinero;
    String Usuario="Prueba@gmail.com";
    String Hora="";
    String Lugar="";
    String Estado="Pendiente";
    String ID="";
    String  LOGIN_URL="http://34.193.208.83/jardinero/solicitar.php";
    JSONParser jsonParser = new JSONParser();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitar);
        Evaluacion = (TextView) findViewById(R.id.lblRate);
        muestraFecha = (TextView) findViewById(R.id.muestraFecha);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnFecha = (Button) findViewById(R.id.btnFecha);
        txtlugar = (EditText) findViewById(R.id.Lugar);
        HH = (EditText) findViewById(R.id.HH);
        MM = (EditText) findViewById(R.id.MM);

        //Calendario
        Calendar calendario = Calendar.getInstance();
        anho = calendario.get(Calendar.YEAR);
        mes = calendario.get(Calendar.MONTH);
        dia = calendario.get(Calendar.DAY_OF_MONTH);

        oyenteSelectorFecha = new DatePickerDialog.OnDateSetListener(){

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth){
                anho=year;
                mes=monthOfYear;
                dia=dayOfMonth;
                MostrarFecha();
            }
        };


        gardinero=(ImageView) findViewById(R.id.imageView2);
        gardinero.setImageResource(R.drawable.gardener);
        Bundle b = this.getIntent().getExtras();

        Evaluacion.setText("" + b.getString("NOMBRE"));
        Usuario=b.getString("usuario");
        Jardinero=Evaluacion.getText().toString();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                 Hora = muestraFecha.getText().toString() +" - "+ HH.getText().toString() +":"+ (MM).getText().toString();
                 Lugar = txtlugar.getText().toString();
                //long dat= currentTimeMillis();
                if(HoraValida(Integer.parseInt(HH.getText().toString()),Integer.parseInt((MM).getText().toString()))==true) {
                    ID = Long.toString(currentTimeMillis());
                    Log.d("mensaje", " id = " + ID);

                    Context context = getApplicationContext();
                    CharSequence text = " Solicitud enviada!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                    new Solicitar.SolicitarJ().execute();
                }
                else{
                    Context context2 = getApplicationContext();
                    CharSequence text2 = "Hora no Valida!";
                    int duration2 = Toast.LENGTH_SHORT;

                    Toast toast2 = Toast.makeText(context2, text2, duration2);
                    toast2.show();

                }
            }
        });

    }


    @Override
    protected Dialog onCreateDialog(int id){
        switch (id){
            case 0:
                return new DatePickerDialog(this, oyenteSelectorFecha, dia, mes, anho);
        }
        return null;
    }

    public void mostrarCalendario(View control) {
        showDialog(TIPP_DIALOGO);
    }


    public void MostrarFecha() {
        muestraFecha.setText(dia+"/"+(mes+1)+"/"+anho);
    }

    public static boolean HoraValida(int hora, int minuto){
        return ((hora<24)&&(hora>=0)&&(minuto<60)&&(minuto>=0));
    }


    class SolicitarJ extends AsyncTask<String, String, String> {
        protected void onPreExecute() {

        }
        @Override
        protected String doInBackground(String... params) {
            List parametros = new ArrayList();
            parametros.add(new BasicNameValuePair("correoU",Usuario));
            parametros.add(new BasicNameValuePair("correo",Jardinero));
            parametros.add(new BasicNameValuePair("hora",Hora));
            parametros.add(new BasicNameValuePair("lugar",Lugar));
            parametros.add(new BasicNameValuePair("estado",Estado));
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