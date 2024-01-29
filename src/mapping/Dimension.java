package mapping;

import connexion.Connect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class Dimension {
    int idDimension;
    String nomDimension;

    public Dimension(String idDimension){
        setIdDimension(idDimension);
    }
    public Dimension(int idDimension, String nomDimension) {
        this.idDimension = idDimension;
        this.nomDimension = nomDimension;
    }

    public Dimension(String idDimension, String nomDimension) {
        this.setIdDimension(idDimension);
        this.nomDimension = nomDimension;
    }

    public int getIdDimension() {
        return idDimension;
    }

    public void setIdDimension(int idDimension) {
        this.idDimension = idDimension;
    }

    public void setIdDimension(String idDimension) {
        this.setIdDimension(Integer.parseInt(idDimension));
    }

    public String getNomDimension() {
        return nomDimension;
    }

    public void setNomDimension(String nomDimension) {
        this.nomDimension = nomDimension;
    }

    public static Vector<Dimension> getAll(Connection connect) throws Exception {
        boolean connexionOuvert=false;
        if (connect == null) {
            connexionOuvert=true;
            Connect myConnect=new Connect();
            connect=myConnect.getConnectionPostgresql();
        }

        Statement state=connect.createStatement();
        String sql="SELECT * FROM dimension order by valeur asc";
        try {
            ResultSet result= state.executeQuery(sql);
            Vector<Dimension> val=new Vector<Dimension>();

            while (result.next()) {
                val.add(new Dimension(result.getInt("id_dimension"),result.getString("nom_dimension")));
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
}
