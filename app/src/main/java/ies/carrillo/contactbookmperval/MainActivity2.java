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

public class MainActivity2 extends AppCompatActivity {
    private ContactDataSource cds;
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



                if(telefonoText.isEmpty() || nombreText.isEmpty() || apellidosText.isEmpty() || correoText.isEmpty()){

                    Toast.makeText(getApplicationContext(), "no puedes dejar ningun campo vacio", Toast.LENGTH_SHORT).show();

                }else {
                    cds.insertDiaryContact(nombreText, apellidosText, telefonoText, correoText);

                    Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                    startActivity(intent);
                }



            }
        });
    }
}
