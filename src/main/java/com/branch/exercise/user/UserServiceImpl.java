package com.branch.exercise.user;

import com.branch.exercise.gitHubClient.GitHubService;
import com.branch.exercise.gitHubClient.GitHubRepo;
import com.branch.exercise.gitHubClient.GitHubUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
class UserServiceImpl implements UserService {
    static private final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final GitHubService gitHubService;

    UserServiceImpl(GitHubService gitHubService) {
        this.gitHubService = gitHubService;
    }

    public User getResponse(String username) {
        return new User(getGitHubUser(username), getGitHubUserRepos(username));
    }

    private GitHubUser getGitHubUser(String username) {
        LOGGER.trace("Get GitHub user: {}", username);

        try {
            return gitHubService.getUser(username);
        } catch (Throwable t) {
            LOGGER.error("Failed to get GitHub user", t);
            throw t;
        }
    }

    private List<GitHubRepo> getGitHubUserRepos(String username) {
        LOGGER.trace("Get GitHub user repos: {}", username);

        try {
            return gitHubService.getUserRepos(username);
        } catch (Throwable t) {
            LOGGER.error("Failed to get GitHub user", t);
            throw t;
        }
    }
}