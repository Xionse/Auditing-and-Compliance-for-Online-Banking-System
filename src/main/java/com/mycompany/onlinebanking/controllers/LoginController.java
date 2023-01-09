// LoginController class handles login requests for the online banking system. It has a login method that is called when a user submits the login form. The login method calls the login method of the LoginService to verify the username and password, and either redirects the user to the accounts page or displays an error message depending on the result. The class also has a showLoginForm method that simply returns the login template
//  The class uses the SLF4J logging framework to log the login attempts. The logger static field is initialized with a logger for the LoginController class, and the logger is used to log relevant events, such as successful or unsuccessful login attempts.

package com.mycompany.onlinebanking.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.mycompany.onlinebanking.services.LoginService;

@Controller
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoginService loginService;

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        return "login";
    }

    @PostMapping("/login")
    public String login(String username, String password, Model model) {
        boolean success = loginService.login(username, password);
        if (success) {
            return "redirect:/accounts";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }
}
