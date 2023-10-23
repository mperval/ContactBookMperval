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

        cds = new ContactDataSource(this);
        cds.getAllDiarysContact();
        cds.openWritableDatabase();
        cds.close();

        EditText telefono = findViewById(R.id.telefono);
        EditText nombre = findViewById(R.id.nombre);
        EditText apellidos = findViewById(R.id.apellidos);
        EditText correo = findViewById(R.id.correo);

        Button btnAceptar = findViewById(R.id.btnAceptar);
        Button btnCancelar = findViewById(R.id.btnCancelar);
        Button btnEliminar = findViewById(R.id.btnEliminar);

        Intent intent = getIntent();
        if (intent != null) {
            String nombre1 = intent.getStringExtra("name");
            String apellidos1 = intent.getStringExtra("lastName");
            String telefono1 = intent.getStringExtra("number");
            String correo1 = intent.getStringExtra("email");

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
                        cds.updateDiary(intent.getIntExtra("id", -1), telefonoText, nombreText, apellidosText, correoText);
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

                c.setName(nombreText);
                c.setLastName(apellidosText);
                c.setNumber(telefonoText);
                c.setEmail(correoText);
                if (intent != null) {
                    cds.deleteDiaryContact(intent.getIntExtra("id", -1));
                }
                Intent intent = new Intent(MainActivityEditar.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}