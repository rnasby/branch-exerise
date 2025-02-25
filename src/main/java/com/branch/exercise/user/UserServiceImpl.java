package com.branch.exercise.user;

import com.branch.exercise.gitHubClient.GitHubService;
import com.branch.exercise.gitHubClient.GitHubRepo;
import com.branch.exercise.gitHubClient.GitHubUser;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
class UserServiceImpl implements UserService {
    private final GitHubService gitHubService;

    UserServiceImpl(GitHubService gitHubService) {
        this.gitHubService = gitHubService;
    }

    public User getResponse(String username) {
        return new User(getGitHubUser(username), getGitHubUserRepos(username));
    }

    private GitHubUser getGitHubUser(String username) {
        try {
            return gitHubService.getUser(username);
        } catch (Throwable t) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to get GitHub user");
        }
    }

    private List<GitHubRepo> getGitHubUserRepos(String username) {
        try {
            return gitHubService.getUserRepos(username);
        } catch (Throwable t) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to get GitHub user repos");
        }
    }
}