package acom.example.myapplicationa.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import acom.example.myapplicationa.Metier.Poste;
import acom.example.myapplicationa.Metier.Saison;

public class SaisonDAO extends DAO<Saison>{

    private SQLiteSponsor dbJudo;
    private static final String Table_Saison= "Saison";
    private static final String Col_Id_Saison = "id_saison";
    private static final String Col_date_deb = "date_deb";
    private static final String Col_date_fin = "date_fin";
    private SQLiteDatabase db;

    public SaisonDAO(Context context) {
        dbJudo = new SQLiteSponsor(context);
    }

    public void open() {
        db = dbJudo.getWritableDatabase();
    }

    public void close() {
        dbJudo.close();
    }

    public void insert(Saison sa) {
        db = dbJudo.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Col_Id_Saison, sa.getId_saison());
        values.put(Col_date_deb, sa.getDateDebut().toString());
        values.put(Col_date_deb, sa.getDateFin().toString());
        db.insert(Table_Saison, null, values);
        db.close();
    }

    public void update(Saison sa) {
        open();
        ContentValues values = new ContentValues();
        values.put(Col_Id_Saison, sa.getId_saison());
        values.put(Col_date_deb, sa.getDateDebut().toString());
        values.put(Col_date_deb, sa.getDateFin().toString());
        db.update(Table_Saison, values, Col_Id_Saison+"="+sa.getId_saison(),null);
        db.close();

    }

    public void delete(Saison sa) {
        open();
        db.delete(Table_Saison,Col_Id_Saison+"="+sa.getId_saison(),null);
        db.close();
    }

}
