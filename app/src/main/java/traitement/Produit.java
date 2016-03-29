package traitement;

public class Produit {
    String nom;
    Integer quantiter;
    String type;
    String jeremy;

    public Produit(String nom, Integer quantiter, String type){
        this.nom = nom;
        this.quantiter = quantiter;
        this.type = type;
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

    public String getType() {
        return type;
    }
}
