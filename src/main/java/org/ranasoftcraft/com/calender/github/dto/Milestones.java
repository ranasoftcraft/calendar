package org.ranasoftcraft.com.calender.github.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Data
public class Milestones implements Serializable {

    private String url;

    private String labels_url;

    private long id;

    private String title;

    private String description;

    private long open_issues;

    private  long closed_issues;

    private String state;

    private Long created_at;

    private Long updated_at;

    private Long due_on;

    private Long closed_at;

    private String node_id;

    public void setCreated_at(String created_at) {
        if(created_at !=null) {
            ZonedDateTime dateTime = ZonedDateTime.parse(created_at);
            this.created_at = dateTime.withZoneSameInstant(ZoneId.of("UTC"))
                    .toInstant().toEpochMilli();
        }
    }

    public void setUpdated_at(String updated_at) {
        if(updated_at!=null) {
            ZonedDateTime dateTime = ZonedDateTime.parse(updated_at);
            this.updated_at = dateTime.withZoneSameInstant(ZoneId.of("UTC"))
                    .toInstant().toEpochMilli();
        }
    }

    public void setDue_on(String due_on) {
        if(due_on !=null) {
            ZonedDateTime dateTime = ZonedDateTime.parse(due_on);
            this.due_on = dateTime.withZoneSameInstant(ZoneId.of("UTC"))
                    .toInstant().toEpochMilli();
        }
    }

    public void setClosed_at(String closed_at) {
        if(closed_at !=null) {
            ZonedDateTime dateTime = ZonedDateTime.parse(closed_at);
            this.closed_at = dateTime.withZoneSameInstant(ZoneId.of("UTC"))
                    .toInstant().toEpochMilli();
        }
    }
}
