package acom.example.myapplicationa.Metier;

public class Donner {

    private Saison So;
    private Correspondant Co;
    private float montantDon;
    private String dateDon;

    public Donner (Saison unSo, Correspondant unCo,  float montant, String dd){
        So=unSo;
        Co=unCo;
        this.montantDon=montant;
        dateDon=dd;
    }

    public Correspondant getCo() {
        return Co;
    }

    public Saison getSo() {
        return So;
    }

    public float getMontant() {
        return montantDon ;
    }

    public String getDateD() {
        return dateDon;
    }

    public void setCo(Correspondant co) {
        this.Co = co;
    }

    public void setSo(Saison so) {
        this.So = so;
    }

    public void setDateD(String dateD) {
        this.dateDon = dateD;
    }

    public void setMontant(float montant) {
        this.montantDon = montant;
    }

    @Override
    public String toString() {
        return Co.getNom_co()+" " + Co.getPrenom_co() +
                " à, lors de la saison " + So.getDateDebut() + "-" + So.getDateFin()+
                " donné " + montantDon + " le " + dateDon;
    }
}
