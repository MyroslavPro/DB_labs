package ua.lviv.iot.lab5backend.controller;

import ua.lviv.iot.lab5backend.dto.CountryDto;
import ua.lviv.iot.lab5backend.dto.LocationDto;
import ua.lviv.iot.lab5backend.dto.assembler.CountryDtoAssembler;
import ua.lviv.iot.lab5backend.dto.assembler.LocationDtoAssembler;
import ua.lviv.iot.lab5backend.domain.Country;
import ua.lviv.iot.lab5backend.domain.Location;
import ua.lviv.iot.lab5backend.service.CountryService;
import ua.lviv.iot.lab5backend.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

@RestController
@RequestMapping(value = "/api/country")
public class CountryController {
    @Autowired
    private CountryService countryService;
    @Autowired
    private LocationService locationService;
    @Autowired
    private CountryDtoAssembler countryDtoAssembler;
    @Autowired
    private LocationDtoAssembler locationDtoAssembler;

    @GetMapping(value = "/{сountryId}")
    public ResponseEntity<CountryDto> getCountry(@PathVariable Integer сountryId) {
    	Country сountry = countryService.findById(сountryId);
    	CountryDto сountryDto = countryDtoAssembler.toModel(сountry);
        return new ResponseEntity<>(сountryDto, HttpStatus.OK);
    }
    
    @GetMapping(value = "/{сountryId}/locations")
    public ResponseEntity<CollectionModel<LocationDto>> getAllLocationsForCountry(@PathVariable Integer сountryId) {
    	List<Location> locations = locationService.findLocationsByCountryId(сountryId);
    	Link selfLink = linkTo(methodOn(CountryController.class).getAllLocationsForCountry(сountryId)).withSelfRel();
        CollectionModel<LocationDto> locationDtos = locationDtoAssembler.toCollectionModel(locations, selfLink);
    	
    	
        return new ResponseEntity<>(locationDtos, HttpStatus.OK);
    }
    

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<CountryDto>> getAllСountries() {
        List<Country> countries = countryService.findAll();
        CollectionModel<CountryDto> countryDtos = countryDtoAssembler.toCollectionModel(countries);
        return new ResponseEntity<>(countryDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<CountryDto> addCountry(@RequestBody Country country) {
    	Country newCountry = countryService.create(country);
    	CountryDto countryDto = countryDtoAssembler.toModel(newCountry);
        return new ResponseEntity<>(countryDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{countryId}")
    public ResponseEntity<?> updateCountry(@RequestBody Country updateCountry, @PathVariable Integer countryId) {
    	countryService.update(countryId, updateCountry);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{countryId}")
    public ResponseEntity<?> deleteCountry(@PathVariable Integer countryId) {
    	countryService.delete(countryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
