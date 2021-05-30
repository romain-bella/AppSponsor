package acom.example.myapplicationa.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;

public class SQLiteSponsor extends SQLiteOpenHelper {

    BufferedReader lecteur = null;
    private static final int DATABASE_VERSION=2;
    private static final String DATABASE_NAME="GestionSponsor";
    private Context context = null;

    public SQLiteSponsor(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    public void onCreate (SQLiteDatabase db){
        try{
            db.execSQL("DROP TABLE IF EXISTS Donner");
            db.execSQL("DROP TABLE IF EXISTS Correspondant");
            db.execSQL("DROP TABLE IF EXISTS Entreprise");
            db.execSQL("DROP TABLE IF EXISTS Poste");
            db.execSQL("DROP TABLE IF EXISTS Saison");
            db.execSQL("CREATE TABLE Poste (id_poste integer primary key autoincrement, nom_poste varchar(50))");
            db.execSQL("CREATE TABLE Entreprise (id_E integer primary key autoincrement, raison_sociale varchar(50), ville varchar (50), rue varchar(50), CP char(5), telephone char(10), mail varchar(50))");
            db.execSQL("CREATE TABLE Saison (id_saison integer primary key autoincrement, dateDebut date, dateFin date)");
            db.execSQL("CREATE TABLE Correspondant (id_correspondant integer primary key autoincrement, nom varchar(50), prenom varchar(50), telephone char(10), mail varchar(50), id_E integer, id_poste integer, constraint fk1_co FOREIGN KEY (id_E) REFERENCES Entreprise (id_E), constraint fk2_co FOREIGN KEY (id_poste) REFERENCES Poste (id_poste))");
            db.execSQL("CREATE TABLE Donner (id_saison integer, id_correspondant integer, montantDon REAL, dateDon date, constraint pk_donner PRIMARY KEY (id_saison, id_correspondant), constraint fk1_donner FOREIGN KEY (id_saison) REFERENCES Saison (id_saison), constraint fk2_donner FOREIGN KEY (id_correspondant) REFERENCES Correspondant (id_correspondant))");
            Log.v("Creation","Effecté");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion){
        try{
            lecteur = new BufferedReader (new InputStreamReader(context.getAssets().open("BASE")));
            int i=0;
            String ligne;
            Log.v("Mise a jour","Lecture du fichier OK");
            while ((ligne=lecteur.readLine())!=null){
                db.execSQL(ligne);
                i++;
                Log.v("Mise a jour","OK ligne "+i);
            }
            lecteur.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
