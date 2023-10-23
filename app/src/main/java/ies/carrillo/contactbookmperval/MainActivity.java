package ies.carrillo.contactbookmperval;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;
import java.util.SortedSet;

import adapters.Adapters;
import database.DatabaseHelper;
import datasource.ContactDataSource;
import models.Contact;

public class MainActivity extends AppCompatActivity {
    private ContactDataSource cds;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnAniadir = findViewById(R.id.btnAniadir);

        // Obtén una referencia al ListView y asocia el adaptador<
        ListView contactListView = findViewById(R.id.listaContactos);

        List<Contact> contactList = cds.getAllDiarysContact();
        // Configura el adaptador para la lista de contactos
        Adapters contactAdapter = new Adapters((Context)this, contactList);

        DatabaseHelper helper = new DatabaseHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();




        contactListView.setAdapter(contactAdapter);

        contactListView.setOnItemClickListener((parent, view, position, id) -> {

            Contact contact = (Contact) parent.getItemAtPosition(position);

            Intent intent = new Intent(this, MainActivity_contact.class);
            intent.putExtra("id", contact.getId());
            intent.putExtra("nombre", contact.getNombre());
            intent.putExtra("apellidos", contact.getApellidos());
            intent.putExtra("telefono", contact.getNumero());
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
        cds.close();
    }
}