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

        String[] columns ={"_id", "title", "author",};
        Cursor bookCursor = database.query("book", columns, null, null, null, null, null);
        //Cursor bookCursor = database.rawQuery("SELECT _id, title, author FROM book WHERE title = ? OR  author ");
        if(bookCursor != null && bookCursor.moveToFirst()){
            do{
                int idIndex = bookCursor.getColumnIndex("_id");//el cursor busca la columna
                int titleIndex = bookCursor.getColumnIndex("title");
                int authorIndex = bookCursor.getColumnIndex("author");
                // o atraves de indicacion de columna. int id = booksCursor.getInt(0);

                int id = bookCursor.getInt(idIndex);
                String title = bookCursor.getString(titleIndex);
                String author = bookCursor.getString(authorIndex);

                Contact contact = new Contact();
                contact.setId(id);
                contact.set(title);
                contact.setAuthor(author);

            }while(bookCursor.moveToNext());
        }
        close();
        return result;
    }
    
}
