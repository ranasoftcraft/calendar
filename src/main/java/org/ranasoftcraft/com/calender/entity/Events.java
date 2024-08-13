package org.ranasoftcraft.com.calender.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "events")
@Data
public class Events implements Serializable {

    @Id
    private String eventId;

    private Long occurAt;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private Mode mode;

    @NonNull
    private Long calendarTimestamp;

    private String colorCode;



    public enum Mode {
        PRIVATE,
        PUBLIC
    }

    public Events() {
        this.calendarTimestamp = Instant.now().toEpochMilli();
        this.eventId = UUID.randomUUID().toString();
        this.occurAt = Instant.now().toEpochMilli();
    }


    public Events setEventId(String eventId) {
        this.eventId = eventId;
        return this;
    }

    public Events setOccurAt(Long occurAt) {
        this.occurAt = occurAt;
        return this;
    }

    public Events setTitle(String title) {
        this.title = title;
        return this;
    }

    public Events setDescription(String description) {
        this.description = description;
        return this;
    }

    public Events setMode(Mode mode) {
        this.mode = mode;
        return this;
    }

    public Events setCalendarTimestamp(@NonNull Long calendarTimestamp) {
        this.calendarTimestamp = calendarTimestamp;
        return this;
    }

    public Events setColorCode(String colorCode) {
        this.colorCode = colorCode;
        return this;
    }
}
