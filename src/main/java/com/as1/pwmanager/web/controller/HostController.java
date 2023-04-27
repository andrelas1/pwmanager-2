package com.as1.pwmanager.web.controller;

import com.as1.pwmanager.persistence.model.Host;
import com.as1.pwmanager.service.host.impl.HostServiceImpl;
import com.as1.pwmanager.web.dto.HostDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/hosts")
public class HostController {

    private final HostServiceImpl hostService;

    public HostController(HostServiceImpl hostService) {
        this.hostService = hostService;
    }

    @GetMapping
    public Collection<HostDto> getAllHosts() {
        ArrayList<Host> hosts = (ArrayList<Host>) hostService.findAll();
        return hosts.stream().map(this::convertToDto).collect(Collectors.toSet());
    }

    @GetMapping("/{id}")
    public HostDto getHostDto(@PathVariable Long id) {
        Host host = hostService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Host not found"));
        return new HostDto(host);
    }

    @PostMapping
    public HostDto saveHost(@RequestBody Host host) {
        Host savedHost = hostService.save(host);

        return convertToDto(savedHost);
    }

    @DeleteMapping("/{id}")
    public HostDto deleteHost(@PathVariable Long id) {
        Host host = this.hostService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Host not found"));
        this.hostService.delete(host);

        return convertToDto(host);
    }

    private HostDto convertToDto(Host host) {
        return new HostDto(host);
    }

}
