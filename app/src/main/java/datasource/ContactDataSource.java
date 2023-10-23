package datasource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

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
    public void openReadableDatabase() throws SQLException{
        database = dbHelper.getReadableDatabase();
    }
    //cerrar conexion.
    public void close(){
        dbHelper.close();
    }
    public void insertDiaryContact(String number, String name, String lastName, String email){
        openWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("number", number);
        values.put("lastName", lastName);
        values.put("email", email);

        if(name == null){
            values.put("name", "anonimo");
        }else{
            values.put("name", name);
        }

        database.insert("Diary", null, values);
        close();
    }
    public void updateDiary(int id, String number, String name, String lastName, String email){

        openWritableDatabase();
        //solo añadimos los pares clave-valor de las columnas que queremos actualizar.
        ContentValues values = new ContentValues();

        values.put("number", number);
        values.put("name", name);
        values.put("lastName", lastName);
        values.put("email", email);

        String[] args = new String[]{String.valueOf(id)};

        database.update("Diary", values, "id=?", args);
        //database.update("Book", values, "author = ?", new String[]{"Cervantes"});

    }
    public void deleteDiaryContact(int id){

        openWritableDatabase();

        String[] args = new String[]{String.valueOf(id)};
        database.delete("Diary", "id=?", args);

        close();
    }

    public SortedSet<Contact> getAllDiarysContact(){
        SortedSet<Contact> result = new TreeSet<>();

        openReadableDatabase();

        String[] columns ={"id", "number", "name", "lastName", "email"};
        Cursor diaryCursor = database.query("Diary", columns, null, null, null, null, null);
        //Cursor bookCursor = database.rawQuery("SELECT _id, title, author FROM book WHERE title = ? OR  author ");
        if(diaryCursor != null && diaryCursor.moveToFirst()){
            do{
                int idIndex = diaryCursor.getColumnIndex("id");//el cursor busca la columna
                int numberIndex = diaryCursor.getColumnIndex("number");
                int nameIndex = diaryCursor.getColumnIndex("name");
                int lastNameIndex = diaryCursor.getColumnIndex("lastName");
                int emailIndex = diaryCursor.getColumnIndex("email");

                // o atraves de indicacion de columna. int id = booksCursor.getInt(0);

                int id = diaryCursor.getInt(idIndex);
                String number = diaryCursor.getString(numberIndex);
                String name = diaryCursor.getString(nameIndex);
                String lastName = diaryCursor.getString(lastNameIndex);
                String email = diaryCursor.getString(emailIndex);

                Contact contact = new Contact();
                contact.setId(id);
                contact.setNumber(number);
                contact.setName(name);
                contact.setLastName(lastName);
                contact.setEmail(email);

                result.add(contact);

            }while(diaryCursor.moveToNext());
        }
        close();
        return result;
    }
}