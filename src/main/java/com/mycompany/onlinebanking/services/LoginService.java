// his LoginService class handles the authentication of users in the online banking system. It has an authenticate method that takes a username and password as input and checks if there is a user in the database with the given username and password. If the authentication is successful, the method returns true, otherwise it returns false.
// The class uses the SLF4J logging framework to log events related to the authentication process, such as attempting to authenticate a user or successfully authenticating a user. The logger static field is initialized with a logger for the LoginService class, and the logger is used to log relevant events.
// The class has an @Autowired field for the UserRepository, which is used to retrieve user records from the database. The authenticate method uses the findByUsername method of the UserRepository to retrieve the user with the given username. If the user is found and the password matches the given password, the authentication is successful.


package com.mycompany.onlinebanking.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.onlinebanking.models.User;
import com.mycompany.onlinebanking.repositories.UserRepository;

@Service
public class LoginService {
    private static final Logger logger = LoggerFactory.getLogger(LoginService.class);

    @Autowired
    private UserRepository userRepository;

    public boolean authenticate(String username, String password) {
        logger.info("Authenticating user with username {}", username);
        User user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            logger.info("User authenticated successfully");
            return true;
        } else {
            logger.info("Authentication failed");
            return false;
        }
    }
}
