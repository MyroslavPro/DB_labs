package ua.lviv.iot.lab6.dto.assembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import ua.lviv.iot.lab6.controller.PhoneController;
import ua.lviv.iot.lab6.domain.Phone;
import ua.lviv.iot.lab6.dto.PhoneDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class PhoneDtoAssembler implements RepresentationModelAssembler<Phone, PhoneDto> {
    @Override
    public PhoneDto toModel(Phone entity) {
    	PhoneDto phoneDto = PhoneDto.builder()
                .id(entity.getId())
                .number(entity.getNumber())
                .build();
        Link selfLink = linkTo(methodOn(PhoneController.class).getPhone(phoneDto.getId())).withSelfRel();
        phoneDto.add(selfLink);
        return phoneDto;
    }

    @Override
    public CollectionModel<PhoneDto> toCollectionModel(Iterable<? extends Phone> entities) {
        CollectionModel<PhoneDto> phoneDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(PhoneController.class).getAllPhones()).withSelfRel();
        phoneDtos.add(selfLink);
        return phoneDtos;
    }

    public CollectionModel<PhoneDto> toCollectionModel(Iterable<? extends Phone> entities,  Link link) {
        CollectionModel<PhoneDto> phoneDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        phoneDtos.add(link);
        return phoneDtos;
    }
}
    
