package acom.example.myapplicationa.Metier;

import java.util.ArrayList;

public class Entreprise {
    private int id_E;
    private String raison_sociale;
    private String ville;
    private String rue;
    private String CP;
    private String telephone;
    private String mail;
    private ArrayList<Correspondant> lesCorrespondants;

    public Entreprise(int id, String rs, String ville, String rue, String CP, String tel, String mail){
        id_E=id;
        raison_sociale=rs;
        this.ville=ville;
        this.rue=rue;
        this.CP=CP;
        telephone=tel;
        this.mail=mail;
        lesCorrespondants = new ArrayList<Correspondant>();
    }

    public int getId_E() {
        return id_E;
    }

    public String getRaison_sociale() {
        return raison_sociale;
    }

    public String getVille() {
        return ville;
    }

    public String getRue() {
        return rue;
    }

    public String getCP() {
        return CP;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getMail() {
        return mail;
    }

    public void setRaison_sociale(String raison_sociale) {
        this.raison_sociale = raison_sociale;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public void setCP(String CP) {
        this.CP = CP;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setLesCorrespondants(Correspondant uncor) {
        this.lesCorrespondants.add(uncor);
    }

    public ArrayList<Correspondant> getLesCorrespondants() {
        return lesCorrespondants;
    }

    @Override
    public String toString() {
        return id_E +
                " " + raison_sociale + " " +
                ville + " " +
                rue + " " +
                CP + " " +
                telephone + " " +
                mail;
    }
}
