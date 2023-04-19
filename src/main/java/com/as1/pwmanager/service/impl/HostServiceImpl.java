package com.as1.pwmanager.service.impl;

import com.as1.pwmanager.persistence.model.Host;
import com.as1.pwmanager.persistence.repository.IHostRepository;
import com.as1.pwmanager.service.IHostService;
import com.as1.pwmanager.service.ILoginService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HostServiceImpl implements IHostService {
    private IHostRepository hostRepository;
    private ILoginService loginService;

    public HostServiceImpl(IHostRepository hostRepository, ILoginService loginService) {
        this.hostRepository = hostRepository;
        this.loginService = loginService;
    }

    @Override
    public Optional<Host> findById(Long id) {
        return this.hostRepository.findById(id);
    }

    @Override
    public Optional<Host> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public Host save(Host host) {
        this.hostRepository.save(host);

        return host;
    }

    @Override
    public Iterable<Host> findAll() {
        return this.hostRepository.findAll();
    }

    @Override
    public void delete(Host host) {
        // TODO: do I really need to do this ? 
        // remove bidirectional relationship before moving on
        this.resetBidirectionalRelationships(host);
        this.hostRepository.delete(host);
    }

    private void resetBidirectionalRelationships(Host host) {
        this.loginService.resetHostRelationship(host);
    }
}
