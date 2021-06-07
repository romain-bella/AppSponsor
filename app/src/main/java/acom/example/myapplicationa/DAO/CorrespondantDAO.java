package acom.example.myapplicationa.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import acom.example.myapplicationa.IHM.ActivityCorrespondant;
import acom.example.myapplicationa.Metier.Correspondant;
import acom.example.myapplicationa.Metier.Entreprise;
import acom.example.myapplicationa.Metier.Poste;
import acom.example.myapplicationa.Metier.Saison;

public class CorrespondantDAO extends DAO<Correspondant>{
    private SQLiteSponsor dbJudo;
    private static final String Table_Correspondant= "Correspondant";
    private static final String Col_Id_Correspondant = "id_correspondant";
    private static final String Col_nom = "nom";
    private static final String Col_prenom = "prenom";
    private static final String Col_tel = "telephone";
    private static final String Col_mail = "mail";
    private static final String Col_ent = "id_E";
    private static final String Col_post = "id_poste";
    private SQLiteDatabase db;

    public CorrespondantDAO(Context context) {
        dbJudo = new SQLiteSponsor(context);
    }

    public void open() {
        db = dbJudo.getWritableDatabase();
    }

    public void close() {
        dbJudo.close();
    }

    public void insert(Correspondant co) {
        db = dbJudo.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Col_Id_Correspondant, co.getId_correspondant());
        values.put(Col_nom, co.getNom_co());
        values.put(Col_prenom, co.getPrenom_co());
        values.put(Col_tel, co.getTelephone());
        values.put(Col_mail, co.getMail());
        values.put(Col_ent, co.getUneEntreprise().getId_E());
        values.put(Col_post, co.getUnPoste().getId_poste());
        db.insert(Table_Correspondant, null, values);
        db.close();
    }

    public void update(Correspondant co) {
        open();
        ContentValues values = new ContentValues();
        values.put(Col_Id_Correspondant, co.getId_correspondant());
        values.put(Col_nom, co.getNom_co());
        values.put(Col_prenom, co.getPrenom_co());
        values.put(Col_tel, co.getTelephone());
        values.put(Col_mail, co.getMail());
        values.put(Col_ent, co.getUneEntreprise().toString());
        values.put(Col_post, co.getUnPoste().toString());
        db.update(Table_Correspondant, values, Col_Id_Correspondant+"="+co.getId_correspondant(),null);
        db.close();

    }

    public void delete(Correspondant co) {
        open();
        db.delete(Table_Correspondant,Col_Id_Correspondant+"="+co.getId_correspondant(),null);
        db.close();
    }

    public void deleteId(int id){
        open();
        db.rawQuery("delete from Correspondant where id_correspondant = "+id+";",null);
        Log.v("BLABLA","SUPP REUSSIT");
        close();
    }

    public int last(){
        open();
        Cursor curseur = db.query (Table_Correspondant, null,null,null,null,null,null);
        curseur.moveToLast();
        int i = curseur.getInt(0);
        curseur.close();
        close();
        return i;
    }

    public int compte(){
        int nbCo = 0;
        open();
        Cursor curseur = db.query(Table_Correspondant,null,null,null,null,null,null,null);
        curseur.moveToFirst();
        nbCo = curseur.getCount();
        close();
        return nbCo;
    }

    /* Correspondant read(long id, Context context) {
        open();
        Correspondant Co = null;
        Entreprise uneEntreprise;
        Poste unPoste;
        EntrepriseDAO bddEn;
        PosteDAO bddPo;
        db.isOpen();
        Cursor curseurC = db.query(Table_Correspondant, null, Col_Id_Correspondant + "=" + id, null, null, null, null);
        bddPo = new PosteDAO(context);
        bddEn = new EntrepriseDAO(context);
        if (curseurC.moveToFirst()) {
            bddEn.open();
            unPoste = new Poste(curseurC.getString(5).)


        Co = new Correspondant(curseurC.getInt(0), curseurC.getString(1), curseurC.getString(2), curseurC.getString(3), curseurC.getString(4), uneEntreprise, unPoste);
        curseurC.close();
        return Co;
    }*/

    public ArrayList<Correspondant> read(){
        open();
        Correspondant unCorrespondant;
        Entreprise uneEntreprise;
        Poste unPoste;
        ArrayList<Correspondant> lesCorrespondants = new ArrayList<Correspondant>();
        db.isOpen();
        //Ouverture du curseur pour la table des Correspondants
        Cursor curseurC = db.query(Table_Correspondant,null,null,null,null,null,null);

        if (curseurC.moveToFirst()) {do{
            //curseurC.moveToFirst();
            Log.v("BLABLA","JE SUIS LA 1");

            Log.v("BLABLA",curseurC.getCount()+"");
            //while (curseurC.isAfterLast() == false ){
            //for (int i = 1; i <= curseurC.getCount(); i++) {
                //Ouverture du curseur pour la table des Entreprises spécifique au correspondant actuel
                Cursor curseurE = db.query("Entreprise", null, "id_E=" + curseurC.getInt(5), null, null, null, null);
                if (curseurE.moveToFirst()) {
                    //curseurE.moveToFirst();
                    uneEntreprise = new Entreprise(curseurE.getInt(0), curseurE.getString(1), curseurE.getString(2), curseurE.getString(3), curseurE.getString(4), curseurE.getString(5), curseurE.getString(6));
                    Log.v("BLABLA","JE SUIS LA 2");
                curseurE.close();
                //Ouverture du curseur pour la table des Postes spécifique au correspondant actuel
                Cursor curseurP = db.query("Poste", null, "id_poste=" + curseurC.getInt(6), null, null, null, null);
                if (curseurP.moveToFirst()) {
                    //curseurP.moveToFirst();
                    unPoste = new Poste(curseurP.getInt(0), curseurP.getString(1));
                    Log.v("BLABLA","JE SUIS LA 3");
                    curseurP.close();
                    unCorrespondant = new Correspondant(curseurC.getInt(0), curseurC.getString(1), curseurC.getString(2), curseurC.getString(3), curseurC.getString(4), uneEntreprise, unPoste);
                    lesCorrespondants.add(unCorrespondant);
                    //curseurC.moveToNext();
                }}

            }while(curseurC.moveToNext());
        }

        curseurC.close();
        close();
        return lesCorrespondants;
    }

}
