// This AccountController class handles requests related to the user's accounts in the online banking system. It has a showAccounts method that retrieves the user's accounts from the AccountService and displays them in the accounts template. It also has a viewAccount method that retrieves a specific account by ID and displays it in the view-account template.


package com.mycompany.onlinebanking.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.mycompany.onlinebanking.models.Account;
import com.mycompany.onlinebanking.services.AccountService;

@Controller
public class AccountController {
    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AccountService accountService;

    @GetMapping("/accounts")
    public String showAccounts(Model model) {
        logger.info("Showing accounts page");
        model.addAttribute("accounts", accountService.getAccounts());
        return "accounts";
    }

    @GetMapping("/accounts/view")
    public String viewAccount(long id, Model model) {
        logger.info("Viewing account with ID {}", id);
        Account account = accountService.getAccount(id);
        if (account != null) {
            model.addAttribute("account", account);
            return "view-account";
        } else {
            model.addAttribute("error", "Account not found");
            return "error";
        }
    }
}
