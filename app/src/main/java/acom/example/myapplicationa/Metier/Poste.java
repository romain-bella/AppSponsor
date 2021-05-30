package acom.example.myapplicationa.Metier;

public class Poste {
    private int id_poste;
    private String nom_poste;

    public Poste(int id, String nom){
        id_poste=id;
        nom_poste=nom;
    }

    public String getNom_poste() {
        return nom_poste;
    }

    public int getId_poste() {
        return id_poste;
    }

    public void setNom_poste(String nom_poste) {
        this.nom_poste = nom_poste;
    }

    @Override
    public String toString() {
        return "Poste{" +
                "id_poste=" + id_poste +
                ", nom_poste='" + nom_poste + '\'' +
                '}';
    }
}
