package vente;

import java.sql.Connection;
import java.sql.Statement;

import connexion.Connect;

public class DetailVente {
    int idSac;
    int quantite;
    
    public DetailVente(int idSac, int quantite) {
        this.idSac = idSac;
        this.quantite = quantite;
    }
    public void insert(Connection connect, int idVente)throws Exception{
        boolean connexionOuvert=false;
        if (connect == null) {
            connexionOuvert=true;
            Connect myConnect=new Connect();
            connect=myConnect.getConnectionPostgresql();
        }

        Statement state=connect.createStatement();
        String sql="insert into details_vente values ("+idVente+", "+this.getIdSac()+","+this.getQuantite()+")";
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
    public int getIdSac() {
        return idSac;
    }
    public void setIdSac(int idSac) {
        this.idSac = idSac;
    }
    public int getQuantite() {
        return quantite;
    }
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}
