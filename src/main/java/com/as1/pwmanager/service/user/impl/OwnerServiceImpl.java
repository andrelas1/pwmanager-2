package com.as1.pwmanager.service.user.impl;

import com.as1.pwmanager.persistence.model.Owner;
import com.as1.pwmanager.persistence.repository.OwnerRepository;
import com.as1.pwmanager.service.user.OwnerService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class OwnerServiceImpl implements OwnerService {

    private OwnerRepository ownerRepository;

    public OwnerServiceImpl(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public Owner addHostname(Owner owner, String hostname) {
        return null;
    }

    @Override
    public Owner save(String username, String password) {
        Owner owner = new Owner(username, password, LocalDate.now());
        this.ownerRepository.save(owner);

        return owner;
    }

    @Override
    public void delete(Owner owner) {

    }

    public void delete(String username) {
        Owner owner = this.ownerRepository.findByUsername(username);
        this.ownerRepository.delete(owner);
    }

    @Override
    public Owner getByUsername(String username) {
        return this.ownerRepository.findByUsername(username);
    }

    @Override
    public Set<Owner> getAllUsers() {
        return StreamSupport.stream(this.ownerRepository.findAll().spliterator(), false).collect(Collectors.toSet());
    }

    @Override
    public Optional<Owner> findById(Long id) {
        return this.ownerRepository.findById(id);
    }
}
