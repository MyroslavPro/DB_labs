package ua.lviv.iot.lab6.dto.assembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import ua.lviv.iot.lab6.controller.GuideController;
import ua.lviv.iot.lab6.domain.Guide;
import ua.lviv.iot.lab6.dto.GuideDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class GuideDtoAssembler implements RepresentationModelAssembler<Guide, GuideDto> {
    @Override
    public GuideDto toModel(Guide entity) {
    	GuideDto guideDto = GuideDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .surname(entity.getSurname())
                .email(entity.getEmail())
                .build();
        Link selfLink = linkTo(methodOn(GuideController.class).getGuide(guideDto.getId())).withSelfRel();
        guideDto.add(selfLink);
        return guideDto;
    }

    @Override
    public CollectionModel<GuideDto> toCollectionModel(Iterable<? extends Guide> entities) {
        CollectionModel<GuideDto> guideDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(GuideController.class).getAllGuides()).withSelfRel();
        guideDtos.add(selfLink);
        return guideDtos;
    }

    public CollectionModel<GuideDto> toCollectionModel(Iterable<? extends Guide> entities,  Link link) {
        CollectionModel<GuideDto> guideDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        guideDtos.add(link);
        return guideDtos;
    }
}
    
