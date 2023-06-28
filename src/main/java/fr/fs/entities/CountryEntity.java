package fr.fs.entities;

import jakarta.persistence.*;

@Entity(name = "PAYS")
@Table(name = "CONTINENT")
public class CountryEntity {
    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    @Column(name = "ID_PAYS")
    private Integer id;
    @Column(name = "NOM_PAYS")
    private  String libelle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CONTINENT_ID")
    private ContinentEntity continent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public ContinentEntity getContinent() {
        return continent;
    }

    public void setContinent(ContinentEntity continent) {
        this.continent = continent;
    }
}
