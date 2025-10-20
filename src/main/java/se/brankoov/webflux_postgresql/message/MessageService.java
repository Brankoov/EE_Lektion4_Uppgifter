package se.brankoov.webflux_postgresql.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class MessageService {

    private static final Logger log = LoggerFactory.getLogger(MessageService.class);
    private final MessageRepository repository;

    public MessageService(MessageRepository repository) {
        this.repository = repository;
    }

    // Skapa meddelande: ID sätts av DB, createdAt sätts här på serversidan
    public Mono<Message> createMessage(Message incoming) {
        Message entity = new Message(
                null,                   // id sätts av DB (SERIAL/IDENTITY)
                incoming.message(),     // text från klienten
                LocalDateTime.now() ,
                incoming.pinned()
        );

        return repository.save(entity)
                .doOnSuccess(saved -> log.info("Saved message id={} pinned={}", saved.id(), saved.pinned()))
                .doOnError(err -> log.error("Failed to save message: {}", err.getMessage()));
    }

    public Mono<Message> getById(Long id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new MessageNotFoundException(id)));
    }
}
