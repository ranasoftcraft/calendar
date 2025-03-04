package org.ranasoftcraft.com.calender.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "event_comments")
@Data
public class EventComments implements Serializable {

    @Id
    private String id;

    private String eventId;

    private String title;

    private String comments;

    private Long occurAt;

    private boolean isDone;

    public EventComments() {
        this.id = UUID.randomUUID().toString();
        this.occurAt = Instant.now().toEpochMilli();
    }

    public EventComments setId(String id) {
        this.id = id;
        return this;
    }

    public EventComments setEventId(String eventId) {
        this.eventId = eventId;
        return this;
    }

    public EventComments setTitle(String title) {
        this.title = title;
        return this;
    }

    public EventComments setComments(String comments) {
        this.comments = comments;
        return this;
    }

    public EventComments setOccurAt(Long occurAt) {
        this.occurAt = occurAt;
        return this;
    }

    public EventComments setDone(boolean done) {
        isDone = done;
        return this;
    }
}
