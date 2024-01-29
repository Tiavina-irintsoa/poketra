package vente;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import connexion.Connect;

public class Client {
    int idCient;
    String nomClient;
    int genre;
   public Client(String nomClient, String genre) {
        this.nomClient = nomClient;
        this.genre = Integer.valueOf(genre);
    }
public Client(int idCient, String nomClient, int genre) {
        this.idCient = idCient;
        this.nomClient = nomClient;
        this.genre = genre;
    }
    public String getGenreString(){
        if(genre==0){
            return "Femme";
        }
        else{
            return "Homme";
        }
    }
public static Vector<Client> list(Connection connect)throws Exception{
       boolean connexionOuvert=false;
       if (connect == null) {
           connexionOuvert=true;
           Connect myConnect=new Connect();
           connect=myConnect.getConnectionPostgresql();
       }

       Statement state=connect.createStatement();
       String sql="SELECT * FROM client";
       try {
           ResultSet result= state.executeQuery(sql);
           Vector<Client> val=new Vector<Client>();

           while (result.next()) {
               val.add(new Client(result.getInt("id_client"),result.getString("nom_client"), result.getInt("genre")));
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
    String sql="INSERT INTO client VALUES(DEFAULT,'"+this.getNomClient()+"',"+this.genre+")";
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
    public int getIdCient() {
        return idCient;
    }
    public void setIdCient(int idCient) {
        this.idCient = idCient;
    }
    public String getNomClient() {
        return nomClient;
    }
    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }
    public int getGenre() {
        return genre;
    }
    public void setGenre(int genre) {
        this.genre = genre;
    }
}
