package org.ranasoftcraft.com.calender.github.reader;

import org.ranasoftcraft.com.calender.github.dto.Issues;
import org.ranasoftcraft.com.calender.github.dto.Milestones;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;

public interface IssueService {

    @GetExchange("/{repo}/issues")
    List<Issues> getIssues(@PathVariable String repo, @RequestParam String state, @RequestParam int milestone);
}
