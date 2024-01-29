package mapping;

import connexion.Connect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class FicheMatiere extends Matiere{
    Vector<FormuleConfection> listFormuleConfection;

    public FicheMatiere(String idMatiere) {
        super(idMatiere);

    }

        public Vector<FormuleConfection> getListFormuleConfection() {
        return listFormuleConfection;
    }

    public void setListFormuleConfection(Vector<FormuleConfection> listFormuleConfection) {
        this.listFormuleConfection = listFormuleConfection;
    }

    public Vector<FormuleConfection> getFormuleConfection(Connection connect) throws Exception {
        boolean connexionOuvert=false;
        if (connect == null) {
            connexionOuvert=true;
            Connect myConnect=new Connect();
            connect=myConnect.getConnectionPostgresql();
        }

        Statement state=connect.createStatement();
        String sql="SELECT * FROM v_formule_confection where id_matiere="+this.idMatiere;
        System.out.println(sql);
        try {
            ResultSet result= state.executeQuery(sql);
            Vector<FormuleConfection> val=new Vector<FormuleConfection>();

            while (result.next()) {

                Sac sac = new Sac(result.getInt("id_sac"), result.getString("nom_sac"));
                Vector<DetailsFormule> detailsFormules=new Vector<DetailsFormule>();
                detailsFormules.add(new DetailsFormule("0",result.getString("quantite")));
                FormuleConfection f = new FormuleConfection(sac,detailsFormules);
                val.add(f);
            }
            System.out.println(val.size());
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


    public void completData(Connection connect) throws Exception {
        setListFormuleConfection(this.getFormuleConfection(connect));
    }
}
