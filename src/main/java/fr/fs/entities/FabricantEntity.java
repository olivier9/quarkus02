package fr.fs.entities;

import jakarta.persistence.*;

@Entity(name = "FABRICANT")

public class FabricantEntity {
    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    @Column(name = "ID_FABRICANT")
    private Integer id;
    @Column(name = "NOM_FABRICANT")
    private  String libelle;

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
}
