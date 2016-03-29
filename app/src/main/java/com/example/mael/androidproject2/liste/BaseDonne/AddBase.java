package com.example.mael.androidproject2.liste.BaseDonne;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.mael.androidproject2.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import traitement.Produit;

/**
 * Created by mael on 18/03/2016.
 */
public class AddBase {
    private SQLiteDatabase db;
    private ContentValues contentValues;
    static public AddBase instance=null;
    static public AddBase getInstance(Context context){
        if(instance == null){
            instance = new AddBase(context);
        }
        return instance;
    }
    private AddBase(Context context){
        db = BaseDonne.getBaseDonne(context).getWritableDatabase();
        contentValues = new ContentValues();
    }
    public void addProduit(Produit produit) {
        contentValues.put(BaseDonne.COLUMN_NAME, produit.getNom());
        contentValues.put(BaseDonne.COLUMN_QUANTITER, produit.getQuantiter());
        long id = db.insert(BaseDonne.DATABASE_TABLE, null, contentValues);

    }
    public Produit[] getProduit(){
        SQLiteDatabase db = BaseDonne.getBaseDonne(MainActivity.CONTEXT).getReadableDatabase() ;
        Cursor cursor = db.query(BaseDonne.DATABASE_TABLE,
                allColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            System.out.println("id = " + cursor.getLong(0) + ", name = " + cursor.getString(1) +
                    ", play= " + cursor.getString(2));
            addItem(new DummyItem(cursor.getString(0), cursor.getString(1), cursor.getString(2)));
            cursor.moveToNext();
        }
    }
}
