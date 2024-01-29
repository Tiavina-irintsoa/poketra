package finance;

import connexion.Connect;

import java.sql.Connection;
import java.sql.Statement;

public class TypeDuree {
    int idTypeDurre;
    int idType;
    double duree;
    int idTypeRh;
    public TypeDuree(String idTYpe, String duree,String rh){
        setIdType(Integer.valueOf(idTYpe));
        setDuree(Double.valueOf(duree));
       setIdTypeRh(Integer.valueOf(rh));
    }
    public int getIdTypeDurre() {
        return idTypeDurre;
    }
    public void setIdTypeDurre(int idTypeDurre) {
        this.idTypeDurre = idTypeDurre;
    }
    public int getIdType() {
        return idType;
    }
    public void setIdType(int idType) {
        this.idType = idType;
    }
    public double getDuree() {
        return duree;
    }
    public void setDuree(double duree) {
        this.duree = duree;
    }

    public void insert(Connection connect) throws Exception{
        boolean connexionOuvert=false;
        if (connect == null) {
            connexionOuvert=true;
            Connect myConnect=new Connect();
            connect=myConnect.getConnectionPostgresql();
        }

        Statement state=connect.createStatement();
        String sql=" insert into type_duree_travail values(default, "+this.getIdType()+", "+this.getDuree()+","+this.getIdTypeRh()+",now())";
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
    public int getIdTypeRh() {
        return idTypeRh;
    }
    public void setIdTypeRh(int idTypeRh) {
        this.idTypeRh = idTypeRh;
    }
}