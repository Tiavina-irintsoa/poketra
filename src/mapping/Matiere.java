package mapping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import connexion.Connect;

public class Matiere {
    int idMatiere;
    String nomMatiere;
    double prix;
    double stock;

    public int getIdMatiere() {
        return idMatiere;
    }
    public void setIdMatiere(int idMatiere) {
        this.idMatiere = idMatiere;
    }
        public void setIdMatiere(String idMatiere) {
        setIdMatiere(Integer.valueOf(idMatiere));
    }
    public String getNomMatiere() {
        return nomMatiere;
    }
    public void setNomMatiere(String nomMatiere) {
        this.nomMatiere = nomMatiere;
    }

    public Matiere(String idMatiere){
        setIdMatiere(idMatiere);
    }
    public Matiere(String id, String prix, boolean modify){
        setIdMatiere(id);
        setPrix(Double.valueOf(prix));
    }
    public Matiere(String nomMatiere,int const1, String prix){
        setNomMatiere(nomMatiere);
        setPrix(Double.valueOf(prix));
    }
    public void modifierPrixDenormalise(Connection connect) throws Exception{
        boolean connexionOuvert=false;
    if (connect == null) {
        connexionOuvert=true;
        Connect myConnect=new Connect();
        connect=myConnect.getConnectionPostgresql();
    }
    String sql = "update matiere set prixunitaire = ? where id_matiere = ? ";
    PreparedStatement state=connect.prepareStatement(sql);
    state.setDouble(1, getPrix());
    state.setInt(2, idMatiere);
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
    public void modifierPrixNormalise(Connection connect) throws Exception{
        boolean connexionOuvert=false;
    if (connect == null) {
        connexionOuvert=true;
        Connect myConnect=new Connect();
        connect=myConnect.getConnectionPostgresql();
    }
        String sql = "insert into prix_unitaire values (default, ?, now())";
        PreparedStatement state=connect.prepareStatement(sql);
        state.setInt(1, idMatiere);
        state.setDouble(2, getPrix());
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
    public void setPrix(String prix){
        setPrix(Double.valueOf(prix));
    }
    public void modifierPrix() throws Exception{
        Connect con = new Connect();
        Connection connect = con.getConnectionPostgresql();
        try {
            modifierPrixNormalise(connect);
            modifierPrixDenormalise(connect);
            connect.commit();
        } catch (Exception e) {
            connect.rollback();;
            throw e;
        }
        finally{
            connect.close();
        }
    }
    public Matiere(int idMatiere,String nomMatiere){
        setIdMatiere(idMatiere);
        setNomMatiere(nomMatiere);
    }
    public Matiere() {
    }
    public static Vector<Matiere> list(Connection connect)throws Exception{
        boolean connexionOuvert=false;
        if (connect == null) {
            connexionOuvert=true;
            Connect myConnect=new Connect();
            connect=myConnect.getConnectionPostgresql();
        }

        Statement state=connect.createStatement();
        String sql="SELECT * FROM v_stock_matiere";
        try {
            ResultSet result= state.executeQuery(sql);
            Vector<Matiere> val=new Vector<Matiere>();
            
            while (result.next()) {
                Matiere m = new Matiere(result.getInt("id_matiere"),result.getString("nom_matiere"));
                m.stock = result.getDouble("reste");
                val.add(m);
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
    public static Vector<Matiere> getAll(Connection connect)throws Exception{
        boolean connexionOuvert=false;
        if (connect == null) {
            connexionOuvert=true;
            Connect myConnect=new Connect();
            connect=myConnect.getConnectionPostgresql();
        }

        Statement state=connect.createStatement();
        String sql="SELECT * FROM matiere";
        try {
            ResultSet result= state.executeQuery(sql);
            Vector<Matiere> val=new Vector<Matiere>();
            
            while (result.next()) {
                Matiere m = new Matiere(result.getInt("id_matiere"),result.getString("nom_matiere"));
                val.add(m);
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

    public static Vector<Matiere> listone(Connection connect,String idmatiere)throws Exception{
        boolean connexionOuvert=false;
        if (connect == null) {
            connexionOuvert=true;
            Connect myConnect=new Connect();
            connect=myConnect.getConnectionPostgresql();
        }

        Statement state=connect.createStatement();
        String sql="SELECT * FROM v_stock_matiere where lower(nom_matiere) like lower('%"+idmatiere+"%')";
        try {
            ResultSet result= state.executeQuery(sql);
            Vector<Matiere> val=new Vector<Matiere>();

            while (result.next()) {
                Matiere m = new Matiere(result.getInt("id_matiere"),result.getString("nom_matiere"));
                m.stock = result.getDouble("reste");
                val.add(m);
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

    public void insert(Connection connect)throws Exception{
        boolean connexionOuvert=false;
        if (connect == null) {
            connexionOuvert=true;
            Connect myConnect=new Connect();
            connect=myConnect.getConnectionPostgresql();
        }

        Statement state=connect.createStatement();
        String sql="INSERT INTO Matiere (nom_matiere,prixunitaire) VALUES ('"+this.getNomMatiere()+"', "+this.getPrix()+")";
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
    public double getPrix() {
        return prix;
    }
    public void setPrix(double prix) {
        this.prix = prix;
    }
    public double getStock() {
        return stock;
    }
    public void setStock(double stock) {
        this.stock = stock;
    }

    

    
    
    
}
