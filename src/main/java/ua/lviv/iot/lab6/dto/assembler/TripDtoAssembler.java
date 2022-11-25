package ua.lviv.iot.lab6.dto.assembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import ua.lviv.iot.lab6.controller.TripController;
import ua.lviv.iot.lab6.domain.Trip;
import ua.lviv.iot.lab6.dto.TripDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class TripDtoAssembler implements RepresentationModelAssembler<Trip, TripDto> {
    @Override
    public TripDto toModel(Trip entity) {
    	TripDto tripDto = TripDto.builder()
                .id(entity.getId())
                .dateStart(entity.getDateStart())
                .guideSalary(entity.getGuideSalary())
                .build();
        Link selfLink = linkTo(methodOn(TripController.class).getTrip(tripDto.getId())).withSelfRel();
        tripDto.add(selfLink);
        return tripDto;
    }

    @Override
    public CollectionModel<TripDto> toCollectionModel(Iterable<? extends Trip> entities) {
        CollectionModel<TripDto> tripDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(TripController.class).getAllTrips()).withSelfRel();
        tripDtos.add(selfLink);
        return tripDtos;
    }

    public CollectionModel<TripDto> toCollectionModel(Iterable<? extends Trip> entities,  Link link) {
        CollectionModel<TripDto> tripDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        tripDtos.add(link);
        return tripDtos;
    }
}
    
