package ua.lviv.iot.lab6.dto.assembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import ua.lviv.iot.lab6.controller.LocationController;
import ua.lviv.iot.lab6.domain.Location;
import ua.lviv.iot.lab6.dto.LocationDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class LocationDtoAssembler implements RepresentationModelAssembler<Location, LocationDto> {
    @Override
    public LocationDto toModel(Location entity) {
    	LocationDto locationDto = LocationDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
        Link selfLink = linkTo(methodOn(LocationController.class).getLocation(locationDto.getId())).withSelfRel();
        locationDto.add(selfLink);
        return locationDto;
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
        locationDtos.add(link);
        return locationDtos;
    }
}
    
    
