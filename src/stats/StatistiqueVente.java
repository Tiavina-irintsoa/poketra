package stats;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import connexion.Connect;

import connexion.Connect;

public class StatistiqueVente {
    double totalVente;
    double beneficeTotal;
    double chargeMatiere;
    double chargeRH;
    double chiffreAffaire;
    int idSac = 0;

    public StatistiqueVente(String idSac) {
        this.idSac = Integer.valueOf(idSac);
    }
    public double getCharge(){
        return chargeMatiere+chargeRH;
    }
    public void completeData(Connection connect) throws Exception{
        boolean connexionOuvert=false;
        if (connect == null) {
            connexionOuvert=true;
            Connect myConnect=new Connect();
            connect=myConnect.getConnectionPostgresql();
        }
        Statement state=connect.createStatement();
        String sql="";
        if(idSac==0){
            sql = "select * from v_benefice_vente_all";
        }
        else{
            sql = "select * from v_benefice_vente_sac where id_sac = "+this.idSac;
        }
        System.out.println(sql);
        try {
            ResultSet result= state.executeQuery(sql);
            result.next();

            setBeneficeTotal(result.getDouble("rentabilite"));
            setChargeMatiere(result.getDouble("cout_matiere"));
            setChargeRH(result.getDouble("cout_rh"));
            setChiffreAffaire(result.getDouble("ca"));
            setTotalVente(result.getDouble("quantite"));
         } catch (Exception e) {
            throw e;
        }finally{
            state.close();
            if (connexionOuvert) {
                connect.close();
            }
        }
    } 

    public double getTotalVente() {
        return totalVente; 
    }
    public void setTotalVente(double totalVente) {
        this.totalVente = totalVente;
    }
    public double getBeneficeTotal() {
        return beneficeTotal;
    }
    public void setBeneficeTotal(double beneficeTotal) {
        this.beneficeTotal = beneficeTotal;
    }
    public double getChargeMatiere() {
        return chargeMatiere;
    }
    public void setChargeMatiere(double chargeMatiere) {
        this.chargeMatiere = chargeMatiere;
    }
    public double getChargeRH() {
        return chargeRH;
    }
    public void setChargeRH(double chargeRH) {
        this.chargeRH = chargeRH;
    }
    public double getChiffreAffaire() {
        return chiffreAffaire;
    }
    public void setChiffreAffaire(double chiffreAffaire) {
        this.chiffreAffaire = chiffreAffaire;
    }
}
