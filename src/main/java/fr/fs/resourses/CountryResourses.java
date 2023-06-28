package fr.fs.resourses;

import fr.fs.dto.CountryDto;
import fr.fs.dto.CreateCountryDto;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;


@Path("/countries/")
@Tag(name = "Country")
@Produces(MediaType.APPLICATION_JSON)

public class CountryResourses {
    @Inject
    private CountryRepository countryRepository;


@GET
public Response getAll() {
    List<CountryEntity> countryEntities = countryRepository.listAll();
    return Response.ok(countryEntities).build();
}
    @GET
    @Operation(summary = "Color by Id", description = "Search a color by its ID")
    @APIResponse(responseCode = "200", description = "Ok, color found")
    @APIResponse(responseCode = "404", description = "Country not found")
    @Path("{id}")
    public Response getById(@PathParam("id") Integer id) {
        CountryEntity pays = paysRepository.findById(id);
        if (pays == null)
            return Response.status(404,"Cet identifiant n'existe pas !").build();
        else
            return Response.ok(new CountryDto(pays)).build();
    }
    @Transactional
    @POST
    @APIResponse(responseCode = "201", description = "la ressource a ete cree");
    @APIResponse(responseCode = "500", description = "le serveur a rencontre un probleme");
    public Response create(CreateCountryDto pays, @Context UriInfo);
}