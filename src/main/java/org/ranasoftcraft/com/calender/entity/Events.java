package org.ranasoftcraft.com.calender.entity;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "events")
@Data
public class Events implements Serializable {

    @Id
    private UUID eventId;

    private Long occurAt;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private Mode mode;

    @NonNull
    private Long calendarTimestamp;

    private String colorCode;



    enum Mode {
        PRIVATE,
        PUBLIC
    }

    public Events() {
        this.calendarTimestamp = Instant.now().toEpochMilli();
        this.eventId = UUID.randomUUID();
        this.occurAt = Instant.now().toEpochMilli();
    }

}
