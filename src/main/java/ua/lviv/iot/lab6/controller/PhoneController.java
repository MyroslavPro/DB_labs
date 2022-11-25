package ua.lviv.iot.lab6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ua.lviv.iot.lab6.domain.Phone;
import ua.lviv.iot.lab6.dto.PhoneDto;
import ua.lviv.iot.lab6.dto.assembler.PhoneDtoAssembler;
import ua.lviv.iot.lab6.service.PhoneService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/phones")
public class PhoneController {
    @Autowired
    private PhoneService phoneService;
    @Autowired
    private PhoneDtoAssembler phoneDtoAssembler;

    @GetMapping(value = "/{phoneId}")
    public ResponseEntity<PhoneDto> getPhone(@PathVariable Integer phoneId) {
    	Phone phone = phoneService.findById(phoneId);
    	PhoneDto phoneDto = phoneDtoAssembler.toModel(phone);
        return new ResponseEntity<>(phoneDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<PhoneDto>> getAllPhones() {
        List<Phone> phones = phoneService.findAll();
        CollectionModel<PhoneDto> phoneDtos = phoneDtoAssembler.toCollectionModel(phones);
        return new ResponseEntity<>(phoneDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<PhoneDto> addPhone(@RequestBody Phone phone) {
    	Phone newPhone= phoneService.create(phone);
    	PhoneDto phoneDto = phoneDtoAssembler.toModel(newPhone);
        return new ResponseEntity<>(phoneDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{phoneId}")
    public ResponseEntity<?> updatePhone(@RequestBody Phone updatePhone, @PathVariable Integer phoneId) {
    	phoneService.update(phoneId, updatePhone);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{phoneId}")
    public ResponseEntity<?> deletePhone(@PathVariable Integer phoneId) {
    	phoneService.delete(phoneId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
