package com.branch.exercise.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.BDDMockito.*;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockitoBean
    UserService userService;

    String expectedCreatedAtJSONValue;

    @BeforeEach
    public void setup() throws Exception {
        expectedCreatedAtJSONValue = JsonPath.read(objectMapper.writeValueAsString(TestData.USER),
                "$.created_at");
    }

    @Test
    public void getUserTest() throws Exception {
        // Set up mock
        given(userService.getResponse(TestData.USER.userName)).willReturn(TestData.USER);

        // Test
        mockMvc.perform(MockMvcRequestBuilders.get("/users/{username}", TestData.USER.userName))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.user_name", is(TestData.USER.userName)))
                .andExpect(jsonPath("$.display_name", is(TestData.USER.displayName)))
                .andExpect(jsonPath("$.email", is(TestData.USER.email)))
                .andExpect(jsonPath("$.avatar", is(TestData.USER.avatar)))
                .andExpect(jsonPath("$.geo_location", is(TestData.USER.geoLocation)))
                .andExpect(jsonPath("$.url", is(TestData.USER.url)))
                .andExpect(jsonPath("$.created_at", is(expectedCreatedAtJSONValue)))
                .andExpect(jsonPath("$.repos[0].name", is(TestData.USER.repos.get(0).name)))
                .andExpect(jsonPath("$.repos[0].url", is(TestData.USER.repos.get(0).url)))
                .andExpect(jsonPath("$.repos[1].name", is(TestData.USER.repos.get(1).name)))
                .andExpect(jsonPath("$.repos[1].url", is(TestData.USER.repos.get(1).url)));
    }
}