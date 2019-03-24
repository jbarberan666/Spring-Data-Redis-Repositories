package fr.pe.polygone.dbreaderservice.controller;

import fr.pe.polygone.dbreaderservice.entity.Message;
import fr.pe.polygone.dbreaderservice.exception.EntityNotFoundException;
import fr.pe.polygone.dbreaderservice.repository.MessageRepository;
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
    private MessageRepository messageRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Message create (@RequestBody Message message){
        logger.info("Message {} created",message);
        return messageRepository.save(message);
    }

    @GetMapping("/{id}")
    public Message findById(@PathVariable String id) throws EntityNotFoundException {
        return messageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id+" non trouvé"));
    }

    @GetMapping
    public Iterable<Message> listAll() {
        return messageRepository.findAll();
    }

}
