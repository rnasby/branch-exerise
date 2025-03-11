package com.branch.exercise.gitHubClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class GitHubConfig {
    @Value("${exercise.github.api.baseurl}")
    private String baseUrl;

    @Bean
    public GitHubAPI gitHubAPI() {
        var restClient = RestClient.builder().baseUrl(baseUrl).build();
        var factory = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient)).build();

        return factory.createClient(GitHubAPI.class);
    }
}