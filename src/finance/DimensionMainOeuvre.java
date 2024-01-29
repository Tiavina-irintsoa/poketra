package finance;

import java.sql.Connection;
import java.sql.Statement;

import connexion.Connect;

public class DimensionMainOeuvre {
    int idDimension; 
    int nb;
    int idTypeRH;

    
    public DimensionMainOeuvre(int idDimension, int nb, int rh) {
        this.idDimension = idDimension;
        this.nb = nb;
        setIdTypeRH(rh);
    }
    public void insert(Connection connect) throws Exception{
        boolean connexionOuvert=false;
        if (connect == null) {
            connexionOuvert=true;
            Connect myConnect=new Connect();
            connect=myConnect.getConnectionPostgresql();
        }

        Statement state=connect.createStatement();
        String sql=" insert into taille_nb_ressources_humaines values("+this.idDimension+","+this.nb+",now(),"+this.getIdTypeRH()+")";
        try {
            System.out.println(sql);
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
    public int getIdDimension() {
        return idDimension;
    }
    public void setIdDimension(int idDimension) {
        this.idDimension = idDimension;
    }
    public int getNb() {
        return nb;
    }
    public void setNb(int nb) {
        this.nb = nb;
    }
    public int getIdTypeRH() {
        return idTypeRH;
    }
    public void setIdTypeRH(int idTypeRH) {
        this.idTypeRH = idTypeRH;
    }
}
