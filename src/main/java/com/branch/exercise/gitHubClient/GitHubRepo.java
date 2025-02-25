package com.branch.exercise.gitHubClient;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;

@Builder
@EqualsAndHashCode
@AllArgsConstructor
public class GitHubRepo {
    public String name;

    @JsonProperty("html_url")
    public String htmlURL;
}