package com.montoya.DataBase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AddQuoteActivity extends AppCompatActivity {

    private EditText editTextMarca;
    private EditText editTextModelo;
    private EditText editTextVelocidad;
    private Button botonAgregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_quote);

        //bind views
        editTextMarca = (EditText) findViewById(R.id.editTextMarca);
        editTextModelo = (EditText) findViewById(R.id.editTextModelo);
        editTextVelocidad = (EditText) findViewById(R.id.editTextVelocidad);
        botonAgregar = (Button) findViewById(R.id.botonAgregar);

        //listener
        botonAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get text
                String Marca = editTextMarca.getText().toString();
                String Modelo = editTextModelo.getText().toString();
                String Velocidad = editTextVelocidad.getText().toString();

                //check if empty
                if (Marca.isEmpty()){
                    editTextMarca.setError("No puede estar vacío");
                    return;
                }
                if (Modelo.isEmpty()){
                    editTextModelo.setError("No puede estar vacío");
                    return;
                }
                if (Velocidad.isEmpty()){
                    editTextVelocidad.setError("No puede estar vacío");
                    return;
                }

                //add to database
                agregarAutoABD(Marca, Modelo, Velocidad);
            }
        });
    }

    private void agregarAutoABD(String Marca, String Modelo, String Velocidad) {
        //create a hashmap
        HashMap<String, Object> AutosHashmap = new HashMap<>();
        AutosHashmap.put("Marca", Marca);
        AutosHashmap.put("Modelo", Modelo);
        AutosHashmap.put("Velocidad", Velocidad);

        //instantiate database connection
        FirebaseDatabase baseDeDatos = FirebaseDatabase.getInstance();
        DatabaseReference referenciaAutos = baseDeDatos.getReference("Autos");

        String clave = referenciaAutos.push().getKey();
        AutosHashmap.put("clave", clave);

        referenciaAutos.child(clave).setValue(AutosHashmap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(AddQuoteActivity.this, "Agregado", Toast.LENGTH_SHORT).show();
                editTextMarca.getText().clear();
                editTextModelo.getText().clear();
                editTextVelocidad.getText().clear();
            }
        });
    }
}
