package ua.lviv.iot.lab6.dto.assembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import ua.lviv.iot.lab6.controller.TourController;
import ua.lviv.iot.lab6.domain.Tour;
import ua.lviv.iot.lab6.dto.TourDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class TourDtoAssembler implements RepresentationModelAssembler<Tour, TourDto> {
    @Override
    public TourDto toModel(Tour entity) {
    	TourDto tourDto = TourDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .price(entity.getPrice())
                .tourDays(entity.getTourDays())
                .description(entity.getDescription())
                .build();
        Link selfLink = linkTo(methodOn(TourController.class).getTour(tourDto.getId())).withSelfRel();
        tourDto.add(selfLink);
        return tourDto;
    }

    @Override
    public CollectionModel<TourDto> toCollectionModel(Iterable<? extends Tour> entities) {
        CollectionModel<TourDto> tourDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(TourController.class).getAllTours()).withSelfRel();
        tourDtos.add(selfLink);
        return tourDtos;
    }

    public CollectionModel<TourDto> toCollectionModel(Iterable<? extends Tour> entities,  Link link) {
        CollectionModel<TourDto> tourDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        tourDtos.add(link);
        return tourDtos;
    }
}
    
