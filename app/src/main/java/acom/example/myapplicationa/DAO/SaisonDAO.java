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
    private static final String Col_date_deb = "dateDebut";
    private static final String Col_date_fin = "dateFin";
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
        values.put(Col_date_deb, sa.getDateDebut());
        values.put(Col_date_fin, sa.getDateFin());
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

    public int last(){
        open();
        Cursor curseur = db.query (Table_Saison, null,null,null,null,null,null);
        curseur.moveToLast();
        int i = curseur.getInt(0);
        curseur.close();
        close();
        return i;
    }

    public Saison read(long id) {
        open();
        Saison laSa;
        db.isOpen();
        Cursor curseur = db.query(Table_Saison, null, Col_Id_Saison+"="+id,null,null,null,null);
        curseur.moveToFirst();
        laSa = new Saison(curseur.getInt(0), curseur.getString(1), curseur.getString(2));
        curseur.close();
        close();
        return laSa;
    }

    public ArrayList<Saison> read() {
        open();
        Saison laSa;
        ArrayList<Saison> lesSaisons = new ArrayList<>();
        db.isOpen();
        Cursor curseur = db.query (Table_Saison, null,null,null,null,null,null);
        curseur.moveToFirst();
        for (int i=1;i<=curseur.getCount();i++){
            laSa = new Saison(curseur.getInt(0),curseur.getString(1), curseur.getString(2));
            lesSaisons.add(laSa);
            curseur.moveToNext();
        }
        curseur.close();
        close();
        return lesSaisons;
    }

}
