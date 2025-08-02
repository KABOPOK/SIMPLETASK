package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Instant;
import java.util.UUID;
import java.time.LocalDateTime;

@Entity
@Table(name = "sos_forms")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SosForm {

    @Id
    @Column(name = "id",  nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "telegram_contact", nullable = false, length = 64)
    private String telegramContact;

    @Column(name = "deactivation_code", nullable = false, columnDefinition = "TEXT")
    private String deactivationCode;

    @Column(name = "address", nullable = false, columnDefinition = "TEXT")
    private String address;

    @Column(name = "date_time", nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Instant dateTime;

    @Column(name = "is_ready")
    private Boolean isReady = false;

    @Column(name = "comment", columnDefinition = "TEXT")
    private String comment;

    @Column(name = "last_activation")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Instant lastActivation;
}
