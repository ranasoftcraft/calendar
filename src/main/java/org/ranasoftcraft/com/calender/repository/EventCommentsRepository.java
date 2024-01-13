package org.ranasoftcraft.com.calender.repository;

import org.ranasoftcraft.com.calender.entity.EventComments;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EventCommentsRepository extends PagingAndSortingRepository<EventComments, UUID> {

}
