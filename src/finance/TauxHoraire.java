package finance;

import connexion.Connect;

import java.sql.Connection;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Locale;

public class TauxHoraire {
    int id_taux_horaire;
    double valeur;
    LocalDate date;

    public int getId_taux_horaire() {
        return id_taux_horaire;
    }

    public void setId_taux_horaire(int id_taux_horaire) {
        this.id_taux_horaire = id_taux_horaire;
    }

    public double getValeur() {
        return valeur;
    }

    public void setValeur(double valeur) {
        this.valeur = valeur;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public TauxHoraire( String valeur) {
        this.valeur = Double.valueOf(valeur);
        this.date = LocalDate.now();
    }

    public void insert(Connection connect) throws Exception{
        boolean connexionOuvert=false;
        if (connect == null) {
            connexionOuvert=true;
            Connect myConnect=new Connect();
            connect=myConnect.getConnectionPostgresql();
        }

        Statement state=connect.createStatement();
        String sql=" insert into taux_horaire values(default, "+this.getValeur()+",now())";
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
}
