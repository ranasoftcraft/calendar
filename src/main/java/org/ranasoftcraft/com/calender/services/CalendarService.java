package org.ranasoftcraft.com.calender.services;

import org.ranasoftcraft.com.calender.entity.EventComments;
import org.ranasoftcraft.com.calender.entity.Events;
import org.ranasoftcraft.com.calender.repository.EventCommentsRepository;
import org.ranasoftcraft.com.calender.repository.EventsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service @Transactional(timeout = 60)
public class CalendarService {

    private EventsRepository eventsRepository;

    private EventCommentsRepository eventCommentsRepository;

    @Autowired
    public void setEventsRepository(EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    @Autowired
    public void setEventCommentsRepository(EventCommentsRepository eventCommentsRepository) {
        this.eventCommentsRepository = eventCommentsRepository;
    }

    public boolean save(Events events) {
        eventsRepository.save(events);
        return Boolean.TRUE;
    }

    public Page<Events> getEvents(final Long startDate , final Long endDate) {
       return eventsRepository.findAll(PageRequest.of(0,100));
    }

    public boolean saveEventComments(EventComments eventComments) {
        eventCommentsRepository.save(eventComments);
        return Boolean.TRUE;
    }

    public Page<EventComments> getIssues(String milestoneId) {
        return eventCommentsRepository.findByEventId(milestoneId, PageRequest.of(0,Integer.MAX_VALUE));
    }


}
