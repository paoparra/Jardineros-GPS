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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Agenda_Lista extends AppCompatActivity {


    ArrayList correo=new ArrayList();
    String resultado;
    String message;
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda__lista);
        listview = (ListView) findViewById(R.id.listview);

        ArrayList correou = (ArrayList) getIntent().getSerializableExtra("correoU");
        final ArrayList horau = (ArrayList) getIntent().getSerializableExtra("hora");
        final ArrayList lugaru = (ArrayList) getIntent().getSerializableExtra("lugar");
        final ArrayList ID = (ArrayList) getIntent().getSerializableExtra("ID");

        final String eleccion= (String)getIntent().getSerializableExtra("op");
        final String usuario= (String)getIntent().getSerializableExtra("usuario");

        Log.d("mensaje","funciono: "+correou.size());
        Log.d("mensaje","eleccion: "+eleccion);

        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < correou.size(); ++i) {
            list.add(correou.get(i).toString());
            //list.add(horau.get(i).toString());
            //list.add(lugaru.get(i).toString());
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
                String solicitante=listview.getAdapter().getItem(position).toString();


                Log.d("mensaje","hola: "+listview.getAdapter().getItem(position)); // muestra el seleccionado

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
