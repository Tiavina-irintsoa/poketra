package mapping;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import connexion.Connect;

public class DureeFabrication {
    int idDureeFabrication;
    Look look;
    Type type;
    Dimension dimension;
    double dureeHeure;
    public DureeFabrication(String look, String type, String dimension, String dureeHeure){
        setLook(look);
        setType(type);
        setDimension(dimension);
        setDureeHeure(dureeHeure);
    }
    private void setDureeHeure(String dureeHeure) {
        setDureeHeure(Double.valueOf(dureeHeure));
    }
    private void setDimension(String dimension) {
        setDimension(new Dimension(dimension, dimension));
    }
    private void setType(String type) {
        setType(new Type(type));
    }
    private void setLook(String look) {
        setLook(new Look(look));
    }
    public double getDureeHeure() {
        return dureeHeure;
    }
    public void setDureeHeure(double dureeHeure) {
        this.dureeHeure = dureeHeure;
    }
    
    public void insert(Connection connect) throws Exception{
    boolean connexionOuvert=false;
    if (connect == null) {
        connexionOuvert=true;
        Connect myConnect=new Connect();
        connect=myConnect.getConnectionPostgresql();
    }
    String sql = "insert into duree_fabrication values (default, ?, ?, ?, ?)";
    PreparedStatement state=connect.prepareStatement(sql);
    state.setInt(1, getDimension().getIdDimension());
    state.setInt(2, getType().getIdType());
    state.setInt(3, getLook().getIdLook());
    state.setDouble(4, getDureeHeure());
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
    public int getIdDureeFabrication() {
        return idDureeFabrication;
    }
    public void setIdDureeFabrication(int idDureeFabrication) {
        this.idDureeFabrication = idDureeFabrication;
    }
    public Look getLook() {
        return look;
    }
    public void setLook(Look look) {
        this.look = look;
    }
    public Type getType() {
        return type;
    }
    public void setType(Type type) {
        this.type = type;
    }
    public Dimension getDimension() {
        return dimension;
    }
    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }
}
