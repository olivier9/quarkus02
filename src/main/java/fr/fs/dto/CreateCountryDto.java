package fr.fs.dto;

public class CreateCountryDto {
    private String nom;
    //devrait pas car pas logique metier
    private Integer idContinent;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getIdContinent() {
        return idContinent;
    }

    public void setIdContinent(Integer idContinent) {
        this.idContinent = idContinent;
    }
}
