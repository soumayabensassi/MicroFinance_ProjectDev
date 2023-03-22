package com.example.microgrowth.Service.Classe;

import com.example.microgrowth.DAO.Entities.Message;
import com.example.microgrowth.DAO.Entities.User;
import com.example.microgrowth.DAO.Repositories.MessageRepository;
import com.example.microgrowth.DAO.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;
    private UserRepository userRepository;
    public List<Message> findBySenderAndRecipient(User sender, User recipient) {
        return messageRepository.findBySenderAndRecipient(sender, recipient);
    }

    public List<Message> findByRecipient(User recipient) {
        return messageRepository.findByRecipient(recipient);
    }

    public Message save(Message message) {
        return messageRepository.save(message);
    }
    public void sendMessage(int senderUsername, int receiverUsername, String content) {
        User sender = userRepository.findById(senderUsername).get();
        User receiver = userRepository.findById(receiverUsername).get();
        Message message = new Message();
        message.setSender(sender);
        message.setRecipient(receiver);
        message.setContent(content);
        messageRepository.save(message);
    }
}
