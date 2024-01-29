package profil;

public class Profil {
    int idProfil;
    String nomProfil;
    
    public void setIdProfil(String id){
        setIdProfil(Integer.valueOf(id));
    }
    public Profil(int idProfil, String nomProfil) {
        this.idProfil = idProfil;
        this.nomProfil = nomProfil;
    }

    public Profil( String nomProfil) {
        this.nomProfil = nomProfil;
    }
    public Profil() {
    }

    public int getIdProfil() {
        return idProfil;
    }
    public void setIdProfil(int idProfil) {
        this.idProfil = idProfil;
    }

    public String getNomProfil() {
        return nomProfil;
    }
    public void setNomProfil(String nomProfil) {
        this.nomProfil = nomProfil;
    }
}
