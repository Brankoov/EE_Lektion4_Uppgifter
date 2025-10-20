package se.brankoov.webflux_postgresql.message;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/messages")
public class MessageController {

    private final MessageService service;

    public MessageController(MessageService service) {
        this.service = service;
    }

    // POST /api/v1/messages
    @PostMapping
    public Mono<ResponseEntity<Message>> createMessage(@RequestBody Message incoming) {
        return service.createMessage(incoming)
                .map(saved -> ResponseEntity.status(HttpStatus.CREATED).body(saved));
    }
}
