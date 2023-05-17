package com.as1.pwmanager.setup;

import com.as1.pwmanager.persistence.model.Owner;
import com.as1.pwmanager.service.login.impl.LoginServiceImpl;
import com.as1.pwmanager.service.user.OwnerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;

import java.time.LocalDate;

public class PopulateDbService implements CommandLineRunner {

    @Value("${pwmanager.populatedb}")
    private boolean shouldPopulateDb;

    @Autowired
    OwnerService ownerService;

    @Autowired
    private LoginServiceImpl loginService;
    private Logger logger = LoggerFactory.getLogger(PopulateDbService.class);

    public void setupDbEntities() {
        this.populateDb();
        Iterable<Owner> users = this.ownerService.getAllUsers();
        for (Owner owner : users) {
            logger.info("USER ID {}", owner.getId());
            logger.info("USER USERNAME {}", owner.getUsername());
            logger.info("USER DATE CREATED {}", owner.getDateCreated());
            logger.info("USER LOGINS {}", owner.getLogins());
        }
    }

    private Owner generateUser(String username, String password) {
        Owner owner = new Owner(username, password, LocalDate.now());
        return owner;
    }

    private void populateDb() {
        Owner owner1 = this.ownerService.save("user@user.com", "123456");
        Owner owner2 = this.ownerService.save("another_user@user.com", "abcdef");
        Owner owner3 = this.ownerService.save("one_more_user@user.com", "aabbcc");


        loginService.save("email@email.com", loginService.hashPassword("123456"), owner1);
        loginService.save("email@email.com", loginService.hashPassword("abcdef"), owner2);
        loginService.save("email@email.com", loginService.hashPassword("aabbcc"), owner3);
    }

    @Override
    public void run(String... args) {
        if (this.shouldPopulateDb) {
            this.setupDbEntities();
        }
    }
}
