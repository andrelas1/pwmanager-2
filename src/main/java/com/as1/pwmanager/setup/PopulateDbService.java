package com.as1.pwmanager.setup;

import com.as1.pwmanager.persistence.model.Host;
import com.as1.pwmanager.service.host.impl.HostServiceImpl;
import com.as1.pwmanager.service.login.impl.LoginServiceImpl;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

public class PopulateDbService implements CommandLineRunner {

    @Value("${pwmanager.populatedb}")
    private boolean shouldPopulateDb;

    @Autowired
    private HostServiceImpl hostService;

    @Autowired
    private LoginServiceImpl loginService;
    private Logger logger = LoggerFactory.getLogger(PopulateDbService.class);

    public void setupDbEntities() {
        this.populateDb();
        Iterable<Host> hosts = this.hostService.findAll();
        for (Host host : hosts) {
            logger.info("HOST ID {}", host.getId());
            logger.info("HOST NAME {}", host.getName());
            logger.info("HOST DATE CREATED {}", host.getDateCreated());
            logger.info("HOST LOGINS {}", host.getLogins());
        }
    }

    private Host generateHost(String name) {
        Host host = new Host(name, LocalDate.now());
        return host;
    }

    private void populateDb() {
        Host host1 = this.hostService.save(this.generateHost("theguardian.com"));
        Host host2 = this.hostService.save(this.generateHost("nrc.nl"));
        Host host3 = this.hostService.save(this.generateHost("globo.com"));

        loginService.save("email@email.com", loginService.hashPassword("123456"), host1);
        loginService.save("email@email.com", loginService.hashPassword("password"), host2);
        loginService.save("email@email.com", loginService.hashPassword("112233"), host3);
    }

    @Override
    public void run(String... args) {
        if (this.shouldPopulateDb) {
            this.setupDbEntities();
        }
    }
}
