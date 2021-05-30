package acom.example.myapplicationa.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


import acom.example.myapplicationa.Metier.Entreprise;

public class EntrepriseDAO {
    private SQLiteSponsor dbJudo;
    private static final String Table_Entreprise= "Entreprise";
    private static final String Col_Id_E = "id_E";
    private static final String Col_raison_sociale = "raison_sociale";
    private static final String Col_ville = "ville";
    private static final String Col_rue = "rue";
    private static final String Col_cp = "CP";
    private static final String Col_tel = "telephone";
    private static final String Col_mail = "mail";
    private SQLiteDatabase db;

    public EntrepriseDAO(Context context) {
        dbJudo = new SQLiteSponsor(context);
    }

    public void open() {
        db = dbJudo.getWritableDatabase();
    }

    public void close() {
        dbJudo.close();
    }

    public void insert(Entreprise e) {
        db = dbJudo.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Col_Id_E, e.getId_E());
        values.put(Col_raison_sociale, e.getRaison_sociale());
        values.put(Col_ville, e.getVille());
        values.put(Col_rue, e.getRue());
        values.put(Col_cp, e.getCP());
        values.put(Col_tel, e.getTelephone());
        values.put(Col_mail, e.getMail());
        db.insert(Table_Entreprise, null, values);
        db.close();
    }

    public void update(Entreprise e) {
        open();
        ContentValues values = new ContentValues();
        values.put(Col_Id_E, e.getId_E());
        values.put(Col_raison_sociale, e.getRaison_sociale());
        values.put(Col_ville, e.getVille());
        values.put(Col_rue, e.getRue());
        values.put(Col_cp, e.getCP());
        values.put(Col_tel, e.getTelephone());
        values.put(Col_mail, e.getMail());
        db.update(Table_Entreprise, values, Col_Id_E+"="+e.getId_E(),null);
        db.close();

    }

    public void delete(Entreprise e) {
        open();
        db.delete(Table_Entreprise,Col_Id_E+"="+e.getId_E(),null);
        db.close();
    }
}
