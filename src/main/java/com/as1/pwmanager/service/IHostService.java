package com.as1.pwmanager.service;

import com.as1.pwmanager.persistence.model.Host;

import java.util.Optional;

public interface IHostService {
    public Optional<Host> findById(Long id);

    public Optional<Host> findByName(String name);

    public Host save(Host host);

    public Iterable<Host> findAll();

    void delete(Host host);
}
