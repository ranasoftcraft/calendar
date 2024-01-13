package org.ranasoftcraft.com.calender.controller;

import org.ranasoftcraft.com.calender.entity.Events;
import org.ranasoftcraft.com.calender.services.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/event")
public class AdminController {


    private CalendarService calendarService;


    @Autowired
    public void setCalendarService(CalendarService calendarService) {
        this.calendarService = calendarService;
    }


    @PostMapping("/create")
    public ResponseEntity<?> createEvent(@RequestBody Events events) {
        return ResponseEntity.status(HttpStatus.CREATED).body(calendarService.save(events));
    }

}

