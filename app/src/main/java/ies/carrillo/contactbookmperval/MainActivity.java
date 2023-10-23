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

        cds = new ContactDataSource(this);
        cds.openWritableDatabase();

        cds.close();

        SortedSet<Contact> contactList = cds.getAllDiarysContact();

        ListView contactListView = findViewById(R.id.listaContactos);

        Adapters contactAdapter = new Adapters((Context)this, contactList);
        contactAdapter.notifyDataSetChanged();

        contactListView.setAdapter(contactAdapter);

        contactListView.setOnItemClickListener((parent, view, position, id) -> {

            Contact contact = (Contact) parent.getItemAtPosition(position);

            Intent intent = new Intent(this, MainActivity_contact.class);
            intent.putExtra("id", contact.getId());
            intent.putExtra("name", contact.getName());
            intent.putExtra("lastName", contact.getLastName());
            intent.putExtra("number", contact.getNumber());
            intent.putExtra("email", contact.getEmail());

            startActivity(intent);
        });
        btnAniadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMain2 = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intentMain2);
            }
        });
        cds.close();
    }
}