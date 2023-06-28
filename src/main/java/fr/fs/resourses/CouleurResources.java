package fr.fs.resourses;

import fr.fs.entities.CouleurEntity;
import fr.fs.repositories.CouleurRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;


@Path("/couleurs/")
@Tag(name = "Couleur")
@Produces(MediaType.APPLICATION_JSON)
public class CouleurResources {

    @Inject
    private CouleurRepository couleurRepository;

    @GET
    public Response getAll(){
        List<CouleurEntity> couleurEntities = couleurRepository.listAll();
        return Response.ok(couleurEntities).build();

    }
    @GET
    @Operation(summary = "Color by Id", description = "Search a color by its ID")
    @APIResponse(responseCode = "200", description = "Ok, color found")
    @APIResponse(responseCode = "404", description = "Color not found")
    @Path("{id}")
    public Response getById(@PathParam("id") Integer id) {
        CouleurEntity couleur = couleurRepository.findById(id);
        if (couleur == null)
            return Response.status(404,"Cet identifiant n'existe pas !").build();
        else
            return Response.ok(couleur).build();
    }

    @Transactional
    @POST
    @APIResponse(responseCode = "201", description = "La ressource a été crée !")
    @APIResponse(responseCode = "500", description = "Le serveur a rencontré un problème !")
    public Response create(String couleurName, @Context UriInfo uriInfo) {

        CouleurEntity couleur = new CouleurEntity();
        couleur.setLibelle(couleurName);
        couleurRepository.persist(couleur);
        UriBuilder uriBuilder = uriInfo.getRequestUriBuilder();
        uriBuilder.path(String.valueOf(couleur.getId()));
        return Response.created(uriBuilder.build()).build();
    }

    @Transactional
    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") Integer id, CouleurEntity couleur) {
        if (couleur.getId().equals(id)) {
            CouleurEntity couleurEntity= couleurRepository.findById(id);
            couleurEntity.setLibelle(couleur.getLibelle());
            return Response.ok(couleurEntity).build();
        } else
            return Response.status(400).build();
    }


    @Transactional
    @DELETE
    @APIResponse(responseCode = "204", description = "La ressource a été supprimée !")
    @APIResponse(responseCode = "404", description = "La ressource n'existe pas ! !")
    @Path("{id}")
    public Response delete(@PathParam("id") Integer id) {
        if (!couleurRepository.deleteById(id))
            return Response.status(Response.Status.NOT_FOUND).build();
        else
            return Response.noContent().build();
    }
}