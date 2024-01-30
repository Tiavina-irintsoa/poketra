package mapping;

import java.sql.Connection;
import java.sql.Statement;
import java.time.LocalDate;

import connexion.Connect;

public class Penalisation {
    int idFournisseur;
    int idCause;
    LocalDate date = LocalDate.now();
    double valeur;

    public Penalisation(String idFournisseur, String idCause, String date){
        setIdFournisseur(Integer.valueOf(idFournisseur));
        setIdCause(Integer.valueOf(idCause));
        setDate(LocalDate.parse(date));
    }
    public void insert(Connection connect) throws Exception{
        boolean connexionOuvert=false;
        if (connect == null) {
            connexionOuvert=true;
            Connect myConnect=new Connect();
            connect=myConnect.getConnectionPostgresql();
        }
        CausePenalisation cause = new CausePenalisation(idCause, idCause);
        cause.getById(connect);
        setValeur(cause.getValeurActuelle());
        Statement state=connect.createStatement();
        String sql="INSERT INTO penalisation values  ("+this.getIdFournisseur()+","+this.idCause+","+this.valeur+",'"+this.date+"')";
        try {
            state.executeUpdate(sql);
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
    public int getIdFournisseur() {
        return idFournisseur;
    }
    public void setIdFournisseur(int idFournisseur) {
        this.idFournisseur = idFournisseur;
    }
    public int getIdCause() {
        return idCause;
    }
    public void setIdCause(int idCause) {
        this.idCause = idCause;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public double getValeur() {
        return valeur;
    }
    public void setValeur(double valeur) {
        this.valeur = valeur;
    }
    
}
