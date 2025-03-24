package com.CMPDI.CPRMSE.NE.poratl.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.CMPDI.CPRMSE.NE.poratl.repositories.AppUserRepository;
import com.CMPDI.CPRMSE.NE.poratl.models.AppUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class PasswordEncodingScript implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(PasswordEncodingScript.class);

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        Iterable<AppUser> users = appUserRepository.findAll();

        for (AppUser user : users) {
            if (!user.getPassword().startsWith("$2a$")) { // Ensure encoding is done only for non-encoded passwords
                String encodedPassword = passwordEncoder.encode(user.getPassword());//i cahnged here from getAadhar method to getPassword method for adding signup feature for users only
                user.setPassword(encodedPassword);
                appUserRepository.save(user);
                logger.info("Encoded password for user: " + user.getEmployeecode());
            }
        }
    }
}
