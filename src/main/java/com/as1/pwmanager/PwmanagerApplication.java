package com.as1.pwmanager;

import com.as1.pwmanager.persistence.model.Host;
import com.as1.pwmanager.service.impl.HostServiceImpl;
import com.as1.pwmanager.service.impl.LoginServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class PwmanagerApplication implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(PwmanagerApplication.class);
    @Autowired
    private HostServiceImpl hostService;

    @Autowired
    private LoginServiceImpl loginService;

    public static void main(String[] args) {
        SpringApplication.run(PwmanagerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
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

        loginService.save("email@email.com", host1);
        loginService.save("email@email.com", host2);
        loginService.save("email@email.com", host3);
    }
}
