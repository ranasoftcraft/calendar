package org.ranasoftcraft.com.calender.github.config;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ranasoftcraft.com.calender.entity.Events;
import org.ranasoftcraft.com.calender.github.dto.Milestones;
import org.ranasoftcraft.com.calender.github.dto.Repository;
import org.ranasoftcraft.com.calender.github.reader.MilestonesService;
import org.ranasoftcraft.com.calender.github.reader.RepositoryService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Collections;
import java.util.List;

/**
 * @author sandeep.rana
 */
@Slf4j
@Component @Data @RequiredArgsConstructor
public class ScheduledTask {


    private final JmsTemplate jmsTemplate;

    private final RepositoryService repositoryService;

    private final MilestonesService milestonesService;

    @Value("${git.owner}")
    private String owner;


    @Scheduled(cron = "0 0 0 * * *")
//    @Scheduled(cron = "0 * * * * *") // every 1 minutes
    public void pullReleases() {
        List<Repository> repositories = Collections.emptyList();
        try { repositories =  repositoryService.getRepository(owner);} catch (Exception e) {
            log.warn("Trying to get repositories ... {}", e.getLocalizedMessage());
        }
        for(Repository repository: repositories) {
            try {
                List<Milestones> milestones = milestonesService.getMilestones(repository.getName());
                for(Milestones m: milestones) {
                    jmsTemplate.convertAndSend("sync-git-milestones", new Events()
                            .setTitle(m.getTitle())
                            .setDescription(m.getDescription())
                            .setMode(Events.Mode.PUBLIC)
                            .setCalendarTimestamp(m.getDue_on())
                            .setColorCode(Instant.now().toEpochMilli() < m.getDue_on() ? "#20aeac" : "#00ff00")
                            .setEventId(m.getNode_id())
                            .setOccurAt(Instant.now().toEpochMilli()));
                    log.info("Synced this {} repo", repository.getName());
                }
            } catch (Exception e) {
                log.warn("This {} repo doesn't have the milestone", repository.getName());
            }

        }
    }
}
