package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final AccountRepository accountRepository;

        @Autowired
        public MessageService(MessageRepository messageRepository, AccountRepository accountRepository){
            this.messageRepository = messageRepository;
            this.accountRepository = accountRepository;
        }

    public Message createMessage(Message message) {
        if(message.getMessageText().length() != 0 && message.getMessageText().length() <= 255 && accountRepository.findById(message.getPostedBy()).isPresent()){
            return messageRepository.save(message);
        } else return null;
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Message getMessageById(int messageId) {
        if(messageRepository.findById(messageId).isPresent()){
            return messageRepository.findById(messageId).get();
        } else return null; 
    }

    public int deleteMessageById(int messageId) {
        if(messageRepository.findById(messageId).isPresent()){
            messageRepository.deleteById(messageId);
            return 1;
        } else return -1; 
    }

    public int updateMessageById(int messageId, Message newMessage) {
        if(!newMessage.getMessageText().isEmpty() && newMessage.getMessageText().length() <= 255 && messageRepository.findById(messageId).isPresent()){
            Message updatedMessage = messageRepository.findById(messageId).get();
            updatedMessage.setMessageText(newMessage.getMessageText());
            messageRepository.save(updatedMessage);          
            return 1;
        } else return -1;
    }

    public List<Message> getAllMessagesByAccountId(int accountId) {
        List<Integer> id = List.of(Integer.valueOf(accountId));
        return messageRepository.findAllById(id);
    }
}
