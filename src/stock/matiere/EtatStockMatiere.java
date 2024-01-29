package stock.matiere;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Vector;

import connexion.Connect;
import mapping.Sac;

public class EtatStockMatiere {
    LocalDate date1=LocalDate.now().minusDays(1);
    LocalDate date2=LocalDate.now();
    Vector<StockMatiere> stockMatieres;

    public EtatStockMatiere(String date1, String date2){
        setDate1(date1);
        setDate2(date2);
    }
    public void completeData(Connection connect) throws Exception{
        boolean connexionOuvert=false;
       if (connect == null) {
           connexionOuvert=true;
           Connect myConnect=new Connect();
           connect=myConnect.getConnectionPostgresql();
       }
       Statement state=connect.createStatement();
       String sql="select * from f_etat_stock_matiere('"+date1+"','"+date2+"')";
       System.out.println(sql);
       try {
           ResultSet resultSet= state.executeQuery(sql);
           this.stockMatieres=new Vector<StockMatiere>();

           while (resultSet.next()) {
               stockMatieres.add(new StockMatiere(resultSet.getInt("id"),resultSet.getString("nom"), resultSet.getDouble("prix_unitaire"), resultSet.getDouble("stock_date1"), resultSet.getDouble("stock_date2"),resultSet.getDouble("montant_total")));
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
    private void setDate1(String date1) {
        if(date1!=null){
            setDate1(LocalDate.parse(date1));
        }
    }
    private void setDate2(String date2) {
        if(date2!=null){
            setDate2(LocalDate.parse(date2));
        }
    }
    public LocalDate getDate1() {
        return date1;
    }
    public void setDate1(LocalDate date1) {
        this.date1 = date1;
    }
    public LocalDate getDate2() {
        return date2;
    }
    public void setDate2(LocalDate date2) {
        this.date2 = date2;
    }
    public Vector<StockMatiere> getStockMatieres() {
        return stockMatieres;
    }
    public void setStockMatieres(Vector<StockMatiere> stockMatieres) {
        this.stockMatieres = stockMatieres;
    }

}
