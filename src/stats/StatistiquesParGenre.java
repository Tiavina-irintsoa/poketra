package stats;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import connexion.Connect;

public class StatistiquesParGenre {
    int idSac;
    String nomSac = "Tous";
    Vector<VenteParGenre> venteParGenre;
    public StatistiquesParGenre(String idSac) {
        this.idSac = Integer.valueOf(idSac);
    }
    public void getStat(Connection connect)throws Exception{
        boolean connexionOuvert=false;
        if (connect == null) {
            connexionOuvert=true;
            Connect myConnect=new Connect();
            connect=myConnect.getConnectionPostgresql();
        }
 
        Statement state=connect.createStatement();
        String sql="";
        if(idSac==0){
            sql = "select * from v_vente_all_genre";
        }
        else{
            sql = "select * from v_vente_par_sac_genre where id_sac = "+this.idSac;
        }
        try {
            ResultSet result= state.executeQuery(sql);
            this.venteParGenre=new Vector<VenteParGenre>();
            
            while (result.next()) {
                venteParGenre.add(new VenteParGenre(result.getInt("genre"), result.getInt("quantite")));
                if(idSac != 0){
                    System.out.println("idSac");
                    this.nomSac = result.getString("nom_sac");
                }
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
    public int getStatFemme(){
        for (int index = 0; index < venteParGenre.size(); index++) {
            if(venteParGenre.get(index).getGenre()==0){
                return venteParGenre.get(index).getVente();
            }
        }
        return 0;
    }
    public int getStatHomme(){
        for (int index = 0; index < venteParGenre.size(); index++) {
            if(venteParGenre.get(index).getGenre()==1){
                return venteParGenre.get(index).getVente();
            }
        }
        return 0;
    }
    public int getIdSac() {
        return idSac;
    }
    public void setIdSac(int idSac) {
        this.idSac = idSac;
    }
    public String getNomSac() {
        return nomSac;
    }
    public void setNomSac(String nomSac) {
        this.nomSac = nomSac;
    }
    public Vector<VenteParGenre> getVenteParGenre() {
        return venteParGenre;
    }
    public void setVenteParGenre(Vector<VenteParGenre> venteParGenre) {
        this.venteParGenre = venteParGenre;
    }
}
