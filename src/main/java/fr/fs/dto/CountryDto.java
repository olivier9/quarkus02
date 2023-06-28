package fr.fs.dto;

import fr.fs.entities.CountryEntity;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

public class CountryDto {
    private Integer idPays;
    private String nomPays;
    public CountryDto(CountryEntity payEntity) {
        idPays = paysEntity.getIdPays();
        nomPays = paysEntity.getNomPays();
    }

    @GET
    public Response getAll() {
        return Response.ok(CountryDto.toDtoList(paysRepository.listAll())).build();
    }

    public static List<CountryDto> toDtoList(List<CountryEntity> countryEntity) {
        List<CountryDto> countryDtoList = new ArrayList<>();
        for (CountryEntity countryEntity : countryEntities)
            countryDtoList.add(new CountryDto((countryEntity)));
            return countryDtoList;
    }

    public Integer getIdPays() {
        return idPays;
    }

    public void setIdPays(Integer idPays) {
        this.idPays = idPays;
    }

    public String getNomPays() {
        return nomPays;
    }

    public void setNomPays(String nomPays) {
        this.nomPays = nomPays;
    }
}
