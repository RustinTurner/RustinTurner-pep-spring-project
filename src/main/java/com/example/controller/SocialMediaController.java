package com.example.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.entity.Message;
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

    @PostMapping("messages")
    public ResponseEntity<Message> createMessage(@RequestBody Message message){
        Message createdMessage = messageService.createMessage(message);
        return ResponseEntity.ok().body(createdMessage);
    }

    @DeleteMapping("messages/{id}")
    public ResponseEntity<Integer> deleteMessageById(@PathVariable int id){
        int rows = messageService.deleteMessageById(id);
        return ResponseEntity.ok().body(rows);
    }

    @GetMapping("accounts/{id}/messages")
    public ResponseEntity<List<Message>> getAllMessagesFromUser(@PathVariable int id){
        List<Message> allMessages = messageService.getAllMessagesFromUser(id);
        return ResponseEntity.ok().body(allMessages);
    }

    @GetMapping("messages")
    public ResponseEntity<List<Message>> getAllMessages(){
        List<Message> allMessages = messageService.getAllMessages();
        return ResponseEntity.ok().body(allMessages);
    }

    @PatchMapping("messages/{id}")
    public ResponseEntity<Integer> updateMessage(@PathVariable int id){
        int rows = messageService.updateMessage(id);
        return ResponseEntity.ok().body(rows);
    }

    @PostMapping("login")
    public ResponseEntity<Account> login(@RequestBody Account account){
        Account authAccount = accountService.login(account);
        return ResponseEntity.ok().body(authAccount);
    }

    @PostMapping("register")
    public ResponseEntity<Account> register(@RequestBody Account account){
        Account newAccount = accountService.register(account);
        return ResponseEntity.ok().body(newAccount);
    }

}
