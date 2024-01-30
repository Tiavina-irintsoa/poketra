package classement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import connexion.Connect;
import mapping.Matiere;

public class ClassementFournisseur {
    int idMatiere;
    int classement;
    Vector<FournisseurEfficacite> results;
    Vector<Matiere> matieres;
    public ClassementFournisseur(String idMatiere, String classement){
        setIdMatiere(Integer.valueOf(idMatiere));
        setClassement(Integer.valueOf(classement));
    }
    public String getClassementString(){
        String classementString ="";
        if(classement==0){
            classementString += "meilleure qualite";
        }
        else if(classement==1){
            classementString+="moindre pénalite";
        }
        else if(classement==2){
            classementString+="meilleur rapport qualité-prix";
        }
        else if(classement==3){
            classementString+="meilleur qualité et service ";
        }
        else if(classement==4){
            classementString+="meilleur qualite et moindre variation";
        }
        else if(classement==5){
            classementString+="prix unitaire moyen";
        }
        return classementString;
    }
    public String getOrderByString(){
        String sql ="order by ";
        if(classement==0){
            sql += "qualite desc";
        }
        else if(classement==1){
            sql+="penalite asc";
        }
        else if(classement==2){
            sql+="rapport_qualite_prix desc";
        }
        else if(classement==3){
            sql+="qualite_service desc ";
        }
        else if(classement==4){
            sql+="qualite_variation desc";
        }
        else if(classement==5){
            sql+="prix_unitaire desc";
        }
        return sql;
    }
    public void getResults(Connection connect) throws Exception{
        boolean connexionOuvert=false;
        if (connect == null) {
            connexionOuvert=true;
            Connect myConnect=new Connect();
            connect=myConnect.getConnectionPostgresql();
        }
        Statement state=connect.createStatement();
        String sql="select * from v_fournisseur_matiere_all_rapport where id_matiere = "+this.getIdMatiere()+" "+ getOrderByString();
        this.matieres = Matiere.getAll(connect);
        System.out.println(sql);
        try {
            ResultSet res= state.executeQuery(sql);
            results = new Vector<FournisseurEfficacite>();
            while (res.next()) {
                results.add(new FournisseurEfficacite(res.getInt("id_fournisseur"), res.getString("nom_fournisseur"), res.getDouble("qualite"), res.getDouble("penalite"), res.getDouble("rapport_qualite_prix"), res.getDouble("qualite_service"), res.getDouble("qualite_variation"),res.getDouble("prix_unitaire")));
            }
         } catch (Exception e) {
            throw e;
        }finally{
            state.close();
            if (connexionOuvert) {
                connect.close();
            }
        }
    }   
    public int getIdMatiere() {
        return idMatiere;
    }
    public void setIdMatiere(int idMatiere) {
        this.idMatiere = idMatiere;
    }
    public int getClassement() {
        return classement;
    }
    public void setClassement(int classement) {
        this.classement = classement;
    }
    public Vector<FournisseurEfficacite> getResults() {
        return results;
    }
    public void setResults(Vector<FournisseurEfficacite> results) {
        this.results = results;
    }
    public Vector<Matiere> getMatieres() {
        return matieres;
    }
    public void setMatieres(Vector<Matiere> matieres) {
        this.matieres = matieres;
    }

}
