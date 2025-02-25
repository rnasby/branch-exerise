package com.branch.exercise.user;

import com.branch.exercise.gitHubClient.GitHubRepo;
import com.branch.exercise.gitHubClient.GitHubUser;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

@Builder
@AllArgsConstructor
class User {
    @JsonProperty("user_name")
    public String userName;

    @JsonProperty("display_name")
    public String displayName;

    public String avatar;

    @JsonProperty("geo_location")
    public String geoLocation;

    public String email;
    public String url;

    @JsonProperty("created_at")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    public Date createdAt;

    @EqualsAndHashCode.Exclude
    public List<Repo> repos;

    User(GitHubUser gitHubUser, List<GitHubRepo> gitHubRepos) {
        this.url = gitHubUser.htmlURL;
        this.email = gitHubUser.email;
        this.userName = gitHubUser.login;
        this.displayName = gitHubUser.name;
        this.avatar = gitHubUser.avatarURL;
        this.createdAt = gitHubUser.createdAt;
        this.geoLocation = gitHubUser.location;
        this.repos = gitHubRepos.stream().map(Repo::new).toList();
    }

    @Builder
    @AllArgsConstructor
    static class Repo {
        public final String name, url;

        Repo(GitHubRepo repo) {
            this.name = repo.name;
            this.url = repo.htmlURL;
        }
    }
}