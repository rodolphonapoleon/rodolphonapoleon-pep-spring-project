package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;


/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */

 @Controller
 @ResponseBody
 public class SocialMediaController {

    private final AccountService accountService;
    private final MessageService messageService;

    @Autowired
    public SocialMediaController(AccountService accountService, MessageService messageService ) {
        this.accountService = accountService;
        this.messageService = messageService;
    }


    @PostMapping("/register")
    public ResponseEntity<Account> registerAccount(@RequestBody Account account) {
        Account createdAccount = accountService.registerAccount(account);
        if(createdAccount != null){
            return ResponseEntity.status(200).body(createdAccount);
        }
        return ResponseEntity.status(409).build();    
    }

    @PostMapping("/login")
    public ResponseEntity<Account> verifyAccount(@RequestBody Account account) {
        Account verifiedAccount = accountService.verifyAccount(account);
        if(verifiedAccount != null){
            return ResponseEntity.status(200).body(verifiedAccount);
        }
        return ResponseEntity.status(401).build();    
    }

    @PostMapping("/messages")
    public ResponseEntity<Message> createMessage (@RequestBody Message message) {
        Message createdMessage = messageService.createMessage(message);
        if(createdMessage != null){
            return ResponseEntity.status(200).body(createdMessage);
        }
        return ResponseEntity.status(400).build();    
    }

    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getAllMessages () {
        List<Message> messages = messageService.getAllMessages();
        return ResponseEntity.status(200).body(messages);    
    }

    @GetMapping("/messages/{messageId}")
    public ResponseEntity<Message> getMessageById (@PathVariable int messageId) {
        Message messageById = messageService.getMessageById(messageId);
        if(messageById != null){
            return ResponseEntity.status(200).body(messageById);
        }
        return ResponseEntity.status(200).build();    
    }

    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity<Integer> deleteMessageById (@PathVariable int messageId) {
        int numRow = messageService.deleteMessageById(messageId);
        if(numRow == 1){
            return ResponseEntity.status(200).body(numRow);
        }
        return ResponseEntity.status(200).build();    
    }


    @PatchMapping("/messages/{messageId}")
    public ResponseEntity<Integer> updateMessageById (@PathVariable int messageId, @RequestBody Message newMessage) {
        int numRow = messageService.updateMessageById(messageId, newMessage);
        if(numRow == 1){
            return ResponseEntity.status(200).body(numRow);
        }
        return ResponseEntity.status(400).build();    
    }

    @GetMapping("/accounts/{accountId}/messages")
    public ResponseEntity<List<Message>> getAllMessagesByAccountId (@PathVariable int accountId) {
        List<Message> messages = messageService.getAllMessagesByAccountId(accountId);
        return ResponseEntity.status(200).body(messages);    
    }

}
 