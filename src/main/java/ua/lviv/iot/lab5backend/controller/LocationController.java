package ua.lviv.iot.lab5backend.controller;

import ua.lviv.iot.lab5backend.dto.LocationDto;
import ua.lviv.iot.lab5backend.dto.assembler.LocationDtoAssembler;
import ua.lviv.iot.lab5backend.domain.Location;
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
@RequestMapping(value = "/api/location")
public class LocationController {
    @Autowired
    private LocationService locationService;
    @Autowired
    private LocationDtoAssembler locationDtoAssembler;

    @GetMapping(value = "/{locationId}")
    public ResponseEntity<LocationDto> getCountry(@PathVariable Integer locationId) {
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

    @PostMapping(value = "")
    public ResponseEntity<LocationDto> addCountry(@RequestBody Location location) {
    	Location newLocation= locationService.create(location);
    	LocationDto locationDto = locationDtoAssembler.toModel(newLocation);
        return new ResponseEntity<>(locationDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{locationId}")
    public ResponseEntity<?> updateCountry(@RequestBody Location updateLocation, @PathVariable Integer locationId) {
    	locationService.update(locationId, updateLocation);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{locationId}")
    public ResponseEntity<?> deleteCountry(@PathVariable Integer locationId) {
    	locationService.delete(locationId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
