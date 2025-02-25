package com.branch.exercise.gitHubClient;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.util.List;

@Service
public class GitHubService implements GitHubAPI {
    static private final String BASE_URL = "https://api.github.com/";

    private final GitHubAPI gitHubAPI;

    GitHubService() {
        var restClient = RestClient.builder().baseUrl(BASE_URL).build();
        var factory = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient)).build();

        gitHubAPI = factory.createClient(GitHubAPI.class);
    }

    @Override
    public GitHubUser getUser(String username) {
        return gitHubAPI.getUser(username);
    }

    @Override
    public List<GitHubRepo> getUserRepos(String username) {
        return gitHubAPI.getUserRepos(username);
    }
}