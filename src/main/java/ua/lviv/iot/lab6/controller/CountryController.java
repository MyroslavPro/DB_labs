package ua.lviv.iot.lab6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ua.lviv.iot.lab6.domain.Client;
import ua.lviv.iot.lab6.domain.Country;
import ua.lviv.iot.lab6.domain.Guide;
import ua.lviv.iot.lab6.domain.Location;
import ua.lviv.iot.lab6.dto.ClientDto;
import ua.lviv.iot.lab6.dto.CountryDto;
import ua.lviv.iot.lab6.dto.GuideDto;
import ua.lviv.iot.lab6.dto.LocationDto;
import ua.lviv.iot.lab6.dto.assembler.ClientDtoAssembler;
import ua.lviv.iot.lab6.dto.assembler.CountryDtoAssembler;
import ua.lviv.iot.lab6.dto.assembler.GuideDtoAssembler;
import ua.lviv.iot.lab6.dto.assembler.LocationDtoAssembler;
import ua.lviv.iot.lab6.service.ClientService;
import ua.lviv.iot.lab6.service.CountryService;
import ua.lviv.iot.lab6.service.GuideService;
import ua.lviv.iot.lab6.service.LocationService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

@RestController
@RequestMapping(value = "/api/countries")
public class CountryController {
    @Autowired
    private CountryService countryService;
    @Autowired
    private LocationService locationService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private GuideService guideService;
    @Autowired
    private CountryDtoAssembler countryDtoAssembler;
    @Autowired
    private LocationDtoAssembler locationDtoAssembler;
    @Autowired
    private ClientDtoAssembler clientDtoAssembler;
    @Autowired
    private GuideDtoAssembler guideDtoAssembler;

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
    
    @GetMapping(value = "/{сountryId}/clients")
    public ResponseEntity<CollectionModel<ClientDto>> getAllClientsForCountry(@PathVariable Integer сountryId) {
    	List<Client> clients = clientService.findClientsByCountryId(сountryId);
    	Link selfLink = linkTo(methodOn(CountryController.class).getAllClientsForCountry(сountryId)).withSelfRel();
        CollectionModel<ClientDto> clientDtos = clientDtoAssembler.toCollectionModel(clients, selfLink);
        return new ResponseEntity<>(clientDtos, HttpStatus.OK);
    }
    
    @GetMapping(value = "/{сountryId}/guides")
    public ResponseEntity<CollectionModel<GuideDto>> getAllGuidesForCountry(@PathVariable Integer сountryId) {
    	List<Guide> guides = guideService.findGuidesByCountryId(сountryId);
    	Link selfLink = linkTo(methodOn(CountryController.class).getAllGuidesForCountry(сountryId)).withSelfRel();
        CollectionModel<GuideDto> guideDtos = guideDtoAssembler.toCollectionModel(guides, selfLink);
        return new ResponseEntity<>(guideDtos, HttpStatus.OK);
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
    
    @PostMapping(value = "/addTen")
    public ResponseEntity<?> addTenCountries(){
        countryService.addTenCountries();
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
    @PostMapping(value = "/createDBFromCountries")
    public ResponseEntity<?> generateTablesFromCountriesWithCursor(){
        countryService.generateTablesFromCountriesWithCursor();
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
}
