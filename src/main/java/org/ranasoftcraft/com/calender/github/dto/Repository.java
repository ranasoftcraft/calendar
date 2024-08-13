package org.ranasoftcraft.com.calender.github.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Data
public class Repository implements Serializable {

    private long id;

    private String node_id;

    private String name;

    private String full_name;

    private String description;

    private Long updated_at;

    public void setUpdated_at(String updated_at) {
        if(updated_at != null) {
            ZonedDateTime dateTime = ZonedDateTime.parse(updated_at);
            this.updated_at = dateTime.withZoneSameInstant(ZoneId.of("UTC"))
                    .toInstant().toEpochMilli();
        }
    }
}
