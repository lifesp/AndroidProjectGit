package com.example.mael.androidproject2.formulaire.BaseDonne;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by mael on 18/03/2016.
 */
public class BaseDonneJSON extends SQLiteOpenHelper {
    private final static String DATABASE_NAME = "DataBaseJSON.db" ;
    public final static String DATABASE_TABLE = "Liste" ;
    private final static int DATABASE_VERSION = 1 ;

    // def des champs de la base
    public static final String COLUMN_ID = "_id"; // id
    public static final String COLUMN_NAME = "name";

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + DATABASE_TABLE + "(" +
            COLUMN_ID + " integer primary key autoincrement, " +
            COLUMN_NAME + " text not null);";

    static private BaseDonneJSON instance = null;
    static public BaseDonneJSON getBaseDonne(Context context){
        if (instance == null){
            instance = new BaseDonneJSON(context);
        }
        return instance;
    }
    private BaseDonneJSON(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(BaseDonneJSON.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(db);

    }
}
