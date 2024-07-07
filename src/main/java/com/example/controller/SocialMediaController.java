package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.example.entity.*;
import com.example.repository.*;
import com.example.service.*;
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
    public ResponseEntity register(@RequestBody String user){
        try{
            Account account = mapper.readValue(user, Account.class);
            Account result = accountService.createAccount(account);
            if(result == null)
                return ResponseEntity.status(400).body("Error Creating Account");
            if(result.getAccountId() == -1)
                return ResponseEntity.status(409).body("Account Already Exists");
            return ResponseEntity.status(200).body(mapper.writeValueAsString(result));
        } catch(Exception e) {
            return ResponseEntity.status(400).body("Error");
        }
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody String user){
        try{
            Account account = mapper.readValue(user, Account.class);
            Account result = accountService.validate(account);
            if(result == null)
                return ResponseEntity.status(400).body("Unauthorized");
            return ResponseEntity.status(200).body(mapper.writeValueAsString(result));
        } catch(Exception e){
            return ResponseEntity.status(400).body("Error");
        }
    }

    @PostMapping("/messages")
    public ResponseEntity createMessage(@RequestBody String post){
        try{
            Message message = mapper.readValue(post, Message.class);
            Message result = messageService.createMessage(message);
            if(result == null)
                return ResponseEntity.status(400).body("Error Creating Message");
            return ResponseEntity.status(200).body(mapper.writeValueAsString(result));
        } catch(Exception e){
            return ResponseEntity.status(400).body("Error");
        }
    }

    @GetMapping("/messages")
    public ResponseEntity allMessages(){
        try{
            return ResponseEntity.status(200).body(mapper.writeValueAsString(messageService.getAllMessages()));
        } catch(Exception e){
            return ResponseEntity.status(400).body("Error");
        }
    }

    @GetMapping("/messages/{messageId}")
    public ResponseEntity messageById(@PathVariable int messageId){
        try{
            Message message = messageService.getMessageById(messageId);
            if(message == null)
                return ResponseEntity.status(200).body("");
            return ResponseEntity.status(200).body(mapper.writeValueAsString(message));
        } catch(Exception e){
            return ResponseEntity.status(400).body("Error");
        }

    }

    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity deleteById(@PathVariable int messageId){
        try{
            Message message = messageService.deleteMessage(messageId);
            if(message == null)
                return ResponseEntity.status(200).body("");
            return ResponseEntity.status(200).body("1");
        } catch(Exception e){
            return ResponseEntity.status(400).body("Error");
        }
    }

    @PatchMapping("/messages/{messageId}")
    public ResponseEntity updateById(@PathVariable int messageId){
        try{
            Message message = messageService.updateMessage(messageId);
            if(message == null)
                return ResponseEntity.status(400).body("Error Updating Message");
            return ResponseEntity.status(200).body("1");
        } catch(Exception e){
            return ResponseEntity.status(400).body("Error");
        }
    }

    @GetMapping("/accounts/{accountId}/messages")
    public ResponseEntity messagesByAccount(@PathVariable int accountId){
        try{
            return ResponseEntity.status(200).body(mapper.writeValueAsString(messageService.allMessagesByAccount(accountId)));
        } catch(Exception e){
            return ResponseEntity.status(400).body("Error");
        }
    }

}
