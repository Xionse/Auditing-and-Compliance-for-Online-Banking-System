// TransactionController class handles requests related to the user's transactions in the online banking system. It has a showTransactions method that retrieves the user's transactions from the TransactionService and displays them in the transactions template. It also has a showAddTransactionForm method that displays the form for adding a new transaction and a addTransaction method that adds a new transaction to the database.

package com.mycompany.onlinebanking.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.mycompany.onlinebanking.models.Transaction;
import com.mycompany.onlinebanking.services.TransactionService;

@Controller
public class TransactionController {
    private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/transactions")
    public String showTransactions(Model model) {
        logger.info("Showing transactions page");
        model.addAttribute("transactions", transactionService.getTransactions());
        return "transactions";
    }

    @GetMapping("/transactions/add")
    public String showAddTransactionForm(Model model) {
        logger.info("Showing add transaction form");
        model.addAttribute("transaction", new Transaction());
        return "add-transaction";
    }

    @PostMapping("/transactions/add")
    public String addTransaction(Transaction transaction, Model model) {
        logger.info("Adding transaction: {}", transaction);
        boolean success = transactionService.addTransaction(transaction);
        if (success) {
            return "redirect:/transactions";
        } else {
            model.addAttribute("error", "Error adding transaction");
            return "add-transaction";
        }
    }
}

