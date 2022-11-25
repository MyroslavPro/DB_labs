package ua.lviv.iot.lab6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ua.lviv.iot.lab6.domain.Guide;
import ua.lviv.iot.lab6.domain.Trip;
import ua.lviv.iot.lab6.dto.GuideDto;
import ua.lviv.iot.lab6.dto.TripDto;
import ua.lviv.iot.lab6.dto.assembler.GuideDtoAssembler;
import ua.lviv.iot.lab6.dto.assembler.TripDtoAssembler;
import ua.lviv.iot.lab6.service.GuideService;
import ua.lviv.iot.lab6.service.TripService;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

@RestController
@RequestMapping(value = "/api/guides")
public class GuideController {
    @Autowired
    private GuideService guideService;
    @Autowired
    private TripService tripService;
    @Autowired
    private GuideDtoAssembler guideDtoAssembler;
    @Autowired
    private TripDtoAssembler tripDtoAssembler;

    @GetMapping(value = "/{guideId}")
    public ResponseEntity<GuideDto> getGuide(@PathVariable Integer guideId) {
    	Guide guide = guideService.findById(guideId);
    	GuideDto guideDto = guideDtoAssembler.toModel(guide);
        return new ResponseEntity<>(guideDto, HttpStatus.OK);
    }
    
    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<GuideDto>> getAllGuides() {
        List<Guide> guides = guideService.findAll();
        CollectionModel<GuideDto> guideDtos = guideDtoAssembler.toCollectionModel(guides);
        return new ResponseEntity<>(guideDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<GuideDto> addGuide(@RequestBody Guide guide) {
    	Guide newGuide= guideService.create(guide);
    	GuideDto guideDto = guideDtoAssembler.toModel(newGuide);
        return new ResponseEntity<>(guideDto, HttpStatus.CREATED);
    }
    
    @GetMapping(value = "/{guideId}/trips")
    public ResponseEntity<CollectionModel<TripDto>> getAllToursForLanguage(@PathVariable Integer guideId) {
    	List<Trip> trips = tripService.findTripsByTourId(guideId);
    	Link selfLink = linkTo(methodOn(GuideController.class).getAllToursForLanguage(guideId)).withSelfRel();
        CollectionModel<TripDto> tripDtos = tripDtoAssembler.toCollectionModel(trips, selfLink);
        return new ResponseEntity<>(tripDtos, HttpStatus.OK);
    }
    

    @PutMapping(value = "/{guideId}")
    public ResponseEntity<?> updateGuide(@RequestBody Guide updateGuide, @PathVariable Integer guideId) {
    	guideService.update(guideId, updateGuide);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{guideId}")
    public ResponseEntity<?> deleteLocation(@PathVariable Integer guideId) {
    	guideService.delete(guideId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
