package finance;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Vector;

import connexion.Connect;
import mapping.Dimension;

public class ParamDimensionRH {
    int nbPersonneTailleStandard;
    int mult;
    int typeRH;
    

    public ParamDimensionRH(){

    }
    public Vector<DimensionMainOeuvre> getDimensionMainOeuvre(Connection connect) throws Exception{
        Vector<DimensionMainOeuvre> res = new Vector<DimensionMainOeuvre>();
        Vector<Dimension> dimension = Dimension.getAll(connect);
        int nb = nbPersonneTailleStandard;
        for (int index = 0; index < dimension.size(); index++) {
            res.add(new DimensionMainOeuvre(dimension.get(index).getIdDimension(), nb, typeRH));
            nb = nb*mult;
        }
        return res;
    }
    public void insertSelf(Connection connect) throws Exception{
        boolean connexionOuvert=false;
        if (connect == null) {
            connexionOuvert=true;
            Connect myConnect=new Connect();
            connect=myConnect.getConnectionPostgresql();
        }

        Statement state=connect.createStatement();
        String sql="insert into param_dimension_rh values("+getNbPersonneTailleStandard()+","+this.getTypeRH()+","+getMult()+",now())";
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
    public void insert() throws Exception{
        Connect con = new Connect();
        Connection connect = con.getConnectionPostgresql();
        try {
            insertSelf(connect);
            Vector<DimensionMainOeuvre>  params = getDimensionMainOeuvre(connect);
        for (int index = 0; index < params.size(); index++) {
            params.get(index).insert(connect);
        }
            connect.commit();
        } catch (Exception e) {
            connect.rollback();;
            throw e;
        }
        finally{
            connect.close();
        }
        
    }
    public ParamDimensionRH(String nbPersonne, String mult, String rh){
        setNbPersonneTailleStandard(nbPersonne);
        setMult(mult);
        setTypeRH(Integer.valueOf(rh));
    }
    public void setMult(String mult2) {
        setMult(Integer.valueOf(mult2));
    }
    public void setNbPersonneTailleStandard(String nbPersonne) {
        setNbPersonneTailleStandard(Integer.valueOf(nbPersonne));
    }
    public double getNbPersonneTailleStandard() {
        return nbPersonneTailleStandard;
    }
    public void setNbPersonneTailleStandard(int nbPersonneTailleStandard) {
        this.nbPersonneTailleStandard = nbPersonneTailleStandard;
    }
    public int getMult() {
        return mult;
    }
    public void setMult(int mult) {
        this.mult = mult;
    }
    public int getTypeRH() {
        return typeRH;
    }
    public void setTypeRH(int typeRH) {
        this.typeRH = typeRH;
    }
   
}
