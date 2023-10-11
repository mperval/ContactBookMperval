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

        Intent intent = getIntent();
        if (intent != null) {
            TextView nombre = (TextView) findViewById(R.id.nombre);
            TextView apellidos = (TextView) findViewById(R.id.apellidos);
            TextView telefono = (TextView) findViewById(R.id.telefono);
            TextView correo = (TextView) findViewById(R.id.correo);

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
    }
}