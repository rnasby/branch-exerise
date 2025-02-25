package com.branch.exercise.user;

import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class UserControllerIT {
    private final MockMvc mockMvc;

    @Autowired
    public UserControllerIT(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    public void testGetUser() throws Exception{
        final String USER = "octocat";

        // TODO: Validate additional repos. The current test only validates the first repo.
        mockMvc.perform(MockMvcRequestBuilders.get("/users/{username}", USER))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(jsonPath("$.user_name", is(USER)))
            .andExpect(jsonPath("$.display_name", is("The Octocat")))
            .andExpect(jsonPath("$.email").value(IsNull.nullValue()))
            .andExpect(jsonPath("$.avatar", is("https://avatars.githubusercontent.com/u/583231?v=4")))
            .andExpect(jsonPath("$.geo_location", is("San Francisco")))
            .andExpect(jsonPath("$.url", is("https://github.com/octocat")))
            .andExpect(jsonPath("$.created_at", is("2011-01-25 18:44:36")))
            .andExpect(jsonPath("$.repos[0].name", is("boysenberry-repo-1")))
            .andExpect(jsonPath("$.repos[0].url", is("https://github.com/octocat/boysenberry-repo-1")));
    }
}