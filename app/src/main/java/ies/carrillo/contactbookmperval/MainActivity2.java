package ies.carrillo.contactbookmperval;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        EditText telefono = findViewById(R.id.telefono);
        EditText nombre = findViewById(R.id.nombre);
        EditText apellidos = findViewById(R.id.apellidos);
        EditText correo = findViewById(R.id.correo);

        Button btnAtras = findViewById(R.id.btnAtras);
        Button btnAniadir = findViewById(R.id.btnAniadir);

        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Si el usuario presiona "Atrás", simplemente finaliza la actividad sin enviar datos
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        btnAniadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Si el usuario presiona "Agregar", obtén los valores de los EditText y envíalos a MainActivity
                String telefonoText = telefono.getText().toString();
                String nombreText = nombre.getText().toString();
                String apellidosText = apellidos.getText().toString();
                String correoText = correo.getText().toString();

                Intent intent = new Intent();
                intent.putExtra("telefono", telefonoText);
                intent.putExtra("nombre", nombreText);
                intent.putExtra("apellidos", apellidosText);
                intent.putExtra("correo", correoText);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
