package com.montoya.DataBase;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.HashMap;
import androidx.annotation.NonNull;

public class DatabaseManager {

    private ArrayList<HashMap<String, String>> listaDeAutos = new ArrayList<>();

    public ArrayList<HashMap<String, String>> getListaDeAutos() {
        return listaDeAutos;
    }

    public void cargarAutosDeDB(final AdaptadorAutos adaptadorAutos) {
        //instantiate database connection
        FirebaseDatabase baseDeDatos = FirebaseDatabase.getInstance();
        DatabaseReference referenciaAutos = baseDeDatos.getReference("Autos");

        referenciaAutos.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listaDeAutos.clear();
                for (DataSnapshot AutosSnapshot: dataSnapshot.getChildren()) {
                    HashMap<String, String> Auto = (HashMap<String, String>) AutosSnapshot.getValue();
                    listaDeAutos.add(Auto);
                }
                adaptadorAutos.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError errorBaseDeDatos) {
                //handle databaseError
            }
        });
    }
}
