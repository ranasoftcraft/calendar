package org.ranasoftcraft.com.calender.github.config;

import lombok.extern.slf4j.Slf4j;
import org.ranasoftcraft.com.calender.github.reader.IssueService;
import org.ranasoftcraft.com.calender.github.reader.MilestonesService;
import org.ranasoftcraft.com.calender.github.reader.RepositoryService;
import org.ranasoftcraft.com.calender.jms.config.JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.util.StreamUtils;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.io.IOException;

/**
 * @author sandeep.rana
 */
@Slf4j
@Configuration
public class GithubConfiguration {

    @Value("${git.url}")
    private String gitUrl;

    @Value("${git.username}")
    private String  gitUsername;

    @Value("${git.password}")
    private String gitPassword;

    @Value("${git.ignore-ssl}")
    private boolean ignoreSsl;

    @Value("${git.token}")
    private String token;

    @Value("${git.owner}")
    private String owner;


    @Bean
    public MilestonesService milestonesService() {

        final RestClient client = RestClient.builder()
                .baseUrl(String.format("%s/repos/%s", gitUrl, StringUtils.hasText(owner) ? owner : gitUsername))
                .defaultHeader("Authorization","Bearer "+ token)
//                .messageConverters(converterList -> converterList.addFirst(new JsonMessageConverter()))
                .build();
        HttpServiceProxyFactory factory = HttpServiceProxyFactory
                .builderFor(RestClientAdapter.create(client))
                .build();
        return factory.createClient(MilestonesService.class);
    }

    @Bean
    public IssueService issueService() {

        final RestClient client = RestClient.builder()
                .baseUrl(String.format("%s/repos/%s", gitUrl, StringUtils.hasText(owner) ? owner : gitUsername))
                .defaultHeader("Authorization","Bearer "+ token)
//                .messageConverters(converterList -> converterList.addFirst(new JsonMessageConverter()))
                .build();
        HttpServiceProxyFactory factory = HttpServiceProxyFactory
                .builderFor(RestClientAdapter.create(client))
                .build();
        return factory.createClient(IssueService.class);
    }

    @Bean
    public RepositoryService repositoryService() {

        RestClient client = RestClient.builder()
                .baseUrl(String.format("%s/orgs", gitUrl))
                .defaultHeader("Authorization","Bearer "+ token)
//                .defaultStatusHandler((statusCode) -> true, (request, response) -> {})
//                .messageConverters(converterList -> converterList.addFirst(new JsonMessageConverter()))
                .build();
        HttpServiceProxyFactory factory = HttpServiceProxyFactory
                .builderFor(RestClientAdapter.create(client))
                .build();
        return factory.createClient(RepositoryService.class);
    }

}
