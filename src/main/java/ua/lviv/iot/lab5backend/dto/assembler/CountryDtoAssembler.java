package ua.lviv.iot.lab5backend.dto.assembler;

import ua.lviv.iot.lab5backend.dto.CountryDto;
import ua.lviv.iot.lab5backend.controller.CountryController;
import ua.lviv.iot.lab5backend.domain.Country;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class CountryDtoAssembler implements RepresentationModelAssembler<Country, CountryDto> {
    @Override
    public CountryDto toModel(Country entity) {
    	CountryDto countryDto = CountryDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
        Link selfLink = linkTo(methodOn(CountryController.class).getCountry(countryDto.getId())).withSelfRel();
        countryDto.add(selfLink);
        return countryDto;
    }

    @Override
    public CollectionModel<CountryDto> toCollectionModel(Iterable<? extends Country> entities) {
        CollectionModel<CountryDto> сountryDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(CountryController.class).getAllСountries()).withSelfRel();
        сountryDtos.add(selfLink);
        return сountryDtos;
    }

    public CollectionModel<CountryDto> toCollectionModel(Iterable<? extends Country> entities,  Link link) {
        CollectionModel<CountryDto> personDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(CountryController.class).getAllСountries()).withSelfRel();
        personDtos.add(selfLink);
        return personDtos;
    }
}
    
