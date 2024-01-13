package org.ranasoftcraft.com.calender.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
