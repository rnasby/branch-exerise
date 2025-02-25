package com.branch.exercise.user;

import com.branch.exercise.gitHubClient.GitHubService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    GitHubService gitHubService;

    @InjectMocks
    UserServiceImpl userService;

    @Test
    public void getUserTest() {
        // Set up mock
        BDDMockito.given(gitHubService.getUser(TestData.GITHUB_USER.login)).willReturn(TestData.GITHUB_USER);
        BDDMockito.given(gitHubService.getUserRepos(TestData.GITHUB_USER.login)).willReturn(TestData.GITHUB_USER_REPOS);

        // Test
        var actualResponse = userService.getResponse(TestData.GITHUB_USER.login);
        Assertions.assertThat(actualResponse).usingRecursiveComparison().isEqualTo(TestData.USER);
    }
}