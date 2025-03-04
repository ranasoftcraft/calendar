package org.ranasoftcraft.com.calender.repository;

import org.ranasoftcraft.com.calender.entity.EventComments;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EventCommentsRepository extends JpaRepository<EventComments, UUID> {

    Page<EventComments> findByEventId(String eventId, PageRequest pageRequest);
}
