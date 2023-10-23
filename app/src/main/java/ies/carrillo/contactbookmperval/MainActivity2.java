package ies.carrillo.contactbookmperval;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import datasource.ContactDataSource;



public class MainActivity2 extends AppCompatActivity {
    private ContactDataSource cds;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        cds = new ContactDataSource(this);
        cds.getAllDiarysContact();
        cds.openWritableDatabase();
        cds.close();

        EditText telefono = findViewById(R.id.telefono2);
        EditText nombre = findViewById(R.id.nombre2);
        EditText apellidos = findViewById(R.id.apellidos2);
        EditText correo = findViewById(R.id.correo2);

        Button btnAtras = findViewById(R.id.btnAtras);
        Button btnAniadir = findViewById(R.id.btnAniadir);
        Button btnlimpiar = findViewById(R.id.btnLimpiar);

        // Configura un listener para el botón "Atras" que regresa a la actividad principal
        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
            }
        });
        // Configura un listener para el botón "Limpiar" que borra los campos de entrada
        btnlimpiar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                telefono.setText("");
                nombre.setText("");
                apellidos.setText("");
                correo.setText("");
            }
        });
        // Configura un listener para el botón "Aniadir" que agrega un nuevo contacto a la base de datos
        btnAniadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtiene el texto de los campos de entrada
                String telefonoText = telefono.getText().toString().trim();
                String nombreText = nombre.getText().toString().trim();
                String apellidosText = apellidos.getText().toString().trim();
                String correoText = correo.getText().toString().trim();

                // Verifica si algún campo está vacío y muestra un mensaje de error si es necesario
                if(telefonoText.isEmpty() || nombreText.isEmpty() || apellidosText.isEmpty() || correoText.isEmpty()){
                    Toast.makeText(getApplicationContext(), "no puedes dejar ningun campo vacio", Toast.LENGTH_SHORT).show();
                }else {
                    // Inserta el nuevo contacto en la base de datos
                    cds.insertDiaryContact(telefono.getText().toString(), nombre.getText().toString(), apellidos.getText().toString(), correo.getText().toString());
                    Toast.makeText(getApplicationContext(), "contacto agragado con exito", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
