package com.montoya.DataBase;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.HashMap;

public class AdaptadorAutos extends ArrayAdapter<HashMap<String, String>> {

    private ArrayList<HashMap<String, String>> listaDeAutos;

    public AdaptadorAutos(Context context, ArrayList<HashMap<String, String>> listaDeAutos) {
        super(context, android.R.layout.simple_list_item_2, android.R.id.text1, listaDeAutos);
        this.listaDeAutos = listaDeAutos;
    }

    @NonNull
    @Override
    public View getView(int posicion, View convertView, @NonNull ViewGroup parent) {
        View vista = super.getView(posicion, convertView, parent);
        TextView texto1 = (TextView) vista.findViewById(android.R.id.text1);
        TextView texto2 = (TextView) vista.findViewById(android.R.id.text2);

        texto1.setText(listaDeAutos.get(posicion).get("Marca"));
        texto2.setText(listaDeAutos.get(posicion).get("Modelo") + ", " + listaDeAutos.get(posicion).get("Velocidad"));

        return vista;
    }
}
