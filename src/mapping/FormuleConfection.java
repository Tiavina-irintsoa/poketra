package mapping;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Vector;

public class FormuleConfection {

    Sac sac;
    Vector<DetailsFormule> detailsFormules;





    public FormuleConfection(Sac sac, Vector<DetailsFormule> detailsFormules) {
        this.sac = sac;
        this.detailsFormules = detailsFormules;
    }

    public Vector<DetailsFormule> getDetailsFormules() {
        return detailsFormules;
    }

    public void setDetailsFormules(Vector<DetailsFormule> detailsFormules) {
        this.detailsFormules = detailsFormules;
    }

    public FormuleConfection(String idSac, HashMap<String,String> detailsFormules) {
        setSac(new Sac(idSac));
        this.setDetailsFormules(new Vector<DetailsFormule>());
        for (HashMap.Entry<String, String> entry : detailsFormules.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            getDetailsFormules().add(new DetailsFormule(key,value));
        }
    }

    public void insertAll(Connection connect) throws Exception {
        for (int i = 0; i <getDetailsFormules().size() ; i++) {
            getDetailsFormules().get(i).insere(connect, sac);
        }
    }

    public Sac getSac() {
        return sac;
    }

    public void setSac(Sac sac) {
        this.sac = sac;
    }
}
