package vente;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import connexion.Connect;

public class Vente {
    int idVente;
    int idClient;
    Date date;
    Vector<DetailVente> details;
    public Vente(String idClient, String date, String[] sacs, String[] quantites) {
        this.idClient = Integer.valueOf(idClient);
        this.date = Date.valueOf(date);
        details = new Vector<DetailVente>();
        for(int i=0;i<sacs.length;i++){
            System.out.println(sacs[i]+"ooo");
            details.add(new DetailVente(Integer.valueOf(sacs[i]), Integer.valueOf(quantites[i])));
        }
    }
    public void insert() throws Exception{
        Connect con = new Connect();
        Connection connect = con.getConnectionPostgresql();
        try {
            insertSelf(connect);
            for (int i = 0; i < details.size(); i++) {
                details.get(i).insert(connect, this.getIdVente());
            }
            connect.commit();
        } catch (Exception e) {
            connect.rollback();;
            throw e;
        }
        finally{
            connect.close();
        }
    }
    public void insertSelf(Connection connect)throws Exception{

        boolean connexionOuvert=false;
        if (connect == null) {
            connexionOuvert=true;
            Connect myConnect=new Connect();
            connect=myConnect.getConnectionPostgresql();
        }
    
        Statement state=connect.createStatement();
        String sql="INSERT INTO vente VALUES(DEFAULT,"+this.getIdClient()+",'"+this.getDate()+"') returning id_vente";
        try {
            System.out.println(sql);
            ResultSet res = state.executeQuery(sql);
            res.next();
            setIdVente(res.getInt("id_vente"));
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
    public int getIdVente() {
        return idVente;
    }
    public void setIdVente(int idVente) {
        this.idVente = idVente;
    }
    public int getIdClient() {
        return idClient;
    }
    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

}
