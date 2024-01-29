package search;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import connexion.Connect;
import mapping.Sac;

public class RecherchePrix {
    double prixMin;
    double prixMax;
    Vector<Sac> result;

    public RecherchePrix (String min, String max){
        setPrixMax(max);
        setPrixMin(min);
    }
    public void setPrixMin(String min) {
        setPrixMin(Double.valueOf(min));
    }
    public void setPrixMax(String max) {
        setPrixMax(Double.valueOf(max));
    }
    public void completeResult(Connection connect) throws Exception{
        boolean connexionOuvert=false;
       if (connect == null) {
           connexionOuvert=true;
           Connect myConnect=new Connect();
           connect=myConnect.getConnectionPostgresql();
       }
       Statement state=connect.createStatement();
       String sql="SELECT * FROM v_cout_matiere_sac where prix <= "+prixMax+" and prix >="+prixMin+" order by prix asc";
       try {
           ResultSet resultSet= state.executeQuery(sql);
           this.result=new Vector<Sac>();

           while (resultSet.next()) {
               result.add(new Sac(resultSet.getString("nom_sac"), resultSet.getDouble("prix")));
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
    public double getPrixMin() {
        return prixMin;
    }
    public void setPrixMin(double prixMin) {
        this.prixMin = prixMin;
    }
    public double getPrixMax() {
        return prixMax;
    }
    public void setPrixMax(double prixMax) {
        this.prixMax = prixMax;
    }
    public Vector<Sac> getResult() {
        return result;
    }
    public void setResult(Vector<Sac> result) {
        this.result = result;
    }
}
