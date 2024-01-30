package fabrication;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Types;

import connexion.Connect;
import mapping.Fournisseur;
import mapping.Matiere;
public class MouvementStockMatiere {
    Matiere matiere;
    double quantiteEnPlus;
    double quantiteEnMoins;
    double prixUnitaire;
    Date date;
    int qualite;
    Fournisseur fournisseur;
    public Matiere getMatiere() {
        return matiere;
    }
    public MouvementStockMatiere(String matiere, String date, String quantitePlus,String idfournisseur, String prix, String qualite){
        setMatiere(new Matiere(matiere));
        setDate(Date.valueOf(date));
        setQuantiteEnPlus(quantitePlus);
        System.out.println(idfournisseur);
        setFournisseur(new Fournisseur(idfournisseur));
        setPrixUnitaire(Double.valueOf(prix));
        setQualite(Integer.valueOf(qualite));
    }
    public void setQuantiteEnPlus(String quantitePlus) {
        setQuantiteEnPlus(Double.valueOf(quantitePlus));
    }
    public void insert(Connection connect) throws Exception{
        boolean connexionOuvert=false;
        if (connect == null) {
            connexionOuvert=true;
            Connect myConnect=new Connect();
            connect=myConnect.getConnectionPostgresql();
        }

        String sql = "insert into mouvement_stock (id_mouvement, id_matiere, quantite_plus,quantite_moins,date,prix_unitaire,id_fournisseur,qualite) values (default, ?, ?,?,?, ?,?,?)";
        PreparedStatement state=connect.prepareStatement(sql);
        state.setInt(1, matiere.getIdMatiere());
        state.setDouble(2, getQuantiteEnPlus());
        state.setDouble(3, getQuantiteEnMoins());
        state.setDate(4, getDate());
        state.setDouble(5, getPrixUnitaire());
        System.out.println(getFournisseur());
        if(getFournisseur() == null){
            System.out.println(Types.INTEGER);
            state.setNull(6, java.sql.Types.INTEGER);
        }
        else if(getFournisseur()!=null){
            state.setInt(6,getFournisseur().getIdFournisseur());
        }
        state.setInt(7, qualite);
        try {
            state.executeUpdate();
            if (connexionOuvert) {
                connect.commit();
            }
        } catch (Exception e) {
            if (connexionOuvert) {
                connect.rollback();
            }
            throw e;
        }finally{
            state.close();
            if (connexionOuvert) {
                connect.close();
            }
        }
        }
    public MouvementStockMatiere(Matiere matiere, double quantiteEnPlus, double quantiteEnMoins, double prixUnitaire, Date date){
        setMatiere(matiere);
        setQuantiteEnPlus(quantiteEnPlus);
        setQuantiteEnMoins(quantiteEnMoins);
        setPrixUnitaire(prixUnitaire);
        setDate(date);
    }
    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }
    public double getQuantiteEnPlus() {
        return quantiteEnPlus;
    }
    public void setQuantiteEnPlus(double quantiteEnPlus) {
        this.quantiteEnPlus = quantiteEnPlus;
    }
    public double getQuantiteEnMoins() {
        return quantiteEnMoins;
    }
    public void setQuantiteEnMoins(double quantiteEnMoins) {
        this.quantiteEnMoins = quantiteEnMoins;
    }
    public double getPrixUnitaire() {
        return prixUnitaire;
    }
    public void setPrixUnitaire(double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public Fournisseur getFournisseur() {
        return fournisseur;
    }
    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }
    public int getQualite() {
        return qualite;
    }
    public void setQualite(int qualite) {
        this.qualite = qualite;
    }

}
