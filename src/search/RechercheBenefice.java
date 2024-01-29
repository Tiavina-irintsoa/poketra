package search;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import connexion.Connect;
import mapping.Sac;

public class RechercheBenefice {
double min;
    double max;
    Vector<Sac> resultat;

    public RechercheBenefice(String min, String max){
        setMin(Double.valueOf(min));
        setMax(Double.valueOf(max));
    }
    public void getResults(Connection connect) throws Exception{
        boolean connexionOuvert=false;
        if (connect == null) {
            connexionOuvert=true;
            Connect myConnect=new Connect();
            connect=myConnect.getConnectionPostgresql();
        }
        Statement state=connect.createStatement();
        String sql="SELECT * FROM v_benefice where benefice <= "+max+" and benefice >="+min+" order by benefice asc";
        try {
            ResultSet resultSet= state.executeQuery(sql);
            this.resultat=new Vector<Sac>();
 
            while (resultSet.next()) {
                Sac sac = new Sac(
                    resultSet.getInt("id_sac"),
                    resultSet.getString("nom_sac"),
                    resultSet.getDouble("cout_matiere"),
                    resultSet.getDouble("cout_rh"),
                    resultSet.getDouble("cout"),
                    resultSet.getDouble("prix_vente"),
                    resultSet.getDouble("benefice")
                );
                resultat.add(sac);
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
    public double getMin() {
        return min;
    }
    public void setMin(double min) {
        this.min = min;
    }
    public double getMax() {
        return max;
    }
    public void setMax(double max) {
        this.max = max;
    }
    public Vector<Sac> getResultat() {
        return resultat;
    }
    public void setResultat(Vector<Sac> resultat) {
        this.resultat = resultat;
    }
    
}
