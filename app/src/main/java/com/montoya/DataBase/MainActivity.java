package com.montoya.DataBase;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private ListView listaAutos;
    private FloatingActionButton botonFlotante;
    private AdaptadorAutos adaptadorAutos;
    private DatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaAutos = (ListView) findViewById(R.id.listaAutos);
        botonFlotante = (FloatingActionButton) findViewById(R.id.botonFlotante);

        databaseManager = new DatabaseManager();
        adaptadorAutos = new AdaptadorAutos(this, databaseManager.getListaDeAutos());

        listaAutos.setAdapter(adaptadorAutos);

        databaseManager.cargarAutosDeDB(adaptadorAutos);

        botonFlotante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento = new Intent(MainActivity.this, AddQuoteActivity.class);
                startActivity(intento);
            }
        });
    }
}