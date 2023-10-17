package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "AGENDA APP";
    private  static final int DATABASE_VERSION = 1;
    private static final String CREATE_CONTACT_TABLE = "CREATE TABLE Contact(_id INTEGER PRIMARY KEY AUTOINCREMENT, number TEXT, name TEXT, lastName TEXT, email TEXT)";

    private static final String DROP_CONTACT_TABLE = "DROP TABLE IF EXISTS Contact";

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_CONTACT_TABLE); //ejecuta una sentencia SQL de creacion de la tabla.
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int ondVersion, int newVersion) {

        db.execSQL(DROP_CONTACT_TABLE);//se ejecuta la sentencia SQL de eliminacion de la tabla.
        onCreate(db);
    }
}
