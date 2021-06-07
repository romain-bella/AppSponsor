package acom.example.myapplicationa.Metier;

import java.util.Date;

public class Saison {
    private int id_saison;
    private String dateDebut;
    private String dateFin;

    public Saison (int id, String datd, String datef){
        id_saison=id;
        dateDebut=datd;
        dateFin=datef;
    }

    public int getId_saison() {
        return id_saison;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    @Override
    public String toString() {
        return id_saison +
                " " + dateDebut +
                "-" + dateFin;
    }
}
