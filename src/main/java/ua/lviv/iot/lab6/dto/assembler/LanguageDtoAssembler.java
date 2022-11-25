package ua.lviv.iot.lab6.dto.assembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import ua.lviv.iot.lab6.controller.LanguageController;
import ua.lviv.iot.lab6.domain.Language;
import ua.lviv.iot.lab6.dto.LanguageDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class LanguageDtoAssembler implements RepresentationModelAssembler<Language, LanguageDto> {
    @Override
    public LanguageDto toModel(Language entity) {
    	LanguageDto languageDto = LanguageDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
        Link selfLink = linkTo(methodOn(LanguageController.class).getLanguage(languageDto.getId())).withSelfRel();
        languageDto.add(selfLink);
        return languageDto;
    }

    @Override
    public CollectionModel<LanguageDto> toCollectionModel(Iterable<? extends Language> entities) {
        CollectionModel<LanguageDto> languageDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(LanguageController.class).getAllLanguages()).withSelfRel();
        languageDtos.add(selfLink);
        return languageDtos;
    }

    public CollectionModel<LanguageDto> toCollectionModel(Iterable<? extends Language> entities,  Link link) {
        CollectionModel<LanguageDto> languageDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        languageDtos.add(link);
        return languageDtos;
    }
}
    
