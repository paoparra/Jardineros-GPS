package com.example.pipe.ubb;


import android.app.Activity;
    import android.content.Context;
    import android.content.Intent;
    import android.graphics.Color;
    import android.os.Bundle;
    import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
    import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
    import java.util.HashMap;
    import java.util.List;


public class JardinerosLista extends Activity {
    ArrayList correo=new ArrayList();
    ArrayList<String> Muestra = new ArrayList<String>();
    String resultado;
    String parametro="";
    String message;
    ListView listview;
    ListViewAdapter adapter;
    ArrayList<String>  titulos= new ArrayList<>();
    ArrayList<String>  subtitulos= new ArrayList<>();
    ArrayList<String> notas = new ArrayList<>();

    int[] imagenes = {R.drawable.icon, R.drawable.icon, R.drawable.icon, R.drawable.icon};



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_jardineros_lista);
            listview = (ListView) findViewById(R.id.listview);


            ArrayList lista = (ArrayList) getIntent().getSerializableExtra("micorreo");
            ArrayList listaN = (ArrayList) getIntent().getSerializableExtra("misnombres");
            ArrayList listaC = (ArrayList) getIntent().getSerializableExtra("misnotas");

            final String eleccion= (String)getIntent().getSerializableExtra("op");
            final String usuario= (String)getIntent().getSerializableExtra("usuario");

            Log.d("mensaje","funciono: "+lista.size());
            Log.d("mensaje","eleccion: "+eleccion);
            //ArrayList<Category> category = new ArrayList<Category>();
            //AdapterCategory adapter = new AdapterCategory(this, category);
            final ArrayList<String> list = new ArrayList<String>();
            for (int i = 0; i < lista.size(); ++i) {
                list.add(lista.get(i).toString());
                subtitulos.add(listaN.get(i).toString());
                titulos.add(lista.get(i).toString());
                notas.add(listaC.get(i).toString());
            }
            /*
            titulos.add("Rudyard Fuster");
            titulos.add("Alexis Vizama");
            titulos.add("Paola Parra1");
            titulos.add("Paola Parra2");*/
            adapter = new ListViewAdapter(this, titulos, subtitulos, notas, imagenes);

            //final StableArrayAdapter adapter = new StableArrayAdapter(this,android.R.layout.simple_list_item_1, list);
            listview.setAdapter(adapter);

            /*listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    //Toast.makeText(getApplicationContext(), "Click ListItem Number " + position, Toast.LENGTH_LONG).show();
                   // view.setBackgroundColor(Color.LTGRAY);  //pone el color cuando selecciono
                    String jardinero=listview.getAdapter().getItem(position).toString();

                    Intent intent;

                    if(eleccion.equals("reclamar")==true){
                        intent = new Intent(JardinerosLista.this, Especifico.class);
                    }
                    else if (eleccion.equals("solicitar")==true){
                        intent = new Intent(JardinerosLista.this, Solicitar.class);
                    }
                    else if (eleccion.equals("ListaReclamos")==true){
                        intent = new Intent(JardinerosLista.this, Ver_Especifico.class);
                    }
                    else{ //para en caso de agun erroe tambien enviarlo a "evaluar", y que no caiga el sistema
                        intent = new Intent(JardinerosLista.this, Main2Activity.class);
                    }


                    Log.d("mensaje","hola: "+listview.getAdapter().getItem(position)); // muestra el seleccionado

                    //Creamos la información a pasar entre actividades
                    Bundle b = new Bundle();
                    b.putString("NOMBRE", listview.getAdapter().getItem(position).toString());
                    b.putString("usuario", usuario);
                    b.putString("jardinero", jardinero);
                    //Añadimos la información al intent
                    intent.putExtras(b);

                    //Iniciamos la nueva actividad
                    startActivity(intent);

                }
            });*/

            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    //Toast.makeText(getApplicationContext(), "Click ListItem Number " + position, Toast.LENGTH_LONG).show();
                    // view.setBackgroundColor(Color.LTGRAY);  //pone el color cuando selecciono
                    String jardinero=listview.getAdapter().getItem(position).toString();

                    Intent intent;

                    if(eleccion.equals("reclamar")==true){
                        intent = new Intent(JardinerosLista.this, Especifico.class);
                    }
                    else if (eleccion.equals("solicitar")==true){
                        intent = new Intent(JardinerosLista.this, Solicitar.class);
                    }
                    else if (eleccion.equals("ListaReclamos")==true){
                        intent = new Intent(JardinerosLista.this, Ver_Especifico.class);
                    }
                    else{ //para en caso de agun erroe tambien enviarlo a "evaluar", y que no caiga el sistema
                        intent = new Intent(JardinerosLista.this, Main2Activity.class);
                    }


                    Log.d("mensaje","hola: "+listview.getAdapter().getItem(position)); // muestra el seleccionado

                    //Creamos la información a pasar entre actividades
                    Bundle b = new Bundle();
                    b.putString("NOMBRE", listview.getAdapter().getItem(position).toString());
                    b.putString("usuario", usuario);
                    b.putString("jardinero", jardinero);
                    //Añadimos la información al intent
                    intent.putExtras(b);

                    //Iniciamos la nueva actividad
                    startActivity(intent);
                   // Toast.makeText(getApplicationContext(), "presiono " + position, Toast.LENGTH_SHORT).show();
                }
            });

        }

        //LISTVIEW  ADAPTER
        public class ListViewAdapter extends BaseAdapter {
            // Declare Variables
            Context context;
            ArrayList<String> titulos;
            ArrayList<String> subtitulos;
            ArrayList<String> notas;
            int[] imagenes;
            LayoutInflater inflater;

            public ListViewAdapter(Context context, ArrayList<String> titulos, ArrayList<String> subtitulos, ArrayList<String> notas, int[] imagenes) {
                this.context = context;
                this.titulos = titulos;
                this.subtitulos=subtitulos;
                this.notas = notas;
                this.imagenes = imagenes;
            }

            @Override
            public int getCount() {
                return titulos.size();
            }

            @Override
            public Object getItem(int position) {
                return titulos.get(position);
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            public View getView(int position, View convertView, ViewGroup parent) {

                // Declare Variables
                TextView txtTitle;
                TextView txtSubtitle;
                TextView txtNote;
                ImageView imgImg;

                //http://developer.android.com/intl/es/reference/android/view/LayoutInflater.html
                inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                View itemView = inflater.inflate(R.layout.jardineros_listrow, parent, false);

                // Locate the TextViews in listview_item.xml
                txtTitle = (TextView) itemView.findViewById(R.id.titulo_jardinero);
                txtSubtitle = (TextView) itemView.findViewById(R.id.subtitulo_jardinero);
                txtNote = (TextView) itemView.findViewById(R.id.nota_jardinero);

                imgImg = (ImageView) itemView.findViewById(R.id.imagen_jardinero);

                // Capture position and set to the TextViews
                txtTitle.setText(titulos.get(position));
                txtSubtitle.setText(subtitulos.get(position));
                txtNote.setText(notas.get(position));
                imgImg.setImageResource(imagenes[position]);

                return itemView;
            }
        }

    /*private class StableArrayAdapter extends ArrayAdapter<String> {

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

        }*/


    }