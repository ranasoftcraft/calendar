package org.ranasoftcraft.com.calender.github.reader;

import org.ranasoftcraft.com.calender.github.dto.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;

public interface RepositoryService {

    @GetExchange("/{owner}/repos")
    List<Repository> getRepository(@PathVariable String owner);
}
