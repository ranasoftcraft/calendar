package org.ranasoftcraft.com.calender.jms.listener;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.ranasoftcraft.com.calender.entity.Events;
import org.ranasoftcraft.com.calender.services.CalendarService;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component @Data
public class GitSyncEventListener {

    private final CalendarService calendarService;

    @JmsListener(destination = "sync-git-milestones", containerFactory = "eventFactory")
    public void listenEvents(Events events) {
        calendarService.save(events);
    }

}
