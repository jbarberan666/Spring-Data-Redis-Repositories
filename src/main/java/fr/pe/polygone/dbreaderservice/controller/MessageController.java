package fr.pe.polygone.dbreaderservice.controller;

import fr.pe.polygone.dbreaderservice.entity.Message;
import fr.pe.polygone.dbreaderservice.exception.EntityNotFoundException;
import fr.pe.polygone.dbreaderservice.repository.MessageRepository;
import fr.pe.polygone.dbreaderservice.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private Logger logger = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    private MessageService messageService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Message createMessage (@RequestBody Message message){
       return messageService.saveMessage(message);
    }

    @GetMapping("/{id}")
    public Message findMessageById(@PathVariable String id) throws EntityNotFoundException {
       return messageService.findById(id);
    }

    @GetMapping
    public Iterable<Message> listAllMessage() {
        return messageService.findAll();
    }

}
