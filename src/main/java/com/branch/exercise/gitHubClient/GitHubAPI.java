package com.branch.exercise.gitHubClient;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;

public interface GitHubAPI {
    @GetExchange("/users/{username}")
    GitHubUser getUser(@PathVariable("username") String username);

    @GetExchange("/users/{username}/repos")
    List<GitHubRepo> getUserRepos(@PathVariable("username") String username);
}