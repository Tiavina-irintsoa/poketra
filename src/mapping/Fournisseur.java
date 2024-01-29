package mapping;

public class Fournisseur {
    int idFournisseur;
    String nom;
    String contact;
    public Fournisseur(String idfournisseur){
        setIdFournisseur(idfournisseur);
    }
    public void setIdFournisseur(String idFournisseur){
        setIdFournisseur(Integer.valueOf(idFournisseur));
    }
    public int getIdFournisseur() {
        return idFournisseur;
    }
    public void setIdFournisseur(int idFournisseur) {
        this.idFournisseur = idFournisseur;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getContact() {
        return contact;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }

    public Fournisseur(){}

    public Fournisseur(String nom, String contact) {
        this.nom = nom;
        this.contact = contact;
    }
}
