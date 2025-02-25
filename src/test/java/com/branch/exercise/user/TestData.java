package com.branch.exercise.user;

import com.branch.exercise.gitHubClient.GitHubRepo;
import com.branch.exercise.gitHubClient.GitHubUser;

import java.util.Date;
import java.util.List;

public class TestData {
    static List<User.Repo> USER_REPOS = List.of(
            User.Repo.builder().name("testName1").url("testUrl1").build(),
            User.Repo.builder().name("testName2").url("testUrl2").build());

    static User USER = User.builder()
            .url("testUrl")
            .userName("testUserName")
            .email("testEmail")
            .avatar("testAvatar")
            .geoLocation("testGeoLocation")
            .displayName("testDisplayName")
            .createdAt(new Date())
            .repos(USER_REPOS)
            .build();

    static GitHubUser GITHUB_USER = GitHubUser.builder()
            .htmlURL(USER.url)
            .login(USER.userName)
            .email(USER.email)
            .avatarURL(USER.avatar)
            .location(USER.geoLocation)
            .name(USER.displayName)
            .createdAt(USER.createdAt)
            .build();

    static List<GitHubRepo> GITHUB_USER_REPOS = USER_REPOS.stream().map(repo -> GitHubRepo.builder()
            .name(repo.name).htmlURL(repo.url).build()).toList();
}