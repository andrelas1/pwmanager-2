package com.as1.pwmanager.web.controller;

import com.as1.pwmanager.persistence.model.Owner;
import com.as1.pwmanager.service.user.OwnerService;
import com.as1.pwmanager.web.dto.OwnerDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/owners")
public class OwnerController {

    private OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping
    public Set<OwnerDto> getUsers() {
        Set<Owner> owners = ownerService.getAllUsers();
        return owners.stream().map(this::convertToDto).collect(Collectors.toSet());
    }

    @GetMapping("/{id}")
    public OwnerDto getUserDto(@PathVariable Long id) {
        Owner owner = ownerService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Host not found"));
        return new OwnerDto(owner.getUsername(), owner.getPassword());
    }

    @PostMapping
    public OwnerDto saveUser(@RequestBody OwnerDto ownerDto) {
        Owner savedOwner = ownerService.save(ownerDto.getUsername(), ownerDto.getPassword());

        return convertToDto(savedOwner);
    }

    @DeleteMapping("/{id}")
    public OwnerDto deleteUser(@PathVariable Long id) {
        Owner owner = this.ownerService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Host not found"));
        this.ownerService.delete(owner);

        return convertToDto(owner);
    }

    private OwnerDto convertToDto(Owner owner) {
        return new OwnerDto(owner.getUsername(), owner.getPassword());
    }

}
