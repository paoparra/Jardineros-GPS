
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


public class UBB extends AppCompatActivity implements View.OnClickListener {
   private Button btnLoginUsuario,btnLoginJardinero,registrar,Login_administrador;

    JSONParser jsonParser = new JSONParser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubb);
        btnLoginUsuario=(Button)findViewById(R.id.btnLoginUsuario);
        btnLoginJardinero=(Button)findViewById(R.id.btnLoginJardinero);
        registrar=(Button)findViewById(R.id.registrar);
        Login_administrador=(Button)findViewById(R.id.login_administrador);
        btnLoginUsuario.setOnClickListener(this);
        btnLoginJardinero.setOnClickListener(this);
        registrar.setOnClickListener(this);
        Login_administrador.setOnClickListener(this);





    }
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btnLoginUsuario:
                 Intent b=new Intent(this,login_usuario.class);
                startActivity(b);
                                        break;
            case R.id.btnLoginJardinero:
                 Intent g= new Intent(this,login_jardinero.class);
                 startActivity(g);
                                        break;
            case R.id.registrar:
                    Intent a=new Intent(this,Ventana_registro.class);
                    startActivity(a);
                    break;
            case R.id.login_administrador:
                    Intent f=new Intent(this,login_admin.class);
                    startActivity(f);
                    break;

        }
    }





}
