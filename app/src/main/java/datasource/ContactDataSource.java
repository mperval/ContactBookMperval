package datasource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import database.DatabaseHelper;
import models.Contact;

public class ContactDataSource {

    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;
    //Constructor.
    public ContactDataSource(Context context){
        dbHelper = new DatabaseHelper(context);
    }
    //abrir conexion.
    public void openWritableDatabase() throws SQLException{

        database = dbHelper.getWritableDatabase();
    }
    //cerrar conexion.
    public void close(){
        dbHelper.close();
    }
    public void insertContact(String number, String name, String lastName, String email){
        openWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("number", number);



        if(name == null){
            values.put("name", "AnonimoLoquisiiimoo");
        }else {
            values.put("name", name);
        }
        if(lastName == null){
            values.put("lastName", "ApellidoAnonimoLoquisiiimoo");
        }else {
            values.put("lastName", lastName);
        }

        values.put("email", email);

        database.insert("Book", "AnonimoLoquisiiimoo", values);
        close();
    }
    public void updateBook(int id, String title, String author){

        openWritableDatabase();
        //solo añadimos los pares clave-valor de las columnas que queremos actualizar.
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("author", author);

        String[] args = new String[]{String.valueOf(id)};

        database.update("Book", values, "_id=?", args);
        //database.update("Book", values, "author = ?", new String[]{"Cervantes"});

    }
    public void deleteBook(int id){

        openWritableDatabase();

        String[] args = new String[]{String.valueOf(id)};
        database.delete("Book", "_id=?", args);

        close();
    }

    public List<Contact> getAllBooks(){
        List<Contact> result = new ArrayList<>();

        openWritableDatabase();

        String[] columns ={"_id", "numero", "nombre", "apellidos", "correo"};
        Cursor bookCursor = database.query("book", columns, null, null, null, null, null);
        //Cursor bookCursor = database.rawQuery("SELECT _id, title, author FROM book WHERE title = ? OR  author ");
        if(bookCursor != null && bookCursor.moveToFirst()){
            do{
                int idIndex = bookCursor.getColumnIndex("_id");//el cursor busca la columna
                int numberIndex = bookCursor.getColumnIndex("numero");
                int nameIndex = bookCursor.getColumnIndex("nombre");
                int lastNameIndex = bookCursor.getColumnIndex("apellidos");
                int correoIndex = bookCursor.getColumnIndex("correo");

                // o atraves de indicacion de columna. int id = booksCursor.getInt(0);

                int id = bookCursor.getInt(idIndex);
                String number = bookCursor.getString(numberIndex);
                String name = bookCursor.getString(nameIndex);
                String lastName = bookCursor.getString(lastNameIndex);
                String correo = bookCursor.getString(correoIndex);
                Contact contact = new Contact();
                contact.setId(id);
                contact.setNumero(number);
                contact.setNombre(name);
                contact.setApellidos(lastName);
                contact.setCorreo(correo);

            }while(bookCursor.moveToNext());
        }
        close();
        return result;
    }
    
}
