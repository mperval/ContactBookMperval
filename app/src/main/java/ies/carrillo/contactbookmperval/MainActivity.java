package ies.carrillo.contactbookmperval;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.SortedSet;

import adapters.Adapters;
import data.Database;

public class MainActivity extends AppCompatActivity {

    private Adapters contactAdapter; // Adaptador para la lista de contactos
    private ActivityResultLauncher<Intent> launchMainActivity2; // Para iniciar MainActivity2 y recibir resultados

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configura el adaptador para la lista de contactos
        contactAdapter = new Adapters(this, Database.lista);

        // Obtén una referencia al ListView y asocia el adaptador
        ListView contactListView = findViewById(R.id.listaContactos);
        contactListView.setAdapter(contactAdapter);

        Button btnAniadir = findViewById(R.id.btnAniadir);

        // Configura el adaptador para la lista de contactos
        contactAdapter = new Adapters(this, Database.lista);

        // Obtén una referencia al ListView y asocia el adaptador
        ListView listaContactos = findViewById(R.id.listaContactos);
        contactListView.setAdapter(contactAdapter);


        btnAniadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inicia MainActivity2 para agregar un nuevo contacto y espera un resultado
                Intent intentMain2 = new Intent(MainActivity.this, MainActivity2.class);
                launchMainActivity2.launch(intentMain2);
            }
        });
    }
}