package com.example.service;

import com.example.entity.*;
import com.example.repository.*;

import javax.transaction.Transactional;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class MessageService {
    MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }

    public List<Message> getAllMessages(){
        return messageRepository.findAll();
    }

    public Message getMessageById(int messageId){
        Optional<Message> optional = messageRepository.findById((long) messageId);
        if(optional.isEmpty())
            return null;
        else
            return optional.get();

    }

    public Message createMessage(Message message){
        return null;
    }

    public Message deleteMessage(int messageId){
        return null;
    }

    public Message updateMessage(int messageId){
        return null;
    }

    public List<Message> allMessagesByAccount(int accountId){
        return null;
    }
}
