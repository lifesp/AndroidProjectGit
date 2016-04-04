package com.example.mael.androidproject2.formulaire.BaseDonne;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mael.androidproject2.MainActivity;

import java.util.LinkedList;
import java.util.List;

import traitement.Produit;

/**
 * Created by mael on 18/03/2016.
 */
public class AddBaseJSON {
    private SQLiteDatabase db;
    private ContentValues contentValues;
    static public AddBaseJSON instance=null;
    static public AddBaseJSON getInstance(Context context){
        if(instance == null){
            instance = new AddBaseJSON(context);
        }
        return instance;
    }
    private AddBaseJSON(Context context){
        db = BaseDonneJSON.getBaseDonne(context).getWritableDatabase();
        contentValues = new ContentValues();
    }
    public void addProduit(Produit produit) {
        contentValues.put(BaseDonneJSON.COLUMN_NAME, produit.getNom());
        long id = db.insert(BaseDonneJSON.DATABASE_TABLE, null, contentValues);

    }
    public List<Produit> getProduit(){
        List<Produit> ps = new LinkedList<Produit>();
        SQLiteDatabase db = BaseDonneJSON.getBaseDonne(MainActivity.CONTEXT).getReadableDatabase() ;
        String [] allColumns = {BaseDonneJSON.COLUMN_ID, 	BaseDonneJSON.COLUMN_NAME,} ;
        Cursor cursor = db.query(BaseDonneJSON.DATABASE_TABLE, allColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            System.out.println("id = " + cursor.getLong(0) + ", name = " + cursor.getString(1));
            ps.add(new Produit(cursor.getString(1), cursor.getInt(2), "test"));
            cursor.moveToNext();
        }
        return ps;
    }
}
