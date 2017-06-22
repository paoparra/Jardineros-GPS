package com.example.pipe.ubb;


import android.app.Activity;
    import android.content.Context;
    import android.content.Intent;
    import android.graphics.Color;
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

import static com.example.pipe.ubb.R.id.listview;

public class JardinerosLista extends Activity {
    ArrayList correo=new ArrayList();
    ArrayList<String> Muestra = new ArrayList<String>();
    String resultado;
    String parametro="";
    String message;
    ListView listview;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_jardineros_lista);
            listview = (ListView) findViewById(R.id.listview);

            ArrayList lista = (ArrayList) getIntent().getSerializableExtra("micorreo");
            final String eleccion= (String)getIntent().getSerializableExtra("op");
            final String usuario= (String)getIntent().getSerializableExtra("usuario");

            Log.d("mensaje","funciono: "+lista.size());
            Log.d("mensaje","eleccion: "+eleccion);

            final ArrayList<String> list = new ArrayList<String>();
            for (int i = 0; i < lista.size(); ++i) {
                list.add(lista.get(i).toString());
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

                    Intent intent;

                    if(eleccion.equals("reclamar")==true){
                        intent = new Intent(JardinerosLista.this, Especifico.class);
                    }
                    else if (eleccion.equals("solicitar")==true){
                        intent = new Intent(JardinerosLista.this, Solicitar.class);
                    }
                    else{ //para en caso de agun erroe tambien enviarlo a "evaluar", y que no caiga el sistema
                        intent = new Intent(JardinerosLista.this, Main2Activity.class);
                    }


                    Log.d("mensaje","hola: "+listview.getAdapter().getItem(position)); // muestra el seleccionado

                    //Creamos la información a pasar entre actividades
                    Bundle b = new Bundle();
                    b.putString("NOMBRE", listview.getAdapter().getItem(position).toString());
                    b.putString("usuario", usuario);
                    //Añadimos la información al intent
                    intent.putExtras(b);

                    //Iniciamos la nueva actividad
                    startActivity(intent);

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