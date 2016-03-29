package traitement;

/**
 * Created by mael on 29/03/2016.
 */
public class Produit {
    String nom;
    Integer quantiter;
    public Produit(String nom, Integer quantiter){
        this.nom = nom;
        this.quantiter = quantiter;
    }

    @Override
    public String toString() {
        return nom + " " + quantiter.toString();
    }

    public String getNom() {
        return nom;
    }

    public Integer getQuantiter() {
        return quantiter;
    }
}
