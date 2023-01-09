// This AccountService class handles the creation and retrieval of accounts in the online banking system. It has methods for retrieving a specific account, retrieving all accounts for a specific user, and creating a new account.
// The class uses the SLF4J logging framework to log events related to the account management process, such as creating a new account or retrieving an account. The logger static field is initialized with a logger for the AccountService class, and the logger is used to log relevant events.
// The class has an @Autowired field for the AccountRepository, which is used to retrieve and save account records to the database. The getAccount method uses the findById method of the AccountRepository to retrieve the account with the given ID. The getAccounts method uses the findAllByUserId method of the AccountRepository to retrieve all accounts for the given user ID. The createAccount method uses the save method of the AccountRepository to create a new account in the database.


package com.mycompany.onlinebanking.services;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.onlinebanking.models.Account;
import com.mycompany.onlinebanking.repositories.AccountRepository;

@Service
public class AccountService {
    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    private AccountRepository accountRepository;

    public Account getAccount(long id) {
        logger.info("Retrieving account with ID {}", id);
        return accountRepository.findById(id).orElse(null);
    }

    public List<Account> getAccounts(long userId) {
        logger.info("Retrieving all accounts for user with ID {}", userId);
        return accountRepository.findAllByUserId(userId);
    }

    public Account createAccount(long userId, String type) {
        logger.info("Creating {} account for user with ID {}", type, userId);
        Account account = new Account();
        account.setUserId(userId);
        account.setType(type);
        account.setBalance(BigDecimal.ZERO);
        accountRepository.save(account);
        logger.info("Account created successfully");
        return account;
    }
}
