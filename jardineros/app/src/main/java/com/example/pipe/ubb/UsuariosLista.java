package com.example.pipe.ubb;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
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
    int[] img = {R.drawable.h1, R.drawable.m1, R.drawable.h2, R.drawable.m2, R.drawable.h3, R.drawable.m3, R.drawable.h4, R.drawable.m4, R.drawable.m5};
    ArrayList<Integer> imagenes = new ArrayList<Integer>();

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

        ArrayList<String> cor = new ArrayList<String>();
        ArrayList<String> hr = new ArrayList<String>();
        ArrayList<String> lg = new ArrayList<String>();

        for (int i = 0; i < correou.size(); ++i) {
            cor.add(correou.get(i).toString());
            hr.add(horau.get(i).toString());
            lg.add(lugaru.get(i).toString());
            imagenes.add(img[(int) (Math.random() * 8)]);
        }

         ListViewAdapter adapter = new ListViewAdapter(this, cor, lg, hr, imagenes);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //Toast.makeText(getApplicationContext(), "Click ListItem Number " + position, Toast.LENGTH_LONG).show();
                // view.setBackgroundColor(Color.LTGRAY);  //pone el color cuando selecciono
                String solicitante=listview.getAdapter().getItem(position).toString();

                Intent intent;

                if(eleccion.equals("Solicitud")==true) {
                    intent = new Intent(UsuariosLista.this, Decision.class);

/*
                else{ //para en caso de agun erroe tambien enviarlo a su "Agenda", y que no caiga el sistema
                    intent = new Intent(UsuariosLista.this, Agenda_Lista.class);
                }
*/

                    Log.d("mensaje", "hola: " + listview.getAdapter().getItem(position)); // muestra el seleccionado

                    //Creamos la informaci�n a pasar entre actividades
                    Bundle b = new Bundle();
                    b.putString("NOMBRE", listview.getAdapter().getItem(position).toString());
                    b.putString("usuario", usuario); // el correo del usuario, osea el jardinero
                    b.putString("solicitante", solicitante); // el correo del solicitante
                    b.putString("hora", horau.get(position).toString()); //  la hora
                    b.putString("lugar", lugaru.get(position).toString()); // el lugar
                    b.putString("ID", ID.get(position).toString()); // el ID unico de la solicitud
                    //A�adimos la informaci�n al intent
                    intent.putExtras(b);

                    //Iniciamos la nueva actividad
                    startActivity(intent);
                }
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
        ArrayList<Integer> imagenes;
        LayoutInflater inflater;

        public ListViewAdapter(Context context, ArrayList<String> titulos, ArrayList<String> subtitulos, ArrayList<String> notas , ArrayList<Integer> imagenes) {
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

            View itemView = inflater.inflate(R.layout.jardinero_listrow, parent, false);

            // Locate the TextViews in listview_item.xml
            txtTitle = (TextView) itemView.findViewById(R.id.titulo_jardinero);
            txtSubtitle = (TextView) itemView.findViewById(R.id.subtitulo_jardinero);
            txtNote = (TextView) itemView.findViewById(R.id.nota_jardinero);

            imgImg = (ImageView) itemView.findViewById(R.id.imagen_jardinero);

            // Capture position and set to the TextViews
            txtTitle.setText(titulos.get(position));
            txtSubtitle.setText(subtitulos.get(position));
            txtNote.setText(notas.get(position));
            imgImg.setImageResource(imagenes.get(position));

            return itemView;
        }
    }
}
