package employe;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Statement;

import connexion.Connect;

public class Employe {
    int idEmploye;
    String nom;
    int idProfil;
    Date dateEmbauche;
    
    public Employe(int idEmploye, String nom) {
        this.idEmploye = idEmploye;
        this.nom = nom;
    }
    public Employe(String nom, String profil, String dateEmbauche){
        setNom(nom);
        setIdProfil(Integer.valueOf(profil));
        setDateEmbauche(Date.valueOf(dateEmbauche));
    }
    public void insert(Connection connect) throws Exception{
        boolean connexionOuvert=false;
        if (connect == null) {
            connexionOuvert=true;
            Connect myConnect=new Connect();
            connect=myConnect.getConnectionPostgresql();
        }

        Statement state=connect.createStatement();
        String sql="insert into employe values(default, '"+getNom()+"', '"+dateEmbauche+"', "+idProfil+")";
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
    public int getIdEmploye() {
        return idEmploye;
    }
    public void setIdEmploye(int idEmploye) {
        this.idEmploye = idEmploye;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public int getIdProfil() {
        return idProfil;
    }
    public void setIdProfil(int idProfil) {
        this.idProfil = idProfil;
    }
    public Date getDateEmbauche() {
        return dateEmbauche;
    }
    public void setDateEmbauche(Date dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }

}
