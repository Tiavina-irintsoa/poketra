package stats;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;

import connexion.Connect;

public class StatistiqueParMois {
    int[] statMois;
    int idSac;
    int annee = LocalDate.now().getYear();

    public StatistiqueParMois(String sac, String annee){
        setIdSac(Integer.valueOf(sac));
        setAnnee(Integer.valueOf(annee));
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
        if(idSac!=0){
            sql = "select * from v_vente_produit_mois where id_Sac = "+this.getIdSac()+" and annee = "+this.getAnnee()+" order by mois asc";
        }
        else{
            sql = "select * from v_vente_tous_mois where annee = "+this.getAnnee()+" order by mois asc";
        }
        try {
            ResultSet result= state.executeQuery(sql);
            this.statMois = new int[12];
            int i = 0;
            while (result.next()) {
                statMois[i] = result.getInt("quantite");
                i++;
            }
         } catch (Exception e) {
            throw e;
        }finally{
            state.close();
            if (connexionOuvert) {
                connect.close();
            }
        }
    }
    public String getStatMoisString(){
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("[");
        
        for (int i = 0; i < statMois.length; i++) {
            jsonBuilder.append(statMois[i]);

            if (i < statMois.length - 1) {
                jsonBuilder.append(", ");
            }
        }
        jsonBuilder.append("]");

        return jsonBuilder.toString();
    }
    public int[] getStatMois() {
        return statMois;
    }
    public void setStatMois(int[] statMois) {
        this.statMois = statMois;
    }
    public int getIdSac() {
        return idSac;
    }
    public void setIdSac(int idSac) {
        this.idSac = idSac;
    }
    public int getAnnee() {
        return annee;
    }
    public void setAnnee(int annee) {
        this.annee = annee;
    }

    
}
