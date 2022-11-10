package ua.lviv.iot.lab5backend.dto.assembler;

import ua.lviv.iot.lab5backend.dto.LocationDto;
import ua.lviv.iot.lab5backend.controller.LocationController;
import ua.lviv.iot.lab5backend.domain.Location;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class LocationDtoAssembler implements RepresentationModelAssembler<Location, LocationDto> {
    @Override
    public LocationDto toModel(Location entity) {
    	LocationDto countryDto = LocationDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
        Link selfLink = linkTo(methodOn(LocationController.class).getCountry(countryDto.getId())).withSelfRel();
        countryDto.add(selfLink);
        return countryDto;
    }

    @Override
    public CollectionModel<LocationDto> toCollectionModel(Iterable<? extends Location> entities) {
        CollectionModel<LocationDto> locationDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(LocationController.class).getAllLocations()).withSelfRel();
        locationDtos.add(selfLink);
        return locationDtos;
    }

    public CollectionModel<LocationDto> toCollectionModel(Iterable<? extends Location> entities,  Link link) {
        CollectionModel<LocationDto> locationDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(LocationController.class).getAllLocations()).withSelfRel();
        locationDtos.add(selfLink);
        return locationDtos;
    }
}
    
    
