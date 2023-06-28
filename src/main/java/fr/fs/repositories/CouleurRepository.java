package fr.fs.repositories;

import fr.fs.entities.CouleurEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class CouleurRepository implements PanacheRepositoryBase<CouleurEntity,Integer> {
}
