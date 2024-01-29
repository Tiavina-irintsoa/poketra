package stats;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Vector;

import connexion.Connect;
import mapping.Sac;

public class StatistiqueVenteGlobal {
    String annee;
    String idSac;
    StatistiqueVente statistiqueVente;
    StatistiqueParMois statistiqueParMois;
    StatistiquesParGenre statistiquesParGenre;
    Vector<Sac> sacs;
    Vector<Integer> annees;
    public StatistiqueVenteGlobal(String annee, String idSac) {
        if(annee==null){
            this.annee = String.valueOf(LocalDate.now().getYear());
        }
        else{
            this.annee = annee;
        }
        if(idSac==null){
            this.idSac = "0";
        }
        else{
            this.idSac = idSac;
        }
    
    }
    public Vector<Integer> getAnnee(Connection connect) throws Exception{
        boolean connexionOuvert=false;
        if (connect == null) {
            connexionOuvert=true;
            Connect myConnect=new Connect();
            connect=myConnect.getConnectionPostgresql();
        }

        Statement state=connect.createStatement();
        String sql="SELECT annee FROM v_annee_vente order by annee asc";
        try {
            ResultSet result= state.executeQuery(sql);
            Vector<Integer> val=new Vector<Integer>();
            while (result.next()) {
                val.add(result.getInt("annee"));
            }
            return val;
        } catch (Exception e) {
            throw e;
        }finally{
            state.close();
            if (connexionOuvert) {
                connect.close();
            }
        }
    }
    public void completeData() throws Exception{
        Connect con = new Connect();
        Connection connect = con.getConnectionPostgresql();
        try {
            this.statistiquesParGenre = new StatistiquesParGenre(idSac);
            this.statistiquesParGenre.getStat(connect);
            statistiqueParMois = new StatistiqueParMois(idSac, annee);
            this.statistiqueParMois.completeData(connect);
            this.statistiqueVente = new StatistiqueVente(idSac);
            statistiqueVente.completeData(connect);
            this.sacs = Sac.list(connect);
            this.annees = getAnnee(connect);
        } catch (Exception e) {
            throw e;
        }
        finally{
            connect.close();
        }
    }
    public StatistiqueVente getStatistiqueVente() {
        return statistiqueVente;
    }
    public void setStatistiqueVente(StatistiqueVente statistiqueVente) {
        this.statistiqueVente = statistiqueVente;
    }
    public StatistiqueParMois getStatistiqueParMois() {
        return statistiqueParMois;
    }
    public void setStatistiqueParMois(StatistiqueParMois statistiqueParMois) {
        this.statistiqueParMois = statistiqueParMois;
    }
    public StatistiquesParGenre getStatistiquesParGenre() {
        return statistiquesParGenre;
    }
    public void setStatistiquesParGenre(StatistiquesParGenre statistiquesParGenre) {
        this.statistiquesParGenre = statistiquesParGenre;
    }
    public String getAnnee() {
        return annee;
    }
    public void setAnnee(String annee) {
        this.annee = annee;
    }
    public String getIdSac() {
        return idSac;
    }
    public void setIdSac(String idSac) {
        this.idSac = idSac;
    }
    public Vector<Sac> getSacs() {
        return sacs;
    }
    public void setSacs(Vector<Sac> sacs) {
        this.sacs = sacs;
    }
    public Vector<Integer> getAnnees() {
        return annees;
    }
    public void setAnnees(Vector<Integer> annees) {
        this.annees = annees;
    }
}
