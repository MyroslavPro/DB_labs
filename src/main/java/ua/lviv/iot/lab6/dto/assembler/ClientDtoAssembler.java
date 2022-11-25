package ua.lviv.iot.lab6.dto.assembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import ua.lviv.iot.lab6.controller.ClientController;
import ua.lviv.iot.lab6.domain.Client;
import ua.lviv.iot.lab6.dto.ClientDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ClientDtoAssembler implements RepresentationModelAssembler<Client, ClientDto> {
    @Override
    public ClientDto toModel(Client entity) {
    	ClientDto clientDto = ClientDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
        Link selfLink = linkTo(methodOn(ClientController.class).getClient(clientDto.getId())).withSelfRel();
        clientDto.add(selfLink);
        return clientDto;
    }

    @Override
    public CollectionModel<ClientDto> toCollectionModel(Iterable<? extends Client> entities) {
        CollectionModel<ClientDto> clientDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(ClientController.class).getAllClients()).withSelfRel();
        clientDtos.add(selfLink);
        return clientDtos;
    }

    public CollectionModel<ClientDto> toCollectionModel(Iterable<? extends Client> entities,  Link link) {
        CollectionModel<ClientDto> clientDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        clientDtos.add(link);
        return clientDtos;
    }
}
    
