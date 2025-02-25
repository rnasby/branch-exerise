package com.branch.exercise.gitHubClient;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Builder
@EqualsAndHashCode
@AllArgsConstructor
public class GitHubUser {
    public String login;

    public String name;

    @JsonProperty("avatar_url")
    public String avatarURL;

    public String location;

    public String email;

    @JsonProperty("html_url")
    public String htmlURL;

    @JsonProperty("created_at")
    public Date createdAt;
}