package classement;

import mapping.Sac;

public class SacBeneficeVente {
    Sac sac;
    double rentabilite;
    double vente;
    double ca;
    double prixVenteUnitaire;

    public SacBeneficeVente(int idSac, String nomSac, double rentabilite, double quantite, double pvu) {
        setRentabilite(rentabilite);
        setVente(quantite);
        setSac(new Sac(idSac, nomSac));
        setPrixVenteUnitaire(pvu);
    }
    public Sac getSac() {
        return sac;
    }
    public void setSac(Sac sac) {
        this.sac = sac;
    }
    public double getRentabilite() {
        return rentabilite;
    }
    public void setRentabilite(double rentabilite) {
        this.rentabilite = rentabilite;
    }
    public double getVente() {
        return vente;
    }
    public void setVente(double vente) {
        this.vente = vente;
    }

    public double getCa() {
        return ca;
    }

    public void setCa(double ca) {
        this.ca = ca;
    }

    public void setPrixVenteUnitaire(double prixVenteUnitaire) {
        this.prixVenteUnitaire = prixVenteUnitaire;
    }
    public double getPrixVenteUnitaire() {
        return prixVenteUnitaire;
    }
}
