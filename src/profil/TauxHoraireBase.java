package profil;

public class TauxHoraireBase {
    int idTaux;
    double taux;

    public TauxHoraireBase() {
    }

    public double getTaux() {
        return taux;
    }

    public void setTaux(double taux) {
        this.taux = taux;
    }

    public int getIdTaux() {
        return idTaux;
    }

    public void setIdTaux(int idTaux) {
        this.idTaux = idTaux;
    }

    public TauxHoraireBase(double taux) {
        this.taux = taux;
    }
}
