package fr.fs.repositories;

import fr.fs.entities.FabricantEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class FabricantRepository implements PanacheRepositoryBase<FabricantEntity,Integer> {
}
