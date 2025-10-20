package se.brankoov.webflux_postgresql.message;


public class MessageNotFoundException extends RuntimeException {
    public MessageNotFoundException(Long id) {
        super("Message with id %d not found".formatted(id));
    }
}
