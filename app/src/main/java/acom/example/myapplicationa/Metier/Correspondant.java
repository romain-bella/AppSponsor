package acom.example.myapplicationa.Metier;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;

public class Correspondant /*implements Parcelable*/ {
    private int id_correspondant;
    private String nom_co;
    private String prenom_co;
    private String telephone;
    private String mail;
    private Entreprise uneEntreprise;
    private Poste unPoste;


    public Correspondant(int id, String nom, String prenom, String tel, String mail, Entreprise uneE, Poste unP){
        id_correspondant=id;
        nom_co = nom;
        prenom_co = prenom;
        telephone = tel;
        this.mail = mail;
        uneEntreprise=uneE;
        unPoste=unP;

    }

    public Correspondant (Correspondant unCo){
        id_correspondant=unCo.getId_correspondant();
        nom_co=unCo.getNom_co();
        prenom_co=unCo.getPrenom_co();
        telephone=unCo.getTelephone();
        mail=unCo.getTelephone();
        uneEntreprise=unCo.getUneEntreprise();
        unPoste= unCo.getUnPoste();

    }

    public int getId_correspondant() {
        return id_correspondant;
    }

    public String getNom_co() {
        return nom_co;
    }

    public String getPrenom_co() {
        return prenom_co;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getMail() {
        return mail;
    }

    public Entreprise getUneEntreprise() {
        return uneEntreprise;
    }

    public Poste getUnPoste() {
        return unPoste;
    }

    public void setNom_co(String nom_co) {
        this.nom_co = nom_co;
    }

    public void setPrenom_co(String prenom_co) {
        this.prenom_co = prenom_co;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setUneEntreprise(Entreprise uneEntreprise) {
        this.uneEntreprise = uneEntreprise;
    }

    public void setUnPoste(Poste unPoste) {
        this.unPoste = unPoste;
    }



    @Override
    public String toString() {
        return id_correspondant+ " " + nom_co + " "+ prenom_co + " "+ telephone + " " + mail + " " +
                "  Entreprise: " + uneEntreprise.getRaison_sociale() +
                "  Poste: " + unPoste.getNom_poste();
    }

/*
    @Override
    public int describeContents() {
        return 0;
    }

    protected Correspondant(Parcel in) {
        this.id_correspondant = in.readInt();
        this.nom_co = in.readString();
        this.prenom_co = in.readString();
        this.telephone = in.readString();
        this.mail = in.readString();
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(id_correspondant);
        parcel.writeString(nom_co);
        parcel.writeString(prenom_co);
        parcel.writeString(telephone);
        parcel.writeString(mail);
    }

    public static final Parcelable.Creator<Correspondant> CREATOR = new Parcelable.Creator<Correspondant>() {
        @Override
        public Correspondant createFromParcel(Parcel source) {
            return new Correspondant(source);
        }

        @Override
        public Correspondant[] newArray(int size) {
            return new Correspondant[size];
        }
    };*/
}
