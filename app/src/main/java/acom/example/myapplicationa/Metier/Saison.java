package acom.example.myapplicationa.Metier;

import java.util.Date;

public class Saison {
    private int id_saison;
    private Date dateDebut;
    private Date dateFin;

    public Saison (int id, Date datd, Date datef){
        id_saison=id;
        dateDebut=datd;
        dateFin=datef;
    }

    public int getId_saison() {
        return id_saison;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    @Override
    public String toString() {
        return "Saison{" +
                "id_saison=" + id_saison +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                '}';
    }
}
