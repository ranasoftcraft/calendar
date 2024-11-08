package org.ranasoftcraft.com.calender.controller;

import lombok.RequiredArgsConstructor;
import org.ranasoftcraft.com.calender.dto.CalendarEvents;
import org.ranasoftcraft.com.calender.entity.Events;
import org.ranasoftcraft.com.calender.github.reader.MilestonesService;
import org.ranasoftcraft.com.calender.services.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestClient;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

@Controller @RequiredArgsConstructor
public class CalendarRest {

    private final CalendarService calendarService;

    private final MilestonesService milestonesService;


    @GetMapping("")
    public String calendar() {
        return "calendar.html";
    }


    @GetMapping("/user/release")
    public ResponseEntity<?> release() throws URISyntaxException {
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
