package profil;

import java.sql.Connection;
import java.sql.Statement;

import connexion.Connect;

public class ParamProfil {
    int idProfil;
    double annee_min;
    double annee_max;
    double th;

    public int getIdProfil() {
        return idProfil;
    }
    public ParamProfil(String idProfil, String anneeMIn, String anneeMax, String mult){
        setIdProfil(Integer.valueOf(idProfil));
        setAnnee_max(Double.valueOf(anneeMax));
        setAnnee_min(Double.valueOf(anneeMIn));
        setTh(Double.valueOf(mult));
    }
    public void insert(Connection connect) throws Exception{
        boolean connexionOuvert=false;
        if (connect == null) {
            connexionOuvert=true;
            Connect myConnect=new Connect();
            connect=myConnect.getConnectionPostgresql();
        }

        Statement state=connect.createStatement();
        String sql="insert into param_profil values("+this.idProfil+", "+this.th+", "+this.annee_min+", "+this.annee_max+", now())";
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
    public void setIdProfil(int idProfil) {
        this.idProfil = idProfil;
    }
    public double getAnnee_min() {
        return annee_min;
    }
    public void setAnnee_min(double annee_min) {
        this.annee_min = annee_min;
    }
    public double getAnnee_max() {
        return annee_max;
    }
    public void setAnnee_max(double annee_max) {
        this.annee_max = annee_max;
    }
    public double getTh() {
        return th;
    }
    public void setTh(double th) {
        this.th = th;
    }
}
