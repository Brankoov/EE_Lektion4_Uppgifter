package se.brankoov.webflux_postgresql.message;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
@Table("messages")
public record Message(
        @Id Long id,
        @Column("message") String message,
        @Column("created_at") LocalDateTime createdAt,
        boolean pinned
) {
}
