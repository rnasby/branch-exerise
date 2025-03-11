package com.branch.exercise.user.gitHubClient;

import com.branch.exercise.error.MalformedUrlException;
import com.branch.exercise.gitHubClient.*;
import com.branch.exercise.gitHubClient.GitHubService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class ExceptionHandlingTest {

    @Mock
    private GitHubAPI gitHubAPI;

    @InjectMocks
    private GitHubService gitHubService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetUser_BadUrl() {
        String username = "someuser";
        when(gitHubAPI.getUser(username)).thenThrow(HttpClientErrorException.class);

        assertThrows(MalformedUrlException.class, () -> gitHubService.getUser(username));
    }

    @Test
    public void testGetUserRepos_BadUrl() {
        String username = "someuser";
        when(gitHubAPI.getUserRepos(username)).thenThrow(RestClientException.class);

        assertThrows(RestClientException.class, () -> gitHubService.getUserRepos(username));
    }
}