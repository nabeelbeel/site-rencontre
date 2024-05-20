package com.projet.siterencontre.services;

import com.projet.siterencontre.entities.Message;
import com.projet.siterencontre.repositories.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    MessageRepo repo;

    public boolean createMessage(Message message){
        Message savedMessage = repo.save(message);
        return repo.existsById(savedMessage.getId());
    }
    public void saveMessage(Message message) {
        message.setDate(LocalDateTime.now());
        repo.save(message);
    }

    public List<Message> showMessagesFromUser(String email){
        return repo.getMessagesFromUser(email);
    }

    public List<Message> showMessagesToUser(String email){
        return repo.getMessagesToUser(email);
    }

}
