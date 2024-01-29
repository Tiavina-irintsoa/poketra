package stock.matiere;

import mapping.Matiere;

public class StockMatiere {
    Matiere matiere;
    double stockDate1;
    double stockDate2;
    double montantTotal;
    public StockMatiere(int id, String nom, double prixunitaire, double stockDate1, double stockDate2, double montantTotal) {
        this.matiere = new Matiere(id, nom,prixunitaire);
        this.stockDate1 = stockDate1;
        this.stockDate2 = stockDate2;
        this.montantTotal = montantTotal;
    }
    public Matiere getMatiere() {
        return matiere;
    }
    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }
    public double getStockDate1() {
        return stockDate1;
    }
    public void setStockDate1(double stockDate1) {
        this.stockDate1 = stockDate1;
    }
    public double getStockDate2() {
        return stockDate2;
    }
    public void setStockDate2(double stockDate2) {
        this.stockDate2 = stockDate2;
    }
    public double getMontantTotal() {
        return montantTotal;
    }
    public void setMontantTotal(double montantTotal) {
        this.montantTotal = montantTotal;
    }
}
