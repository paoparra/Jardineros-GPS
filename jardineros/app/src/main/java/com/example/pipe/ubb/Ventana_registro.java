package com.example.pipe.ubb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Paola Alejandra on 21-06-2017.
 */

public class Ventana_registro  extends AppCompatActivity implements View.OnClickListener {
    Button RegistrarUser,RegistrarJardinero;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        RegistrarUser=(Button)findViewById(R.id.registrarUser);
        RegistrarJardinero=(Button)findViewById(R.id.registrarJardinero);
        RegistrarUser.setOnClickListener(this);
        RegistrarJardinero.setOnClickListener(this);

    }

    public void onClick(View v){
        switch(v.getId()){
            case R.id.registrarUser:
                Intent a=new Intent(this,RegistrarUsuario.class);
                startActivity(a);
                                        break;
            case R.id.registrarJardinero:
                Intent b=new Intent(this,RegistrarUsuarioJardinero.class);
                startActivity(b);
                                        break;
        }
    }
}
