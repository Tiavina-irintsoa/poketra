package classement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import connexion.Connect;

public class ClassementSac {
    int classement = 0;
    Vector<SacBeneficeVente> result;
    public ClassementSac(String classement) {
        this.classement = Integer.valueOf(classement);
    }
    public void completeData(Connection connect) throws Exception{
        boolean connexionOuvert=false;
        if (connect == null) {
            connexionOuvert=true;
            Connect myConnect=new Connect();
            connect=myConnect.getConnectionPostgresql();
        }
        Statement state=connect.createStatement();
        String sql="select * from v_benefice_vente_sac";
        if(classement==0){
            sql += " order by quantite desc";
        }
        else{
            sql+=" order by rentabilite desc";
        }
        System.out.println(sql);
        try {
            ResultSet res= state.executeQuery(sql);
            result = new Vector<SacBeneficeVente>();
            while (res.next()) {
                result.add(new SacBeneficeVente(res.getInt("id_sac"),res.getString("nom_sac"),res.getDouble("rentabilite"),res.getDouble("quantite"), res.getDouble("prix_vente_unitaire")));
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
    public int getClassement() {
        return classement;
    }
    public void setClassement(int classement) {
        this.classement = classement;
    }
    public Vector<SacBeneficeVente> getResult() {
        return result;
    }
    
    public void setResult(Vector<SacBeneficeVente> result) {
        this.result = result;
    }
}
