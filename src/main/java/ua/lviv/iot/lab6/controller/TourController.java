package ua.lviv.iot.lab6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ua.lviv.iot.lab6.domain.Tour;
import ua.lviv.iot.lab6.domain.Trip;
import ua.lviv.iot.lab6.dto.TourDto;
import ua.lviv.iot.lab6.dto.TripDto;
import ua.lviv.iot.lab6.dto.assembler.TourDtoAssembler;
import ua.lviv.iot.lab6.dto.assembler.TripDtoAssembler;
import ua.lviv.iot.lab6.service.TourService;
import ua.lviv.iot.lab6.service.TripService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

@RestController
@RequestMapping(value = "/api/tours")
public class TourController {
    @Autowired
    private TourService tourService;
    @Autowired
    private TripService tripService;
    @Autowired
    private TourDtoAssembler tourDtoAssembler;
    @Autowired
    private TripDtoAssembler tripDtoAssembler;

    @GetMapping(value = "/{tourId}")
    public ResponseEntity<TourDto> getTour(@PathVariable Integer tourId) {
    	Tour tour= tourService.findById(tourId);
    	TourDto tourDto = tourDtoAssembler.toModel(tour);
        return new ResponseEntity<>(tourDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<TourDto>> getAllTours() {
        List<Tour> tours= tourService.findAll();
        CollectionModel<TourDto> tourDtos = tourDtoAssembler.toCollectionModel(tours);
        return new ResponseEntity<>(tourDtos, HttpStatus.OK);
    }
    
    //AvailableTours
    @GetMapping(value = "/available/{price}")
    public ResponseEntity<CollectionModel<TourDto>> getAllAvailableTours(@PathVariable Double price) {
        List<Tour> tours= tourService.findAvailableToursByPrice(price);
        CollectionModel<TourDto> tourDtos = tourDtoAssembler.toCollectionModel(tours);
        return new ResponseEntity<>(tourDtos, HttpStatus.OK);
    }
    
    
    @GetMapping(value = "/{tourId}/trips")
    public ResponseEntity<CollectionModel<TripDto>> getAllToursForTrip(@PathVariable Integer tourId) {
    	List<Trip> trips = tripService.findTripsByTourId(tourId);
    	Link selfLink = linkTo(methodOn(TourController.class).getAllToursForTrip(tourId)).withSelfRel();
        CollectionModel<TripDto> tripDtos = tripDtoAssembler.toCollectionModel(trips, selfLink);
        return new ResponseEntity<>(tripDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<TourDto> addTour(@RequestBody Tour tour) {
    	Tour newTour = tourService.create(tour);
    	TourDto tourDto = tourDtoAssembler.toModel(newTour);
        return new ResponseEntity<>(tourDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{tourId}")
    public ResponseEntity<?> updateTour(@RequestBody Tour updateTour, @PathVariable Integer tourId) {
    	tourService.update(tourId, updateTour);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{tourId}")
    public ResponseEntity<?> deleteClient(@PathVariable Integer tourId) {
    	tourService.delete(tourId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
