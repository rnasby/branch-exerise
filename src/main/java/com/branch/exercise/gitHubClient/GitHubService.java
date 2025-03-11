package com.branch.exercise.gitHubClient;

import com.branch.exercise.error.MalformedUrlException;
import com.branch.exercise.error.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
public class GitHubService implements GitHubAPI {
    static private final Logger LOGGER = LoggerFactory.getLogger(GitHubService.class);

    private final GitHubAPI gitHubAPI;

    @Autowired
    public GitHubService(GitHubAPI gitHubAPI) {
        this.gitHubAPI = gitHubAPI;
    }

    @Override
    public GitHubUser getUser(String username) {
        GitHubUser user = null;

        try {
            user = this.gitHubAPI.getUser(username);
        } catch (HttpClientErrorException e) {
            throw new MalformedUrlException("Incorrect URL:" + e.getMessage());
        }

        if (user == null) {
            throw new UserNotFoundException("User not found, " + username);
        }

        return user;
    }

    @Override
    public List<GitHubRepo> getUserRepos(String username) {
        try {
            return this.gitHubAPI.getUserRepos(username);
        } catch (HttpClientErrorException e) {
            LOGGER.warn("Incorrect URL:{}", e.getMessage());
            throw new MalformedUrlException("Incorrect URL:" + e.getMessage());
        }
    }
}