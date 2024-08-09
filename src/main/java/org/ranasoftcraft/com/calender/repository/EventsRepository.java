package org.ranasoftcraft.com.calender.repository;

import org.ranasoftcraft.com.calender.entity.Events;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EventsRepository extends JpaRepository<Events, UUID> {
}
