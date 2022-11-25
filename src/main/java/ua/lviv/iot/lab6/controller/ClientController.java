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
@RequestMapping(value = "/api/clients")
public class ClientController {
    @Autowired
    private ClientService clientService;
    @Autowired
    private ClientDtoAssembler сlientDtoAssembler;

    @GetMapping(value = "/{сlientId}")
    public ResponseEntity<ClientDto> getClient(@PathVariable Integer сlientId) {
    	Client сlient = clientService.findById(сlientId);
    	ClientDto сlientDto = сlientDtoAssembler.toModel(сlient);
        return new ResponseEntity<>(сlientDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<ClientDto>> getAllClients() {
        List<Client> сlients = clientService.findAll();
        CollectionModel<ClientDto> сlientDtos = сlientDtoAssembler.toCollectionModel(сlients);
        return new ResponseEntity<>(сlientDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<ClientDto> addClient(@RequestBody Client client) {
    	Client newClient = clientService.create(client);
    	ClientDto сlientDto = сlientDtoAssembler.toModel(newClient);
        return new ResponseEntity<>(сlientDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{clientId}")
    public ResponseEntity<?> updateClient(@RequestBody Client updateClient, @PathVariable Integer clientId) {
    	clientService.update(clientId, updateClient);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{clientId}")
    public ResponseEntity<?> deleteClient(@PathVariable Integer clientId) {
    	clientService.delete(clientId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @PostMapping(value = "/addtoManyToMany/{client}/{trip}")
    public ResponseEntity<?> fillManyToManyForClientsAndTrips(@PathVariable Integer client,@PathVariable Integer trip){
    	clientService.fillManyToManyForClientsAndTrips(client,trip);
    	return new ResponseEntity<>(HttpStatus.OK);
    }
}
