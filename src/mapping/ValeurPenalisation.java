package mapping;

import java.sql.Connection;
import java.sql.Statement;
import java.time.LocalDate;

import connexion.Connect;

public class ValeurPenalisation {
    int idCause;
    int valeur;
    LocalDate date = LocalDate.now();
    public ValeurPenalisation(String idCause, String valeur) {
        this.idCause = Integer.valueOf(idCause);
        setValeur(Integer.valueOf(valeur));
    }
    public ValeurPenalisation(int idCause, int valeurActuelle) {
        setIdCause(idCause);
        setValeur(valeurActuelle);
    }
    public int getIdCause() {
        return idCause;
    }
    public void insert() throws Exception{
        Connect con = new Connect();
        Connection connect = con.getConnectionPostgresql();
        try {
            this.insertSelf(connect);
            CausePenalisation c = new CausePenalisation(idCause,valeur);
            c.updateValeurActuelle(connect);
            connect.commit();
        } catch (Exception e) {
            connect.rollback();;
            throw e;
        }
        finally{
            connect.close();
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
        String sql="INSERT INTO valeur_penalisation values  ("+this.idCause+","+this.valeur+",'"+this.date+"')";
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
    public void setIdCause(int idCause) {
        this.idCause = idCause;
    }
    public int getValeur() {
        return valeur;
    }
    public void setValeur(int valeur) {
        this.valeur = valeur;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
}
