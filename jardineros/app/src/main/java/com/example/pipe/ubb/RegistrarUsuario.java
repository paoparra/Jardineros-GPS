package com.example.pipe.ubb;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrarUsuario extends AppCompatActivity implements View.OnClickListener{

    private Button btnEnviarRegistro;
    private EditText editTextNombre;
    private EditText edidTextApellido;
    private EditText edidTextCorreo;
    private EditText edidTextTelefono;
    private EditText edidTextContrasena;
    private EditText edidTextContrasena2;
    private TextView textViewMensaje;
    String nombre;
    String apellido;
    String contrasenha;
    String telefono;
    String correo;
    String contrasenha2;
    String respuesta;
    String mensaje;

    String  LOGIN_URL="http://34.193.208.83/jardinero/registros/registrarUsuario.php";
    JSONParser jsonParser = new JSONParser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrar_usuario);

        textViewMensaje=(TextView)findViewById(R.id.textViewMensaje);
        editTextNombre=(EditText)findViewById(R.id.editTextNombre);
        edidTextApellido=(EditText)findViewById(R.id.editTextApellido);
        edidTextCorreo=(EditText)findViewById(R.id.editTextCorreo);
        edidTextTelefono=(EditText)findViewById(R.id.editTextTelefono);
        edidTextContrasena=(EditText)findViewById(R.id.editTextContrasena);
        edidTextContrasena2=(EditText)findViewById(R.id.editTextContrasena2);
        btnEnviarRegistro=(Button)findViewById(R.id.btnEnviarRegistro);

        btnEnviarRegistro.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnEnviarRegistro:
                nombre=editTextNombre.getText().toString();
                apellido=edidTextApellido.getText().toString();
                correo=edidTextCorreo.getText().toString();
                telefono=edidTextTelefono.getText().toString();
                contrasenha=edidTextContrasena.getText().toString();
                contrasenha2=edidTextContrasena2.getText().toString();
                if(!correo.isEmpty()&&!nombre.isEmpty()&&!apellido.isEmpty()&&!telefono.isEmpty()&&!contrasenha.isEmpty()&&!contrasenha2.isEmpty()){
                    if(contrasenha.equals(contrasenha2)){
                        if(isEmailValid(correo)) {
                            if(telefono.length()==9) {
                                new RegistrarUsuario.Registrar().execute();
                            }
                            else{
                                textViewMensaje.setText("Teléfono no válido");
                                textViewMensaje.setTextSize(18);
                                findViewById(R.id.editTextTelefono).requestFocus();
                                edidTextTelefono.selectAll();
                            }
                        }
                        else{
                            textViewMensaje.setText("Correo no válido");
                            textViewMensaje.setTextSize(18);
                            findViewById(R.id.editTextCorreo).requestFocus();
                            edidTextCorreo.selectAll();
                        }
                    }
                    else{
                        textViewMensaje.setText("Contraseñas no son iguales");
                        textViewMensaje.setTextSize(18);
                        findViewById(R.id.editTextContrasena2).requestFocus();
                        edidTextContrasena2.selectAll();
                    }
                }
                else{
                    System.out.println();
                    textViewMensaje.setText("Existen Campos Vacíos");
                    textViewMensaje.setTextSize(18);
                    if(nombre.isEmpty()) findViewById(R.id.editTextNombre).requestFocus();
                    else if (apellido.isEmpty()) findViewById(R.id.editTextApellido).requestFocus();
                    else if (correo.isEmpty()) findViewById(R.id.editTextCorreo).requestFocus();
                    else if(telefono.isEmpty()) findViewById(R.id.editTextTelefono).requestFocus();
                    else if(contrasenha.isEmpty()) findViewById(R.id.editTextContrasena).requestFocus();
                    else if(contrasenha2.isEmpty()) findViewById(R.id.editTextContrasena2).requestFocus();

                }
                break;
        }
    }

    public static boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    public class Registrar extends AsyncTask<String, String, String> {
        protected void onPreExecute() {

        }
        @Override
        protected String doInBackground(String... params) {
            List registro = new ArrayList();
            registro.add(new BasicNameValuePair("correo",correo));
            registro.add(new BasicNameValuePair("contrasenha",contrasenha));
            registro.add(new BasicNameValuePair("nombre",nombre));
            registro.add(new BasicNameValuePair("apellido_p",apellido));
            registro.add(new BasicNameValuePair("telefono",telefono));
            // hacer peticion por post con los parametros ingresados
            respuesta = jsonParser.makeHttpRequest(LOGIN_URL, "POST",
                    registro).toString();
            JSONObject object = null;
            try {
                object = new JSONObject(respuesta);
                JSONArray json_array = object.optJSONArray("respuesta");
                mensaje = json_array.getJSONObject(0).getString("message");
            } catch (JSONException e) {
                e.printStackTrace();
            }



            return null;
        }

        protected void onPostExecute(String file_url) {

            textViewMensaje.setText(mensaje);
            textViewMensaje.setTextSize(18);

        }
    }
}

