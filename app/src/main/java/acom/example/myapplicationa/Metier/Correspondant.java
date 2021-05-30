package acom.example.myapplicationa.Metier;

import java.util.HashMap;

public class Correspondant {
    private int id_correspondant;
    private String nom_co;
    private String prenom_co;
    private String telephone;
    private String mail;
    private Entreprise uneEntreprise;
    private Poste unPoste;
    private HashMap<Saison, Float> lesDonnations;

    public Correspondant(int id, String nom, String prenom, String tel, String mail, Entreprise uneE, Poste unP){
        id_correspondant=id;
        nom_co = nom;
        prenom_co = prenom;
        telephone = tel;
        this.mail = mail;
        uneEntreprise=uneE;
        unPoste=unP;
        lesDonnations = new HashMap<Saison, Float>();
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

    public HashMap<Saison, Float> getLesDonnations() {
        return lesDonnations;
    }

    public void setLesDonnations(Saison s, Float donnation) {
        this.lesDonnations.put(s, donnation);
    }

    @Override
    public String toString() {
        return "Correspondant{" +
                "id_correspondant=" + id_correspondant +
                ", nom_co='" + nom_co + '\'' +
                ", prenom_co='" + prenom_co + '\'' +
                ", telephone='" + telephone + '\'' +
                ", mail='" + mail + '\'' +
                ", uneEntreprise=" + uneEntreprise +
                ", unPoste=" + unPoste +
                '}';
    }
}
