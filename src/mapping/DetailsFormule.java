package mapping;

import connexion.Connect;

import java.sql.Connection;
import java.sql.Statement;

public class DetailsFormule {
    Matiere matiere;
    double quantite;

    public DetailsFormule(Matiere matiere, double quantite) {
        this.matiere = matiere;
        this.quantite = quantite;
    }
    public DetailsFormule(String matiere, String quantite) {
        this.matiere = new Matiere(matiere);
        this.setQuantite(quantite);
    }

    public Matiere getMatiere() {
        return matiere;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public void setQuantite(String quantite) {
        this.setQuantite(Double.valueOf(quantite));
    }

    public void insere(Connection connect, Sac sac) throws Exception {
        boolean connexionOuvert=false;
        if (connect == null) {
            connexionOuvert=true;
            Connect myConnect=new Connect();
            connect=myConnect.getConnectionPostgresql();
        }

        Statement state=connect.createStatement();
        String sql="INSERT INTO matiere_sac VALUES(DEFAULT,"+this.getMatiere().getIdMatiere()+","+sac.getIdSac()+","+quantite+")";
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
}
