package org.citadel.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.citadel.entities.enums.EventStatus;

import java.time.LocalDateTime;

@Data
@Entity
public class Event {

    @Id
    @Column(unique = true, name = "ID")
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column(name = "NameOfEvent", length = 100)
    private String nameOfEvent;

    @Column(length = 255)
    private String description;

    @Column
    private LocalDateTime eventStartDate;

    @Column
    private LocalDateTime eventClosingDate;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    private EventStatus eventStatus = EventStatus.GENERATED;
}
