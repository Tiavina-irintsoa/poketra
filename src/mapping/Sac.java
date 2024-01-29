package mapping;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import connexion.Connect;

public class Sac {
    int idSac;
    Dimension dimension;
    Type type;
    Look look;
    String nomSac;
    double coutMatiere;
    double coutRH;
    double cout;
    double prixVente;
    double benefice;
    
    public Sac(int idSac, String nomSac, double coutMatiere, double coutRH, double cout, double prixVente,
            double benefice) {
        this.idSac = idSac;
        this.nomSac = nomSac;
        this.coutMatiere = coutMatiere;
        this.coutRH = coutRH;
        this.cout = cout;
        this.prixVente = prixVente;
        this.benefice = benefice;
    }
    public Sac(String idSac){
        setIdSac(Integer.valueOf(idSac));
    }
    public Sac(int idSac, String nomSac){
        setIdSac(idSac);
        setNomSac(nomSac);
    }

    public static Vector<Sac> list(Connection connect)throws Exception{
        boolean connexionOuvert=false;
        if (connect == null) {
            connexionOuvert=true;
            Connect myConnect=new Connect();
            connect=myConnect.getConnectionPostgresql();
        }

        Statement state=connect.createStatement();
        String sql="SELECT * FROM sac";
        try {
            ResultSet result= state.executeQuery(sql);
            Vector<Sac> val=new Vector<Sac>();

            while (result.next()) {
                val.add(new Sac(result.getInt("id_sac"), result.getString("nom_sac")));
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

    public Sac(String nomSac,double prixMatiere){
        setNomSac(nomSac);
        setCoutMatiere(prixMatiere);
    }
    public Sac(String iddimension, String idtype, String idlook){
        setDimension(new Dimension(Integer.valueOf(iddimension), ""));
        setType(new Type(idtype));
        setLook(new Look(idlook));
    }
    public Sac(String iddimension, String idtype, String idlook, String nomSac){
        setDimension(new Dimension(Integer.valueOf(iddimension), ""));
        setType(new Type(idtype));
        setLook(new Look(idlook));
        setNomSac(nomSac);
    }
    public void insert(Connection connect) throws Exception{
        boolean connexionOuvert=false;
        if (connect == null) {
            connexionOuvert=true;
            Connect myConnect=new Connect();
            connect=myConnect.getConnectionPostgresql();
        }

        Statement state=connect.createStatement();
        String sql="INSERT INTO sac VALUES(DEFAULT,"+getDimension().idDimension+", "+getType().getIdType()+","+getLook().getIdLook()+", '"+this.getNomSac()+"')";
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
    public Dimension getDimension() {
        return dimension;
    }
    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }
    public Type getType() {
        return type;
    }
    public void setType(Type type) {
        this.type = type;
    }
    public Look getLook() {
        return look;
    }
    public void setLook(Look look) {
        this.look = look;
    }
    public int getIdSac() {
        return idSac;
    }
    public void setIdSac(int idSac) {
        this.idSac = idSac;
    }
    public double getCoutMatiere() {
        return coutMatiere;
    }
    public void setCoutMatiere(double coutMatiere) {
        this.coutMatiere = coutMatiere;
    }
    public double getCoutRH() {
        return coutRH;
    }
    public void setCoutRH(double coutRH) {
        this.coutRH = coutRH;
    }
    public double getCout() {
        return cout;
    }
    public void setCout(double cout) {
        this.cout = cout;
    }
    public double getPrixVente() {
        return prixVente;
    }
    public void setPrixVente(double prixVente) {
        this.prixVente = prixVente;
    }
    public double getBenefice() {
        return benefice;
    }
    public void setBenefice(double benefice) {
        this.benefice = benefice;
    }
    public String getNomSac() {
        return nomSac;
    }
    public void setNomSac(String nomSac) {
        this.nomSac = nomSac;
    }
}
