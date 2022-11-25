package ua.lviv.iot.lab6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ua.lviv.iot.lab6.domain.Location;
import ua.lviv.iot.lab6.domain.Tour;
import ua.lviv.iot.lab6.dto.LocationDto;
import ua.lviv.iot.lab6.dto.TourDto;
import ua.lviv.iot.lab6.dto.assembler.LocationDtoAssembler;
import ua.lviv.iot.lab6.dto.assembler.TourDtoAssembler;
import ua.lviv.iot.lab6.service.LocationService;
import ua.lviv.iot.lab6.service.TourService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

@RestController
@RequestMapping(value = "/api/locations")
public class LocationController {
    @Autowired
    private LocationService locationService;
    @Autowired
    private TourService tourService;
    @Autowired
    private LocationDtoAssembler locationDtoAssembler;
    @Autowired
    private TourDtoAssembler tourDtoAssembler;

    @GetMapping(value = "/{locationId}")
    public ResponseEntity<LocationDto> getLocation(@PathVariable Integer locationId) {
    	Location location = locationService.findById(locationId);
    	LocationDto locationDto = locationDtoAssembler.toModel(location);
        return new ResponseEntity<>(locationDto, HttpStatus.OK);
    }
    
    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<LocationDto>> getAllLocations() {
        List<Location> locations = locationService.findAll();
        CollectionModel<LocationDto> locationDtos = locationDtoAssembler.toCollectionModel(locations);
        return new ResponseEntity<>(locationDtos, HttpStatus.OK);
    }
    
    @GetMapping(value = "/{locationId}/tours")
    public ResponseEntity<CollectionModel<TourDto>> getAllToursForLanguage(@PathVariable Integer locationId) {
    	List<Tour> tours = tourService.findToursByLanguageId(locationId);
    	Link selfLink = linkTo(methodOn(LocationController.class).getAllToursForLanguage(locationId)).withSelfRel();
        CollectionModel<TourDto> tourDtos = tourDtoAssembler.toCollectionModel(tours, selfLink);
        return new ResponseEntity<>(tourDtos, HttpStatus.OK);
    }
    

    @PostMapping(value = "")
    public ResponseEntity<LocationDto> addLocation(@RequestBody Location location) {
    	Location newLocation= locationService.create(location);
    	LocationDto locationDto = locationDtoAssembler.toModel(newLocation);
        return new ResponseEntity<>(locationDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{locationId}")
    public ResponseEntity<?> updateLocation(@RequestBody Location updateLocation, @PathVariable Integer locationId) {
    	locationService.update(locationId, updateLocation);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{locationId}")
    public ResponseEntity<?> deleteLocation(@PathVariable Integer locationId) {
    	locationService.delete(locationId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
