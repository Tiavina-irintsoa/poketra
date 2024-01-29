package mapping;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import connexion.Connect;

public class Look {
    int idLook;
    String nomLook;

    public Look() {

    }


    public int getIdLook() {
        return idLook;
    }
    public void setIdLook(int idLook) {
        this.idLook = idLook;
    }
    public void setIdLook(String idLook) {
        setIdLook(Integer.valueOf(idLook));
    }
    public String getNomLook() {
        return nomLook;
    }
    public void setNomLook(String nomLook) {
        this.nomLook = nomLook;
    }

    public Look(String idLook){
        setIdLook(idLook);
    }
    public Look(String nomLook, int const1){
        setNomLook(nomLook);
    }
    public Look(int idLook, String nomLook) {
       setIdLook(idLook);
       setNomLook(nomLook);
    }

    public void insert(Connection connect)throws Exception{
        boolean connexionOuvert=false;
        if (connect == null) {
            connexionOuvert=true;
            Connect myConnect=new Connect();
            connect=myConnect.getConnectionPostgresql();
        }

        Statement state=connect.createStatement();
        String sql="INSERT INTO Look VALUES(DEFAULT,'"+this.getNomLook()+"')";
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

//    public static Vector<Look> list(Connection connect)throws Exception{
//        boolean connexionOuvert=false;
//        if (connect == null) {
//            connexionOuvert=true;
//            Connect myConnect=new Connect();
//            connect=myConnect.getConnectionPostgresql();
//        }
//
//        Statement state=connect.createStatement();
//        String sql="SELECT * FROM Look";
//        try {
//            ResultSet result= state.executeQuery(sql);
//            Vector<Look> val=new Vector<Look>();
//
//            while (result.next()) {
//                val.add(new Look(result.getInt("id_look"),result.getString("nom_look")));
//            }
//
//            return val;
//        } catch (Exception e) {
//            throw e;
//        }finally{
//            state.close();
//            if (connexionOuvert) {
//                connect.close();
//            }
//        }
//
//    }
    
}
