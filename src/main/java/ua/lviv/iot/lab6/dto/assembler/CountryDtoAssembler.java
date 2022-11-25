package ua.lviv.iot.lab6.dto.assembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import ua.lviv.iot.lab6.controller.CountryController;
import ua.lviv.iot.lab6.domain.Country;
import ua.lviv.iot.lab6.dto.CountryDto;

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
        CollectionModel<CountryDto> сountryDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        сountryDtos.add(link);
        return сountryDtos;
    }
}
    
