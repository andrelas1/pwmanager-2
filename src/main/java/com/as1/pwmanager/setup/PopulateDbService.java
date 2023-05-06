package com.as1.pwmanager.setup;

import com.as1.pwmanager.persistence.model.Host;
import com.as1.pwmanager.service.host.IHostService;
import com.as1.pwmanager.service.login.ILoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;

import java.time.LocalDate;

public class PopulateDbService implements CommandLineRunner {

    @Value("${pwmanager.populatedb}")
    private boolean shouldPopulateDb;

    private IHostService hostService;
    private ILoginService loginService;
    private Logger logger = LoggerFactory.getLogger(PopulateDbService.class);

    public PopulateDbService(IHostService hostService, ILoginService loginService) {
        this.hostService = hostService;
        this.loginService = loginService;
    }

    public void setupDbEntities() {
        logger.info("---------------- START: SETUP DB -------------------");
        this.populateDb();
        Iterable<Host> hosts = this.hostService.findAll();
        for (Host host : hosts) {
            logger.info("HOST ID {}", host.getId());
            logger.info("HOST NAME {}", host.getName());
            logger.info("HOST DATE CREATED {}", host.getDateCreated());
            logger.info("HOST LOGINS {}", host.getLogins());
        }
        logger.info("---------------- END: SETUP DB -------------------");
    }

    private Host generateHost(String name) {
        Host host = new Host(name, LocalDate.now());
        return host;
    }

    private void populateDb() {
        Host host1 = this.hostService.save(this.generateHost("theguardian.com"));
        Host host2 = this.hostService.save(this.generateHost("nrc.nl"));
        Host host3 = this.hostService.save(this.generateHost("globo.com"));

        loginService.save("email@email.com", host1);
        loginService.save("email@email.com", host2);
        loginService.save("email@email.com", host3);
    }

    @Override
    public void run(String... args) {
        if (this.shouldPopulateDb) {
            this.setupDbEntities();
        }
    }
}
