package employe;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import connexion.Connect;
import profil.Profil;

public class FicheEmploye {
    Employe employe;
    Profil profil;
    double tauxHoraire;

    public static Vector<FicheEmploye> list(Connection connect)throws Exception{
        boolean connexionOuvert=false;
        if (connect == null) {
            connexionOuvert=true;
            Connect myConnect=new Connect();
            connect=myConnect.getConnectionPostgresql();
        }

        Statement state=connect.createStatement();
        String sql="SELECT * FROM v_profil_taux_horaire_actuel";
        try {
            ResultSet res= state.executeQuery(sql);
            Vector<FicheEmploye> val=new Vector<FicheEmploye>();
            while(res.next()){
                FicheEmploye fiche = new FicheEmploye();
                fiche.setEmploye(new Employe(res.getInt("id_employe"), res.getString("nom_employe")));
                fiche.setTauxHoraire(res.getDouble("taux_horaire"));
                fiche.setProfil(new Profil(res.getInt("id_profil"), res.getString("nom_profil")));
                val.add(fiche);
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

    public Employe getEmploye() {
        return employe;
    }
    public void setEmploye(Employe employe) {
        this.employe = employe;
    }
    public Profil getProfil() {
        return profil;
    }
    public void setProfil(Profil profil) {
        this.profil = profil;
    }
    public double getTauxHoraire() {
        return tauxHoraire;
    }
    public void setTauxHoraire(double tauxHoraire) {
        this.tauxHoraire = tauxHoraire;
    }
}
