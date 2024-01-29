package mapping;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import connexion.Connect;

public class TypeRH {
    int idType;
    String nomType;
    double tauxHoraire;
    
    public TypeRH(int idType, String nomType, double tauxHoraire) {
        this.idType = idType;
        this.nomType = nomType;
        this.tauxHoraire = tauxHoraire;
    }
    public TypeRH(String nomType, String tauxHoraire) {
        this.nomType = nomType;
        setTauxHoraire(Double.valueOf(tauxHoraire));
    }
    public static Vector<TypeRH> getAll(Connection connect)throws Exception{
        boolean connexionOuvert=false;
        if (connect == null) {
            connexionOuvert=true;
            Connect myConnect=new Connect();
            connect=myConnect.getConnectionPostgresql();
        }

        Statement state=connect.createStatement();
        String sql="SELECT * FROM type_rh";
        try {
            ResultSet result= state.executeQuery(sql);
            Vector<TypeRH> val=new Vector<TypeRH>();
            
            while (result.next()) {
                val.add(new TypeRH(result.getInt("id_type_rh"), result.getString("nom"), result.getDouble("taux_horaire")));
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
        String sql="INSERT INTO type_rh (nom,taux_horaire) VALUES ('"+this.getNomType()+"', "+this.getTauxHoraire()+")";
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
    public int getIdType() {
        return idType;
    }
    public void setIdType(int idType) {
        this.idType = idType;
    }
    public String getNomType() {
        return nomType;
    }
    public void setNomType(String nomType) {
        this.nomType = nomType;
    }
    public double getTauxHoraire() {
        return tauxHoraire;
    }
    public void setTauxHoraire(double tauxHoraire) {
        this.tauxHoraire = tauxHoraire;
    }
}
