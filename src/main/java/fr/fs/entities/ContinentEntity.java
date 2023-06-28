package fr.fs.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "CONTINENT", schema = "dbo", catalog = "SDBM")
public class ContinentEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID_CONTINENT")
    private Integer id;
    @Basic
    @Column(name = "NOM_CONTINENT")
    private String libelle;

    public Integer getId() {
        return id;
    }

    public void setId(int idContinent) {
        this.id = idContinent;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String nomContinent) {
        this.libelle = nomContinent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContinentEntity that = (ContinentEntity) o;
        return id == that.id && Objects.equals(libelle, that.libelle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, libelle);
    }
}
