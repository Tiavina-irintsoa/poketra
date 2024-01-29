package mapping;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import connexion.Connect;

public class FicheLook extends Look{
    Vector<Matiere> listMatieres;

    public Vector<Matiere> getListMatieres() {
        return listMatieres;
    }

    public void setListMatieres(Vector<Matiere> listMatieres) {
        this.listMatieres = listMatieres;
    }

    public FicheLook(String idLook,String[] listIdMAtieres){
        super(idLook);
        for (int i = 0; i < listIdMAtieres.length; i++) {
            addMatiere(listIdMAtieres[i]);
        }
    }

    public FicheLook(String idLook){
        super(idLook);
    }

    public void addMatiere(String IdMatieres){
        if (listMatieres==null) {
            listMatieres=new Vector<Matiere>();
        }
        listMatieres.add(new Matiere(IdMatieres));
    }

    public void insertMatiereCompatible(Connection connect,Matiere matiere)throws Exception{
        boolean connexionOuvert=false;
        if (connect == null) {
            connexionOuvert=true;
            Connect myConnect=new Connect();
            connect=myConnect.getConnectionPostgresql();

        }
        
        Statement state=connect.createStatement();
        String sql="INSERT INTO matiere_look VALUES(Default,"+this.getIdLook()+","+matiere.getIdMatiere()+")";
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

    public void insertMatiereCompatibles(Connection connect)throws Exception{
        boolean connexionOuvert=false;
        if (connect == null) {
            connexionOuvert=true;
            Connect myConnect=new Connect();
            connect=myConnect.getConnectionPostgresql();
        }
        try {
            for (int i = 0; i < listMatieres.size(); i++) {
                insertMatiereCompatible(connect, listMatieres.get(i));
            }
            if (connexionOuvert) {
                connect.commit();
            }
        } catch (Exception e) {
            if (connexionOuvert) {
                connect.rollback();
            }
        }finally{
            if (connexionOuvert) {
                connect.close();
            }
        }

    }

    public Vector<Matiere> getMatieres(Connection connect) throws Exception{
        boolean connexionOuvert=false;
        if (connect == null) {
            connexionOuvert=true;
            Connect myConnect=new Connect();
            connect=myConnect.getConnectionPostgresql();
        }
        
        Statement state=connect.createStatement();
        String sql="SELECT * FROM v_liste_matiere_look where id_look="+this.getIdLook();
        try {
            Vector<Matiere> matieres=new Vector<Matiere>();
            ResultSet result= state.executeQuery(sql);
            while (result.next()) {
                matieres.add(new Matiere(result.getInt("id_matiere"), result.getString("nom_matiere")));
            }
            return matieres;
        } catch (Exception e) {
            throw e;
        }finally{
            state.close();
            if (connexionOuvert) {
                connect.close();
            }
        }
    }

    public Vector<Matiere> getMatieresNonCompatible(Connection connect) throws Exception{
        boolean connexionOuvert=false;
        if (connect == null) {
            connexionOuvert=true;
            Connect myConnect=new Connect();
            connect=myConnect.getConnectionPostgresql();
        }
        
        Statement state=connect.createStatement();
        String sql="SELECT * FROM v_liste_matier_look_non_compatible where id_look ="+this.getIdLook();
        try {
            Vector<Matiere> matieres=new Vector<Matiere>();
            ResultSet result= state.executeQuery(sql);
            while (result.next()) {
                matieres.add(new Matiere(result.getInt("id_matiere"), result.getString("nom_matiere")));
            }
            return matieres;
        } catch (Exception e) {
            throw e;
        }finally{
            state.close();
            if (connexionOuvert) {
                connect.close();
            }
        }
    }

    public void completData(Connection connect)throws Exception{
        boolean connexionOuvert=false;
        if (connect == null) {
            connexionOuvert=true;
            Connect myConnect=new Connect();
            connect=myConnect.getConnectionPostgresql();
        }
        setListMatieres(getMatieres(connect));
    }



    

    
}

