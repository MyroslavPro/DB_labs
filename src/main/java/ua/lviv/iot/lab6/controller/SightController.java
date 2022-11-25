package ua.lviv.iot.lab6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ua.lviv.iot.lab6.domain.Sight;
import ua.lviv.iot.lab6.dto.SightDto;
import ua.lviv.iot.lab6.dto.assembler.SightDtoAssembler;
import ua.lviv.iot.lab6.service.SightService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/sights")
public class SightController {
    @Autowired
    private SightService sightService;
    
    @Autowired
    private SightDtoAssembler sightDtoAssembler;

    @GetMapping(value = "/{sightId}")
    public ResponseEntity<SightDto> getSight(@PathVariable Integer sightId) {
    	Sight sight = sightService.findById(sightId);
    	SightDto sightDto = sightDtoAssembler.toModel(sight);
        return new ResponseEntity<>(sightDto, HttpStatus.OK);
    }
    
    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<SightDto>> getAllSights() {
        List<Sight> sights = sightService.findAll();
        CollectionModel<SightDto> sightDtos = sightDtoAssembler.toCollectionModel(sights);
        return new ResponseEntity<>(sightDtos, HttpStatus.OK);
    }
    
    @PostMapping(value = "")
    public ResponseEntity<SightDto> addSight(@RequestBody Sight sight) {
    	Sight newSight= sightService.create(sight);
    	SightDto sightDto = sightDtoAssembler.toModel(newSight);
        return new ResponseEntity<>(sightDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{sightId}")
    public ResponseEntity<?> updateSight(@RequestBody Sight updateSight, @PathVariable Integer sightId) {
    	sightService.update(sightId, updateSight);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{sightId}")
    public ResponseEntity<?> deleteSight(@PathVariable Integer sightId) {
    	sightService.delete(sightId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
