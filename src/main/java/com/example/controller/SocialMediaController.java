package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.example.entity.Account;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;
import com.example.service.AccountService;
import com.example.service.MessageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {

    @Autowired
    private AccountRepository accountRepository;
    AccountService accountService = new AccountService(accountRepository);
    @Autowired
    private MessageRepository messageRepository;
    MessageService messageService = new MessageService(messageRepository);
    ObjectMapper mapper = new ObjectMapper();

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody String user) throws JsonMappingException, JsonProcessingException{
        try{
            Account account = mapper.readValue(user, Account.class);
            Account result = accountService.createAccount(account);
        } catch(Exception e) {}
        return null;
        
    }

    @PostMapping("/login")
    public ResponseEntity login(){
        return null;
    }

    @PostMapping("/messages")
    public ResponseEntity createMessage(){
        return null;
    }

    @GetMapping("/messages")
    public ResponseEntity allMessages(){
        return null;
    }

    @GetMapping("/messages/{messageId}")
    public ResponseEntity messageById(@PathVariable int messageId){
        return ResponseEntity.status(200).body("blah");
    }

    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity deleteById(@PathVariable int messageId){
        return null;
    }

    @PatchMapping("/messages/{messageId}")
    public ResponseEntity updateById(@PathVariable int messageId){
        return null;
    }

    @GetMapping("/accounts/{accountId}/messages")
    public ResponseEntity messagesByAccount(@PathVariable int accountId){
        return null;
    }

}
