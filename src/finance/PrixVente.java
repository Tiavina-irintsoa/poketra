package finance;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import connexion.Connect;
public class PrixVente {
    int idSac;
    double prixVente;

    public PrixVente(String idSac, String prixVente) {
        setIdSac(Integer.valueOf(idSac));
        setPrixVente(prixVente);
    }
    public void modifierPrixDenormalise(Connection connect) throws Exception{
        boolean connexionOuvert=false;
    if (connect == null) {
        connexionOuvert=true;
        Connect myConnect=new Connect();
        connect=myConnect.getConnectionPostgresql();
    }
    String sql = "update sac set prix_vente = ? where id_sac = ? ";
    PreparedStatement state=connect.prepareStatement(sql);
    state.setDouble(1, getPrixVente());
    state.setInt(2, getIdSac());
    try {
        state.executeUpdate();
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
    public void modifierPrix() throws Exception{
        Connect con = new Connect();
        Connection connect = con.getConnectionPostgresql();
        try {
            insertNormalise(connect);
            modifierPrixDenormalise(connect);
            connect.commit();
        } catch (Exception e) {
            connect.rollback();;
            throw e;
        }
        finally{
            connect.close();
        }
    }
    public void insertNormalise(Connection connect) throws Exception{
        boolean connexionOuvert=false;
        if (connect == null) {
            connexionOuvert=true;
            Connect myConnect=new Connect();
            connect=myConnect.getConnectionPostgresql();
        }

        Statement state=connect.createStatement();
        String sql=" insert into prix_vente values(default, "+this.getIdSac()+", "+this.getPrixVente()+",now())";
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
    private void setPrixVente(String prixVente2) {
        setPrixVente(Double.valueOf(prixVente2));
    }
    public double getPrixVente() {
        return prixVente;
    }
    public void setPrixVente(double prixVente) {
        this.prixVente = prixVente;
    }
    public int getIdSac() {
        return idSac;
    }
    public void setIdSac(int idSac) {
        this.idSac = idSac;
    }
}
