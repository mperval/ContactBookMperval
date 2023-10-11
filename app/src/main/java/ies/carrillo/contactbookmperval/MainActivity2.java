package ies.carrillo.contactbookmperval;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import data.Database;
import models.Contact;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        EditText telefono = findViewById(R.id.telefono2);
        EditText nombre = findViewById(R.id.nombre2);
        EditText apellidos = findViewById(R.id.apellidos2);
        EditText correo = findViewById(R.id.correo2);

        Button btnAtras = findViewById(R.id.btnAtras);
        Button btnAniadir = findViewById(R.id.btnAniadir);
        Button btnlimpiar = findViewById(R.id.btnLimpiar);

        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btnlimpiar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                telefono.setText("");
                nombre.setText("");
                apellidos.setText("");
                correo.setText("");
            }
        });
        btnAniadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String telefonoText = telefono.getText().toString();
                String nombreText = nombre.getText().toString();
                String apellidosText = apellidos.getText().toString();
                String correoText = correo.getText().toString();

                // creo el objeto contacto y lo añado.
                Contact c = new Contact();

                c.setId(Database.getID());//funcion de Database
                c.setNombre(nombreText);
                c.setApellidos(apellidosText);
                c.setNumero(telefonoText);
                c.setCorreo(correoText);

                if(c.getNumero().isEmpty() || c.getApellidos().isEmpty() || c.getCorreo().isEmpty() || c.getNombre().isEmpty()){

                    Toast.makeText(getApplicationContext(), "no puedes dejar ningun campo vacio", Toast.LENGTH_SHORT).show();

                }else {
                    Database.addContact(c);
                    Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                    startActivity(intent);
                }



            }
        });
    }
}
