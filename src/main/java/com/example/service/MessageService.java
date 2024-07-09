package com.example.service;

import com.example.entity.*;
import com.example.repository.MessageRepository;
import com.example.repository.AccountRepository;

import javax.transaction.Transactional;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class MessageService {
    MessageRepository messageRepository;
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }

    public List<Message> getAllMessages(){
        return messageRepository.findAll();
    }

    public Message getMessageById(int messageId){
        Message message = messageRepository.findMessageByMessageId(messageId);
        return message;

    }

    public Message createMessage(Message message){
        if(message.getMessageText().length() < 1 || message.getMessageText().length() > 255 || accountRepository.findAccountByAccountId(message.getPostedBy()) == null)
            return null;
        Message newMessage = new Message(message.getPostedBy(), message.getMessageText(), message.getTimePostedEpoch());
        messageRepository.save(newMessage);
        return newMessage;
    }

    public int deleteMessage(int messageId){
        return messageRepository.deleteByMessageId(messageId);  
    }

    public int updateMessage(int messageId, String text){
        Message message = getMessageById(messageId);
        message.setMessageText(text);
        deleteMessage(messageId);
        if(createMessage(message) == null)
            return 0;
        return 1;
    }

    public List<Message> allMessagesByAccount(int accountId){
        return messageRepository.findMessagesByPostedBy(accountId);
    }
}
