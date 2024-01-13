package org.ranasoftcraft.com.calender.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data @Builder
public class CalendarEvents implements Serializable {

    private String title;

    private boolean allDay;

    private Date start;

    private String url;

    private String backgroundColor;


}
