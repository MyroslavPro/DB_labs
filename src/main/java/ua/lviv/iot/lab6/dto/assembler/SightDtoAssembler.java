package ua.lviv.iot.lab6.dto.assembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import ua.lviv.iot.lab6.controller.SightController;
import ua.lviv.iot.lab6.domain.Sight;
import ua.lviv.iot.lab6.dto.SightDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class SightDtoAssembler implements RepresentationModelAssembler<Sight, SightDto> {
    @Override
    public SightDto toModel(Sight entity) {
    	SightDto sightDto = SightDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
        Link selfLink = linkTo(methodOn(SightController.class).getSight(sightDto.getId())).withSelfRel();
        sightDto.add(selfLink);
        return sightDto;
    }

    @Override
    public CollectionModel<SightDto> toCollectionModel(Iterable<? extends Sight> entities) {
        CollectionModel<SightDto> sightDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(SightController.class).getAllSights()).withSelfRel();
        sightDtos.add(selfLink);
        return sightDtos;
    }
}