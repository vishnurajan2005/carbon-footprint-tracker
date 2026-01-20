package com.infosys.carbon.controller;

import com.infosys.carbon.entity.Transaction;
import com.infosys.carbon.entity.User;
import com.infosys.carbon.entity.MarketplaceItem;
import com.infosys.carbon.repository.TransactionRepository;
import com.infosys.carbon.repository.UserRepository;
import com.infosys.carbon.repository.MarketplaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
@CrossOrigin("*")
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MarketplaceRepository marketplaceRepository;

    @PostMapping
    public Transaction createTransaction(
            @RequestParam Long userId,
            @RequestParam Long itemId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        MarketplaceItem item = marketplaceRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        Transaction tx = new Transaction();
        tx.setUser(user);
        tx.setItem(item);
        tx.setAmount(item.getPrice());

        return transactionRepository.save(tx);
    }
}

