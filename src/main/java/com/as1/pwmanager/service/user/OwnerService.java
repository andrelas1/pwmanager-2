package com.as1.pwmanager.service.user;

import com.as1.pwmanager.persistence.model.Owner;

import java.util.Optional;
import java.util.Set;

public interface OwnerService {

    Owner addHostname(Owner owner, String hostname);

    Owner save(String username, String password);

    void delete(Owner owner);

    Owner getByUsername(String username);

    Set<Owner> getAllUsers();

    Optional<Owner> findById(Long id);
}
