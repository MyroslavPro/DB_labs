package ua.lviv.iot.lab6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ua.lviv.iot.lab6.domain.Client;
import ua.lviv.iot.lab6.domain.Language;
import ua.lviv.iot.lab6.domain.Tour;
import ua.lviv.iot.lab6.dto.ClientDto;
import ua.lviv.iot.lab6.dto.LanguageDto;
import ua.lviv.iot.lab6.dto.TourDto;
import ua.lviv.iot.lab6.dto.assembler.ClientDtoAssembler;
import ua.lviv.iot.lab6.dto.assembler.LanguageDtoAssembler;
import ua.lviv.iot.lab6.dto.assembler.TourDtoAssembler;
import ua.lviv.iot.lab6.service.ClientService;
import ua.lviv.iot.lab6.service.LanguageService;
import ua.lviv.iot.lab6.service.TourService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

@RestController
@RequestMapping(value = "/api/languages")
public class LanguageController {
    @Autowired
    private LanguageService languageService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private TourService tourService;
    @Autowired
    private LanguageDtoAssembler languageDtoAssembler;
    @Autowired
    private ClientDtoAssembler clientDtoAssembler;
    @Autowired
    private TourDtoAssembler tourDtoAssembler;

    @GetMapping(value = "/{languageId}")
    public ResponseEntity<LanguageDto> getLanguage(@PathVariable Integer languageId) {
    	Language language = languageService.findById(languageId);
    	LanguageDto languageDto = languageDtoAssembler.toModel(language);
        return new ResponseEntity<>(languageDto, HttpStatus.OK);
    }
    
    @GetMapping(value = "/{languageId}/clients")
    public ResponseEntity<CollectionModel<ClientDto>> getAllClientsForLanguage(@PathVariable Integer languageId) {
    	List<Client> clients = clientService.findClientsByLanguageId(languageId);
    	Link selfLink = linkTo(methodOn(LanguageController.class).getAllClientsForLanguage(languageId)).withSelfRel();
        CollectionModel<ClientDto> clientDtos = clientDtoAssembler.toCollectionModel(clients, selfLink);
        return new ResponseEntity<>(clientDtos, HttpStatus.OK);
    }
    
    @GetMapping(value = "/{languageId}/tours")
    public ResponseEntity<CollectionModel<TourDto>> getAllToursForLanguage(@PathVariable Integer languageId) {
    	List<Tour> tours = tourService.findToursByLanguageId(languageId);
    	Link selfLink = linkTo(methodOn(LanguageController.class).getAllToursForLanguage(languageId)).withSelfRel();
        CollectionModel<TourDto> tourDtos = tourDtoAssembler.toCollectionModel(tours, selfLink);
        return new ResponseEntity<>(tourDtos, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<LanguageDto>> getAllLanguages() {
        List<Language> locations = languageService.findAll();
        CollectionModel<LanguageDto> languageDtos = languageDtoAssembler.toCollectionModel(locations);
        return new ResponseEntity<>(languageDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<LanguageDto> addLanguage(@RequestBody Language language) {
    	Language newLanguage= languageService.create(language);
    	LanguageDto languageDto = languageDtoAssembler.toModel(newLanguage);
        return new ResponseEntity<>(languageDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{languageId}")
    public ResponseEntity<?> updateLanguage(@RequestBody Language updateLanguage, @PathVariable Integer languageId) {
    	languageService.update(languageId, updateLanguage);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{languageId}")
    public ResponseEntity<?> deleteLanguage(@PathVariable Integer languageId) {
    	languageService.delete(languageId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
