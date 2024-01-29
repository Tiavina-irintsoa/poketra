package mapping;

import connexion.Connect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class Type {
    int idType;
    String nomType;

    public Type(int idType, String nomType) {
        this.idType = idType;
        this.nomType = nomType;
    }
    public Type(String id){
        setIdType(id);
    }

    public Type(String idType, String nomType) {
        this.setIdType(idType);
        this.nomType = nomType;
    }

    public Type() {

    }

    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }
    public void setIdType(String idType) {
        this.setIdType(Integer.parseInt(idType));
    }

    public String getNomType() {
        return nomType;
    }

    public void setNomType(String nomType) {
        this.nomType = nomType;
    }

    public static Vector<Type> getAll(Connection connect) throws Exception {
        boolean connexionOuvert=false;
        if (connect == null) {
            connexionOuvert=true;
            Connect myConnect=new Connect();
            connect=myConnect.getConnectionPostgresql();
        }

        Statement state=connect.createStatement();
        String sql="SELECT * FROM type";
        try {
            ResultSet result= state.executeQuery(sql);
            Vector<Type> val=new Vector<Type>();

            while (result.next()) {
                val.add(new Type(result.getInt("id_type"),result.getString("nom_type")));
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

    public void insert(Connection connect){

    }
}
