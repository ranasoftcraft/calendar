package org.ranasoftcraft.com.calender.github.reader;

import org.ranasoftcraft.com.calender.github.dto.Milestones;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;

public interface MilestonesService {

    @GetExchange("/{repo}/milestones")
    List<Milestones> getMilestones(@PathVariable String repo);
}
