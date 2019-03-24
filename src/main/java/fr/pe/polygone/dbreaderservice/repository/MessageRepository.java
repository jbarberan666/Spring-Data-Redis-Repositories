package fr.pe.polygone.dbreaderservice.repository;

import fr.pe.polygone.dbreaderservice.entity.Message;
import org.springframework.data.repository.CrudRepository;

/*
 * Extending CrudRepository in MessageRepository,
 * we automatically get a complete set of persistence methods that perform CRUD functionality.
 */

public interface MessageRepository extends CrudRepository<Message, String> {}


