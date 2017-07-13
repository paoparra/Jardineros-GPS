package com.example.pipe.ubb;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UsuariosLista extends AppCompatActivity {

    ArrayList correo=new ArrayList();
    ArrayList<String> Muestra = new ArrayList<String>();
    String resultado;
    String parametro="";
    String message;
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios_lista);
        listview = (ListView) findViewById(R.id.listview);

        ArrayList correou = (ArrayList) getIntent().getSerializableExtra("correoU");
        final ArrayList horau = (ArrayList) getIntent().getSerializableExtra("horaU");
        final ArrayList lugaru = (ArrayList) getIntent().getSerializableExtra("LugarU");
        final ArrayList ID = (ArrayList) getIntent().getSerializableExtra("ID");

        final String eleccion= (String)getIntent().getSerializableExtra("op");
        final String usuario= (String)getIntent().getSerializableExtra("usuario");

        Log.d("mensaje","funciono: "+correou.size());
        Log.d("mensaje","eleccion: "+eleccion);

        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < correou.size(); ++i) {
            list.add(correou.get(i).toString());
        }

        final StableArrayAdapter adapter = new StableArrayAdapter(this,
                android.R.layout.simple_list_item_1, list);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //Toast.makeText(getApplicationContext(), "Click ListItem Number " + position, Toast.LENGTH_LONG).show();
                // view.setBackgroundColor(Color.LTGRAY);  //pone el color cuando selecciono
                String solicitante = listview.getAdapter().getItem(position).toString();

                Intent intent;

                if (eleccion.equals("Solicitud") == true) {
                    intent = new Intent(UsuariosLista.this, Decision.class);

/*
                else{ //para en caso de agun erroe tambien enviarlo a su "Agenda", y que no caiga el sistema
                    intent = new Intent(UsuariosLista.this, Agenda_Lista.class);
               }

*/
                Log.d("mensaje", "hola: " + listview.getAdapter().getItem(position)); // muestra el seleccionado

                //Creamos la información a pasar entre actividades
                Bundle b = new Bundle();
                b.putString("NOMBRE", listview.getAdapter().getItem(position).toString());
                b.putString("usuario", usuario); // el correo del usuario, osea el jardinero
                b.putString("solicitante", solicitante); // el correo del solicitante
                b.putString("hora", horau.get(position).toString()); //  la hora
                b.putString("lugar", lugaru.get(position).toString()); // el lugar
                b.putString("ID", ID.get(position).toString()); // el ID unico de la solicitud
                //Añadimos la información al intent
                intent.putExtras(b);

                //Iniciamos la nueva actividad
                startActivity(intent);
            }
            }
        });

    }

    private class StableArrayAdapter extends ArrayAdapter<String> {

        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId,
                                  List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    }
}
