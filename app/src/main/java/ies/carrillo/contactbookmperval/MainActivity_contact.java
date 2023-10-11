package ies.carrillo.contactbookmperval;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
        if (intent != null) {


            nombre.setText(intent.getStringExtra("nombre"));
            apellidos.setText(intent.getStringExtra("apellidos"));
            telefono.setText(intent.getStringExtra("telefono"));
            correo.setText(intent.getStringExtra("correo"));
        }
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

                intent.putExtra("nombre", nombre.getText().toString());
                intent.putExtra("apellidos", apellidos.getText().toString());
                intent.putExtra("telefono", telefono.getText().toString());
                intent.putExtra("correo", correo.getText().toString());

                startActivity(intent);
            }
        });
    }
}