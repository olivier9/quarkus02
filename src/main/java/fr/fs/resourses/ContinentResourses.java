package fr.fs.resourses;

import fr.fs.entities.ContinentEntity;
import fr.fs.entities.CountryEntity;
import fr.fs.repositories.ContinentRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;

@Path("/continents/")
@Tag(name = "Continent")
@Produces(MediaType.APPLICATION_JSON)
public class ContinentResourses {

    @Inject
    private ContinentRepository continentRepository;

    @GET
    public Response getAll() {
        List<ContinentEntity> continentEntities = continentRepository.listAll();
        return Response.ok(continentEntities).build();
    }

    @GET
    @Operation(summary = "Continent by Id", description = "Search a color by its ID")
    @APIResponse(responseCode = "200", description = "Ok, continent found")
    @APIResponse(responseCode = "404", description = "Continent not found")
    @Path("{id}")
    public Response getById(@PathParam("id") Integer id) {
        ContinentEntity continent = continentRepository.findById(id);
        if (continent == null)
            return Response.status(404, "Cet identifiant n'existe pas !").build();
        else
            return Response.ok(continent).build();
    }

    @Transactional
    @POST
    @APIResponse(responseCode = "201", description = "La ressource a été crée !")
    @APIResponse(responseCode = "500", description = "Le serveur a rencontré un problème !")
    public Response create(String continentName, @Context UriInfo uriInfo) {

        ContinentEntity continent = new ContinentEntity();
        continent.setLibelle(continentName);
        continentRepository.persist(continent);
        UriBuilder uriBuilder = uriInfo.getRequestUriBuilder();
        uriBuilder.path(String.valueOf(continent.getId()));
        return Response.created(uriBuilder.build()).build();
    }

    @Transactional
    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") Integer id, ContinentEntity continent) {
        if (continent.getId().equals(id)) {
            ContinentEntity continentEntity = continentRepository.findById(id);
            continentEntity.setLibelle(continent.getLibelle());
            return Response.ok(continentEntity).build();
        } else
            return Response.status(400).build();
    }

    @Transactional
    @DELETE
    @APIResponse(responseCode = "204", description = "La ressource a été supprimée !")
    @APIResponse(responseCode = "404", description = "La ressource n'existe pas ! !")
    @Path("{id}")
    public Response delete(@PathParam("id") Integer id) {
        if (!continentRepository.deleteById(id))
            return Response.status(Response.Status.NOT_FOUND).build();
        else
            return Response.noContent().build();
    }

    @Path("/continents/pays")
    @Tag(name = "Pays")
    @Produces(MediaType.APPLICATION_JSON)
    public class CountryResourses {
        @Inject
        private ContinentRepository paysRepository;

        @GET
        public Response getAll() {
            List<CountryEntity> countryEntities = countryRepository.listAll();
            return Response.ok(countryEntities).build();
    }

        @GET
        @Operation(summary = "Coutry by Id", description = "Search a color by its ID")
        @APIResponse(responseCode = "200", description = "Ok, country found")
        @APIResponse(responseCode = "404", description = "Country not found")
        @Path("{id}")
        public Response getById(@PathParam("id") Integer id) {
            CountryEntity country = countryRepository.findById(id);
            if (country == null)
                return Response.status(404, "Cet identifiant n'existe pas !").build();
            else
                return Response.ok(country).build();
        }

        @Transactional
        @POST
        @APIResponse(responseCode = "201", description = "La ressource a été crée !")
        @APIResponse(responseCode = "500", description = "Le serveur a rencontré un problème !")
        public Response create(String countryName, @Context UriInfo uriInfo) {

            ContinentEntity country = new CountryEntity();
            country.setLibelle(countryName);
            countryRepository.persist(country);
            UriBuilder uriBuilder = uriInfo.getRequestUriBuilder();
            uriBuilder.path(String.valueOf(country.getId()));
            return Response.created(uriBuilder.build()).build();
        }

        @Transactional
        @DELETE
        @APIResponse(responseCode = "204", description = "La ressource a été supprimée !")
        @APIResponse(responseCode = "404", description = "La ressource n'existe pas ! !")
        @Path("{id}")
        public Response delete(@PathParam("id") Integer id) {
            if (!countryRepository.deleteById(id))
                return Response.status(Response.Status.NOT_FOUND).build();
            else
                return Response.noContent().build();
        }
}