package org.ranasoftcraft.com.calender.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "event_comments")
@Data
public class EventComments implements Serializable {

    @Id
    private UUID id;

    private UUID eventId;

    private String comments;

    private Long occurAt;

    private boolean isDone;

}
