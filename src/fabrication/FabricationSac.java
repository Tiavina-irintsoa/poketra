package fabrication;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import connexion.Connect;
import mapping.Sac;
public class FabricationSac {
    Sac sac;
    double quantite;
    Date date;
    Vector<MatiereStock> utilise;
    Vector<MouvementStockMatiere> UtilisationMatieres;
    public Sac getSac() {
        return sac;
    }
    public void setSac(Sac sac) {
        this.sac = sac;
    }
    public double getQuantite() {
        return quantite;
    }
    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public Vector<MouvementStockMatiere> getUtilisationMatieres() throws Exception{
        Vector<MouvementStockMatiere> utilisationMatieres = new Vector<MouvementStockMatiere>()
        ;
        for (MatiereStock matiere : utilise) {
            if(matiere.isEnough()){
                utilisationMatieres.add(new MouvementStockMatiere(matiere.getMatiere(),0,matiere.getQuantiteUtilise(),0,this.date));
            }
            else{
                throw new Exception(matiere.generateExceptionMessage());
            }
        }
        return utilisationMatieres;
    }
    public FabricationSac(String idsac, String quantite, String date){
        setSac(new Sac(idsac));
        setQuantite(quantite);
        setDate(Date.valueOf(date));
    }
    public void setQuantite(String quantite2) {
        setQuantite(Double.valueOf(quantite2));
    }
    public void fabriquer() throws Exception{
        Connect con = new Connect();
        Connection connect = con.getConnectionPostgresql();
        try {
            getUtilise(connect);
            this.UtilisationMatieres = getUtilisationMatieres();
            insertUtilisation(connect);
            insert(connect);
            connect.commit();
        } catch (Exception e) {
            connect.rollback();
            throw e;
        }
        finally{
            connect.close();
        }
    }
    public void insert(Connection connect) throws Exception{
        boolean connexionOuvert=false;
        if (connect == null) {
            connexionOuvert=true;
            Connect myConnect=new Connect();
            connect=myConnect.getConnectionPostgresql();
        }
        String sql = "insert into fabrication values (default, ?, ?,?)";
        PreparedStatement state=connect.prepareStatement(sql);
        state.setInt(1, getSac().getIdSac());
        state.setDouble(2, getQuantite());
        state.setDate(3, date);
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
    public void insertUtilisation(Connection con) throws Exception{
        for (MouvementStockMatiere u : UtilisationMatieres) {
            u.insert(con);
        }
    }
    public void getUtilise(Connection connect) throws Exception{
        boolean connexionOuvert=false;
       if (connect == null) {
           connexionOuvert=true;
           Connect myConnect=new Connect();
           connect=myConnect.getConnectionPostgresql();
       }
       Statement state=connect.createStatement();
       String sql="SELECT * FROM v_quantite_matiere_stock_besoin where id_sac = "+getSac().getIdSac();
       try {
           ResultSet res= state.executeQuery(sql);
           this.utilise=new Vector<MatiereStock>();

           while (res.next()) {
               utilise.add(new MatiereStock(res.getInt("id_matiere"), res.getString("nom_matiere"), res.getDouble("reste"), res.getDouble("quantite")*getQuantite()));
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
    public Vector<MatiereStock> getUtilise() {
        return utilise;
    }
}
