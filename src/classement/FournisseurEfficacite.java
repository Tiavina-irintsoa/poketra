package classement;

import mapping.Fournisseur;

public class FournisseurEfficacite {
    Fournisseur fournisseur;
    double prixUnitaire;
    double qualite;
    double penalite;
    double rapportQualitePrix;
    double qualiteService;
    double variationQualite;
    public FournisseurEfficacite(int idFournisseur, String nomFournisseur, double qualite, double penalite, double rapportQualitePrix,
            double qualite_service, double qualiteVariation, double prixUnitaire) {
            setFournisseur(new Fournisseur( idFournisseur, nomFournisseur));
            setQualite(qualite);
            setPenalite(penalite);
            setRapportQualitePrix(rapportQualitePrix);
            setQualiteService(qualite_service);
            setVariationQualite(qualiteVariation);
            setPrixUnitaire(prixUnitaire);
    }
    public Fournisseur getFournisseur() {
        return fournisseur;
    }
    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }
    public double getPrixUnitaire() {
        return prixUnitaire;
    }
    public void setPrixUnitaire(double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }
    public double getRapportQualiteService() {
        return rapportQualitePrix;
    }
    public void setRapportQualitePrix(double rapportQualitePrix) {
        this.rapportQualitePrix = rapportQualitePrix;
    }
    public double getQualiteService() {
        return qualiteService;
    }
    public void setQualiteService(double qualiteService) {
        this.qualiteService = qualiteService;
    }
    public double getVariationQualite() {
        return variationQualite;
    }
    public void setVariationQualite(double variationQualite) {
        this.variationQualite = variationQualite;
    }
    public double getQualite() {
        return qualite;
    }
    public void setQualite(double qualite) {
        this.qualite = qualite;
    }
    public double getPenalite() {
        return penalite;
    }
    public void setPenalite(double penalite) {
        this.penalite = penalite;
    }
    public double getRapportQualitePrix() {
        return rapportQualitePrix;
    }
}
