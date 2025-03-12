package com.example.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.exception.InvalidCredentialsException;
import com.example.exception.InvalidMessageException;
import com.example.exception.MessageNotFoundException;
import com.example.exception.UserAlreadyExistsException;
import com.example.service.AccountService;
import com.example.service.MessageService;

/**
 * You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {
    private AccountService accountService;
    private MessageService messageService;

    public SocialMediaController(AccountService accountService, MessageService messageService){
        this.accountService = accountService;
        this.messageService = messageService;
    }

    @PostMapping("messages")
    public ResponseEntity<Message> createMessage(@RequestBody Message message) throws InvalidMessageException{
        Message createdMessage = messageService.createMessage(message);
        return ResponseEntity.ok().body(createdMessage);
    }

    @DeleteMapping("messages/{id}")
    public ResponseEntity<Integer> deleteMessageById(@PathVariable int id) throws MessageNotFoundException{
        int rows = messageService.deleteMessageById(id);
        return ResponseEntity.ok().body(rows);
    }

    @GetMapping("accounts/{id}/messages")
    public ResponseEntity<List<Message>> getAllMessagesFromUser(@PathVariable int id) throws MessageNotFoundException{
        List<Message> allMessages = messageService.getAllMessagesFromUser(id);
        return ResponseEntity.ok().body(allMessages);
    }

    @GetMapping("messages")
    public ResponseEntity<List<Message>> getAllMessages(){
        List<Message> allMessages = messageService.getAllMessages();
        return ResponseEntity.ok().body(allMessages);
    }

    @GetMapping("messages/{id}")
    public ResponseEntity<Message> getMessageById(@PathVariable int id) throws MessageNotFoundException{
        Message message = messageService.getMessageById(id);
        return ResponseEntity.ok().body(message);
    }

    @PatchMapping("messages/{id}")
    public ResponseEntity<Integer> updateMessage(@PathVariable int id, @RequestBody Message message) throws InvalidMessageException{
        int rows = messageService.updateMessage(id, message);
        return ResponseEntity.ok().body(rows);
    }

    @PostMapping("login")
    public ResponseEntity<Account> login(@RequestBody Account account) throws InvalidCredentialsException{
        Account authAccount = accountService.login(account);
        return ResponseEntity.ok().body(authAccount);
    }

    @PostMapping("register")
    public ResponseEntity<Account> register(@RequestBody Account account) throws UserAlreadyExistsException{
        Account newAccount = accountService.register(account);
        return ResponseEntity.ok().body(newAccount);
    }

}
