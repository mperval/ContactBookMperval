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
import models.Contact;

public class MainActivity extends AppCompatActivity {

    private Adapters contactAdapter; // Adaptador para la lista de contactos
    private ActivityResultLauncher<Intent> launchMainActivity2; // Para iniciar MainActivity2 y recibir resultados

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnAniadir = findViewById(R.id.btnAniadir);

        // Obtén una referencia al ListView y asocia el adaptador
        ListView contactListView = findViewById(R.id.listaContactos);

        // Configura el adaptador para la lista de contactos
        contactAdapter = new Adapters(this, Database.lista);

        SortedSet<Contact> contactList = Database.lista;

        contactListView.setAdapter(contactAdapter);

        contactListView.setOnItemClickListener((parent, view, position, id) -> {
            Contact contact = (Contact) parent.getItemAtPosition(position);
            Intent intent = new Intent(this, MainActivity_contact.class);
            intent.putExtra("nombre", contact.getNombre());
            intent.putExtra("apellidos", contact.getApellidos());
            intent.putExtra("numero", contact.getNumero());
            intent.putExtra("correo", contact.getCorreo());

            startActivity(intent);
        });



        btnAniadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inicia MainActivity2.
                Intent intentMain2 = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intentMain2);
            }
        });
    }
}