package ies.carrillo.contactbookmperval;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import datasource.ContactDataSource;

public class MainActivity_contact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_contact);

        Button btnAtras = findViewById(R.id.btnAtras);
        Button btnEditar = findViewById(R.id.btnEditar);
        TextView nombre = (TextView) findViewById(R.id.nombre);
        TextView apellidos = (TextView) findViewById(R.id.apellidos);
        TextView telefono = (TextView) findViewById(R.id.telefono);
        TextView correo = (TextView) findViewById(R.id.correo);

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", -1);
        nombre.setText(intent.getStringExtra("name"));
        apellidos.setText(intent.getStringExtra("lastName"));
        telefono.setText(intent.getStringExtra("number"));
        correo.setText(intent.getStringExtra("email"));

        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity_contact.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity_contact.this, MainActivityEditar.class);

                intent.putExtra("name", nombre.getText().toString());
                intent.putExtra("lastName", apellidos.getText().toString());
                intent.putExtra("number", telefono.getText().toString());
                intent.putExtra("email", correo.getText().toString());
                intent.putExtra("id", id);

                startActivity(intent);
            }
        });
    }
}