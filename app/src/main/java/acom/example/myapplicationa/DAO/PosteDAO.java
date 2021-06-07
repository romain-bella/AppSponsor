package acom.example.myapplicationa.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import acom.example.myapplicationa.DAO.DAO;
import acom.example.myapplicationa.DAO.SQLiteSponsor;
import acom.example.myapplicationa.Metier.Correspondant;
import acom.example.myapplicationa.Metier.Poste;

public class PosteDAO extends DAO<Poste>{

    private SQLiteSponsor dbJudo;
    private static final String Table_Poste = "Poste";
    private static final String Col_Id_POST = "id_poste";
    private static final String Col_NomPoste = "nom_poste";
    private SQLiteDatabase db;

    public PosteDAO(Context context) {
        dbJudo = new SQLiteSponsor(context);
    }

    public void open() {
        db = dbJudo.getWritableDatabase();
    }

    public void close() {
        dbJudo.close();
    }

    public void insert(Poste po) {
        db = dbJudo.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Col_Id_POST, po.getId_poste());
        values.put(Col_NomPoste, po.getNom_poste());
        db.insert(Table_Poste, null, values);
        db.close();
    }

    public void update(Poste po) {
        open();
        ContentValues values = new ContentValues();
        values.put(Col_Id_POST, po.getId_poste());
        values.put(Col_NomPoste, po.getNom_poste());
        db.update(Table_Poste, values, Col_Id_POST+"="+po.getId_poste(),null);
        db.close();

    }

    public void delete(Poste po) {
        open();
        db.delete(Table_Poste,Col_Id_POST+"="+po.getId_poste(),null);
        db.close();
    }

    public int compte(){
        int nbpostes = 0;
        open();
        Cursor curseur = db.query(Table_Poste,null,null,null,null,null,null,null);
        curseur.moveToFirst();
        nbpostes = curseur.getCount();
        close();
        return nbpostes;
    }

    public int last(){
        open();
        Cursor curseur = db.query (Table_Poste, null,null,null,null,null,null);
        curseur.moveToLast();
        int i = curseur.getInt(0);
        curseur.close();
        close();
        return i;
    }

    public Poste read(long id) {
        open();
        Poste lePoste;
        db.isOpen();
        Cursor curseur = db.query(Table_Poste, null, Col_Id_POST+"="+id,null,null,null,null);
        curseur.moveToFirst();
        lePoste = new Poste(curseur.getInt(0), curseur.getString(1));
        curseur.close();
        close();
        return lePoste;
    }

    public ArrayList<Poste> read() {
        open();
        Poste lePoste;
        ArrayList<Poste> lesPostes = new ArrayList<>();
        db.isOpen();
        Cursor curseur = db.query (Table_Poste, null,null,null,null,null,null);
        curseur.moveToFirst();
        for (int i=1;i<=curseur.getCount();i++){
            lePoste = new Poste(curseur.getInt(0),curseur.getString(1));
            lesPostes.add(lePoste);
            curseur.moveToNext();
        }
        curseur.close();
        close();
        return lesPostes;
    }
}


