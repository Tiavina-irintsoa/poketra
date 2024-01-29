package fabrication;

import mapping.Matiere;

public class MatiereStock {
    Matiere matiere;
    double stock;
    double quantiteUtilise;
    public Matiere getMatiere() {
        return matiere;
    }
    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }
    public boolean isEnough(){
        if(stock<quantiteUtilise){
            return false;
        }
        return true;
    }
    public String generateExceptionMessage(){
        return "Il manque "+(quantiteUtilise - stock)+" pour "+matiere.getNomMatiere();
    }
    public double getStock() {
        return stock;
    }
    public void setStock(double stock) {
        this.stock = stock;
    }
    public MatiereStock(int idMatiere, String nomMatiere,double stock, double quantite_utilise){
        setMatiere(new Matiere(idMatiere, nomMatiere));
        setStock(stock);
        setQuantiteUtilise(quantite_utilise);
    }
    public double getQuantiteUtilise() {
        return quantiteUtilise;
    }
    public void setQuantiteUtilise(double quantiteUtilise) {
        this.quantiteUtilise = quantiteUtilise;
    }
}
