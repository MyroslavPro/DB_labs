package ua.lviv.iot.lab6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ua.lviv.iot.lab6.domain.Client;
import ua.lviv.iot.lab6.domain.Trip;
import ua.lviv.iot.lab6.dto.ClientDto;
import ua.lviv.iot.lab6.dto.TripDto;
import ua.lviv.iot.lab6.dto.assembler.ClientDtoAssembler;
import ua.lviv.iot.lab6.dto.assembler.TripDtoAssembler;
import ua.lviv.iot.lab6.service.ClientService;
import ua.lviv.iot.lab6.service.TripService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

@RestController
@RequestMapping(value = "/api/trips")
public class TripController {
    @Autowired
    private TripService tripService;
    @Autowired
    private TripDtoAssembler tripDtoAssembler;

    @GetMapping(value = "/{tripId}")
    public ResponseEntity<TripDto> getTrip(@PathVariable Integer tripId) {
    	Trip trip= tripService.findById(tripId);
    	TripDto tripDto = tripDtoAssembler.toModel(trip);
        return new ResponseEntity<>(tripDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<TripDto>> getAllTrips() {
        List<Trip> trips= tripService.findAll();
        CollectionModel<TripDto> tripDtos = tripDtoAssembler.toCollectionModel(trips);
        return new ResponseEntity<>(tripDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<TripDto> addTrip(@RequestBody Trip trip) {
    	Trip newTrip = tripService.create(trip);
    	TripDto tripDto = tripDtoAssembler.toModel(newTrip);
        return new ResponseEntity<>(tripDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{tripId}")
    public ResponseEntity<?> updateTrip(@RequestBody Trip updateTrip, @PathVariable Integer tripId) {
    	tripService.update(tripId, updateTrip);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{tripId}")
    public ResponseEntity<?> deleteTrip(@PathVariable Integer tripId) {
    	tripService.delete(tripId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
