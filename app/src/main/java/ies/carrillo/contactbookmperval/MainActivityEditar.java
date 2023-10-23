package ies.carrillo.contactbookmperval;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import datasource.ContactDataSource;
import models.Contact;

public class MainActivityEditar extends AppCompatActivity {
    private ContactDataSource cds;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_editar);

        EditText telefono = findViewById(R.id.telefono);
        EditText nombre = findViewById(R.id.nombre);
        EditText apellidos = findViewById(R.id.apellidos);
        EditText correo = findViewById(R.id.correo);

        Button btnAceptar = findViewById(R.id.btnAceptar);
        Button btnCancelar = findViewById(R.id.btnCancelar);
        Button btnEliminar = findViewById(R.id.btnEliminar);

        Intent intent = getIntent();
        if (intent != null) {
            String nombre1 = intent.getStringExtra("nombre");
            String apellidos1 = intent.getStringExtra("apellidos");
            String telefono1 = intent.getStringExtra("telefono");
            String correo1 = intent.getStringExtra("correo");

            nombre.setText(nombre1);
            apellidos.setText(apellidos1);
            telefono.setText(telefono1);
            correo.setText(correo1);
        }
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String telefonoText = telefono.getText().toString();
                String nombreText = nombre.getText().toString();
                String apellidosText = apellidos.getText().toString();
                String correoText = correo.getText().toString();



                if(telefonoText.isEmpty() || nombreText.isEmpty() || apellidosText.isEmpty() || correoText.isEmpty()){

                    Toast.makeText(getApplicationContext(), "no puedes dejar ningun campo vacio", Toast.LENGTH_SHORT).show();
                }else{
                    if (intent != null) {
                        int id = intent.getIntExtra("id", -1);
                        cds.updateDiary(id, telefonoText, nombreText, apellidosText, correoText);
                    }
                }

                Intent intent = new Intent(MainActivityEditar.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityEditar.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String telefonoText = telefono.getText().toString();
                String nombreText = nombre.getText().toString();
                String apellidosText = apellidos.getText().toString();
                String correoText = correo.getText().toString();


                Contact c = new Contact();

                c.setNombre(nombreText);
                c.setApellidos(apellidosText);
                c.setNumero(telefonoText);
                c.setCorreo(correoText);



                Intent intent = new Intent(MainActivityEditar.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}