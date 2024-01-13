package org.ranasoftcraft.com.calender.controller;

import org.ranasoftcraft.com.calender.dto.CalendarEvents;
import org.ranasoftcraft.com.calender.entity.Events;
import org.ranasoftcraft.com.calender.services.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

@Controller
public class CalendarRest {

    private CalendarService calendarService;

    @Autowired
    public void setCalendarService(CalendarService calendarService) {
        this.calendarService = calendarService;
    }


    @GetMapping("/calendar")
    public String calendar() {
        return "calendar.html";
    }


    @GetMapping("/user/release")
    public ResponseEntity<?> release() {
        Page<Events> events = calendarService.getEvents(0L,0L);
        return ResponseEntity.ok(events.get().map(m-> {
            return CalendarEvents.builder()
                    .url("null")
                    .title(m.getTitle())
                    .start(new Date(m.getCalendarTimestamp()))
                    .allDay(true)
                    .backgroundColor(m.getColorCode())
                    .build();
        }).collect(Collectors.toList()));
    }
}
