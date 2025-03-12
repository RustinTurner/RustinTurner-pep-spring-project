package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.exception.InvalidMessageException;
import com.example.exception.MessageNotFoundException;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;

@Service
public class MessageService {
    private MessageRepository messageRepository;
    private AccountRepository accountRepository;

    public MessageService(MessageRepository messageRepository, AccountRepository accountRepository){
        this.messageRepository = messageRepository;
        this.accountRepository = accountRepository;
    }

    public Message createMessage(Message message) throws InvalidMessageException {
        if(!accountRepository.existsById(message.getPostedBy()) || message.getMessageText() == "" || message.getMessageText().length() > 254){
            throw new InvalidMessageException(null);
        }
        return messageRepository.save(message);
    }

    public int deleteMessageById(int id) throws MessageNotFoundException{
        if(!messageRepository.existsById(id)){
            throw new MessageNotFoundException(null);
        }
        messageRepository.deleteById(id);
        return 1;
    }

    public List<Message> getAllMessagesFromUser(int id) throws MessageNotFoundException{
        Optional<List<Message>> oMessages = messageRepository.findByPostedBy(id);
        if(oMessages.isPresent()){
            return oMessages.get();             
        }
        throw new MessageNotFoundException(null);
    }

    public List<Message> getAllMessages(){
        return (List<Message>) messageRepository.findAll();
    }

    public Message getMessageById(int id) throws MessageNotFoundException{
        Optional<Message> oMessage = messageRepository.findById(id);
        if(oMessage.isPresent()){
            return oMessage.get();
        } 
        throw new MessageNotFoundException(null);
    }

    public int updateMessage(int id, Message newMessage) throws InvalidMessageException{
        if(!messageRepository.existsById(id) || newMessage.getMessageText() == "" || newMessage.getMessageText().length() > 254){
            throw new InvalidMessageException(null);
        }
        Optional<Message> oMessage = messageRepository.findById(id);
        Message message = oMessage.get();
        message.setMessageText(newMessage.getMessageText());
        messageRepository.save(message);
        return 1;
    }
}
