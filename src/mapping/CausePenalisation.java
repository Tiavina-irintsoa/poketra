package mapping;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import connexion.Connect;

public class CausePenalisation {
    int idCause;
    String cause;
    int valeurActuelle;

    public void insert() throws Exception{
        Connect con = new Connect();
        Connection connect = con.getConnectionPostgresql();
        try {
            this.insertSelf(connect);
            ValeurPenalisation valeurPenalisation = new ValeurPenalisation(idCause, valeurActuelle);
            valeurPenalisation.insertSelf(connect);
            connect.commit();
        } catch (Exception e) {
            connect.rollback();;
            throw e;
        }
        finally{
            connect.close();
        }
    }
    public void getById(Connection connect)throws Exception{
        boolean connexionOuvert=false;
        if (connect == null) {
            connexionOuvert=true;
            Connect myConnect=new Connect();
            connect=myConnect.getConnectionPostgresql();
        }

        Statement state=connect.createStatement();
        String sql="SELECT * FROM cause_penalisation where id_cause = "+this.getIdCause();
        try {
            ResultSet result= state.executeQuery(sql);
            result.next();
            setValeurActuelle(result.getInt("valeur_actuelle"));
        } catch (Exception e) {
            throw e;
        }finally{
            state.close();
            if (connexionOuvert) {
                connect.close();
            }
        }

    }
    
    public static Vector<CausePenalisation> list(Connection connect)throws Exception{
        boolean connexionOuvert=false;
        if (connect == null) {
            connexionOuvert=true;
            Connect myConnect=new Connect();
            connect=myConnect.getConnectionPostgresql();
        }

        Statement state=connect.createStatement();
        String sql="SELECT * FROM cause_penalisation";
        try {
            ResultSet result= state.executeQuery(sql);
            Vector<CausePenalisation> val=new Vector<CausePenalisation>();
            
            while (result.next()) {
                CausePenalisation m = new CausePenalisation(result.getInt("id_cause"), result.getString("cause"), result.getInt("valeur_actuelle"));
                val.add(m);
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
    
    public CausePenalisation(int idCause, int valeur) throws Exception {
        setIdCause(idCause);
        setValeurActuelle(valeur);
	}
	public CausePenalisation(String cause2, String valeur) throws NumberFormatException, Exception {
        setCause(cause2);
        setValeurActuelle(Integer.valueOf(valeur));
    }

    public CausePenalisation(int idCause, String cause, int valeur) throws Exception {
        setIdCause(idCause);
        setValeurActuelle(valeur);
        setCause(cause);
    }
    public void updateValeurActuelle(Connection connect) throws Exception{
        boolean connexionOuvert=false;
        if (connect == null) {
            connexionOuvert=true;
            Connect myConnect=new Connect();
            connect=myConnect.getConnectionPostgresql();
        }

        Statement state=connect.createStatement();
        String sql="update cause_penalisation set valeur_actuelle = "+valeurActuelle+" where id_cause = "+this.idCause;
        System.out.println(sql);
        try {
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
    public void insertSelf(Connection connect) throws Exception{
        boolean connexionOuvert=false;
        if (connect == null) {
            connexionOuvert=true;
            Connect myConnect=new Connect();
            connect=myConnect.getConnectionPostgresql();
        }

        Statement state=connect.createStatement();
        String sql="INSERT INTO cause_penalisation values (default, '"+cause+"',"+valeurActuelle+") returning id_cause";
        try {
            ResultSet res = state.executeQuery(sql);
            res.next(); 
            setIdCause(res.getInt("id_cause"));
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
    public int getIdCause() {
        return idCause;
    }
    public void setIdCause(int idCause) {
        this.idCause = idCause;
    }
    public String getCause() {
        return cause;
    }
    public void setCause(String cause) {
        this.cause = cause;
    }
    public int getValeurActuelle() {
        return valeurActuelle;
    }
    public void setValeurActuelle(int valeurActuelle) throws Exception {
        if(valeurActuelle>10 || valeurActuelle<0){
            throw new Exception("Valeur invalide");
        }
        this.valeurActuelle = valeurActuelle;
    }
}
