package fr.pe.polygone.dbreaderservice.service;

import fr.pe.polygone.dbreaderservice.controller.MessageController;
import fr.pe.polygone.dbreaderservice.entity.Message;
import fr.pe.polygone.dbreaderservice.exception.EntityNotFoundException;
import fr.pe.polygone.dbreaderservice.repository.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private Logger logger = LoggerFactory.getLogger(MessageController.class);



    @Autowired
    private MessageRepository messageRepository;

    public Message saveMessage(Message message){
        logger.info("Message {} saved",message);
        return messageRepository.save(message);

    }

    public Message findById(String id){
        return messageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id+" non trouvé"));

    }

    public Iterable<Message> findAll(){
        return messageRepository.findAll();
    }
}
