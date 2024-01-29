package stats;

public class VenteParGenre {
    int genre;
    int vente;
    public VenteParGenre(int genre, int vente) {
        this.genre = genre;
        this.vente = vente;
    }
    public int getGenre() {
        return genre;
    }
    public void setGenre(int genre) {
        this.genre = genre;
    }
    public int getVente() {
        return vente;
    }
    public void setVente(int vente) {
        this.vente = vente;
    }
}
