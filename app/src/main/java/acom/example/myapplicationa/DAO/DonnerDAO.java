package acom.example.myapplicationa.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import acom.example.myapplicationa.Metier.Correspondant;
import acom.example.myapplicationa.Metier.Donner;
import acom.example.myapplicationa.Metier.Entreprise;
import acom.example.myapplicationa.Metier.Poste;
import acom.example.myapplicationa.Metier.Saison;

public class DonnerDAO extends DAO <Donner> {
    private SQLiteSponsor dbJudo;
    private static final String Table_Donner= "Donner";
    private static final String Col_Id_Saison = "id_saison";
    private static final String Col_Id_Correspondant = "id_correspondant";
    private static final String Col_MontantD = "montantDon";
    private static final String Col_dateD = "dateDon";
    private SQLiteDatabase db;

    public DonnerDAO(Context context) {
        dbJudo = new SQLiteSponsor(context);
    }

    public void open() {
        db = dbJudo.getWritableDatabase();
    }

    public void close() {
        dbJudo.close();
    }

    public void insert(Donner donner) {
        db = dbJudo.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Col_Id_Saison, donner.getSo().getId_saison());
        values.put(Col_Id_Correspondant, donner.getCo().getId_correspondant());
        values.put(Col_MontantD, donner.getMontant());
        values.put(Col_dateD, donner.getDateD());
        db.insert(Table_Donner, null, values);
        db.close();
    }

    public void update(Donner donner) {
        open();
        ContentValues values = new ContentValues();
        values.put(Col_Id_Saison, donner.getSo().getId_saison());
        values.put(Col_Id_Correspondant, donner.getCo().getId_correspondant());
        values.put(Col_MontantD, donner.getMontant());
        values.put(Col_dateD, donner.getDateD());
        db.update(Table_Donner, values, Col_Id_Saison+"="+donner.getSo().getId_saison()+" and "+ Col_Id_Correspondant+"="+donner.getCo().getId_correspondant(), null);
        db.close();

    }

    public void delete(Donner donner) {
        open();
        db.delete(Table_Donner,Col_Id_Saison+"="+donner.getSo().getId_saison()+" and "+ Col_Id_Correspondant+"="+donner.getCo().getId_correspondant(),null);
        db.close();
    }

    public int last(){
        open();
        Cursor curseur = db.query (Table_Donner, null,null,null,null,null,null);
        curseur.moveToLast();
        int i = curseur.getInt(0);
        curseur.close();
        close();
        return i;
    }

    /*public Donner read(long idS, long idC) {
        open();
        Donner donner;
        db.isOpen();
        Cursor curseur = db.query(Table_Donner, null, Col_Id_Saison+"="+idS+" and "+ Col_Id_Correspondant+"="+idC,null,null,null,null);
        curseur.moveToFirst();
        donner = new Donner(curseur.getInt(0), curseur.getString(1), curseur.getString(2));
        curseur.close();
        close();
        return laSa;
    }*/

    public ArrayList<Donner> read(){
        open();
        Donner unD;
        Correspondant unC;
        Entreprise uneEntreprise;
        Poste unPoste;
        Saison uneS;
        ArrayList<Donner> lesD = new ArrayList<Donner>();
        db.isOpen();
        //Ouverture du curseur pour la table des Correspondants
        Cursor curseurD = db.query(Table_Donner,null,null,null,null,null,null);

        if (curseurD.moveToFirst()) {do{

            Cursor curseurS = db.query("Saison", null, Col_Id_Saison+"=" + curseurD.getInt(0), null, null, null, null);
            if ( curseurS != null && curseurS.moveToFirst()) {

                uneS = new Saison(curseurS.getInt(0), curseurS.getString(1), curseurS.getString(2));
                Log.v("BLABLA","JE SUIS LA 2");
                curseurS.close();

                Cursor curseurC = db.query("Correspondant", null, Col_Id_Correspondant+"=" + curseurD.getInt(1), null, null, null, null);
                if (curseurC.moveToFirst()) {

                    Cursor curseurCE = db.query("Entreprise",null, "id_E="+curseurC.getInt(5),null,null,null,null);
                    if (curseurCE.moveToFirst()) {
                        uneEntreprise = new Entreprise(curseurCE.getInt(0), curseurCE.getString(1), curseurCE.getString(2), curseurCE.getString(3), curseurCE.getString(4), curseurCE.getString(5), curseurCE.getString(6));
                        curseurCE.close();
                        Cursor curseurCP = db.query("Poste",null, "id_poste="+curseurC.getInt(6),null,null,null,null);
                    if (curseurCP.moveToFirst()) {
                        unPoste = new Poste(curseurCP.getInt(0), curseurCP.getString(1));
                    unC = new Correspondant(curseurC.getInt(0), curseurC.getString(1), curseurC.getString(2), curseurC.getString(3), curseurC.getString(4), uneEntreprise, unPoste);
                    Log.v("BLABLA","JE SUIS LA 3");

                    unD = new Donner(uneS, unC, curseurC.getInt(2), curseurC.getString(3));
                    lesD.add(unD);
                    //curseurC.moveToNext();
                        curseurCP.close();
                        curseurC.close();
                }}}}

            curseurS.close();
        }while(curseurD.moveToNext());
        }

        curseurD.close();
        close();
        return lesD;
    }
}
