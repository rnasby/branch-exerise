package com.branch.exercise;

import lombok.Builder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
class PageController {
    static private final Logger LOGGER = LoggerFactory.getLogger(PageController.class);

    PageController() {
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/index.html";
    }

    @GetMapping("/template")
    String getTemplate(Model model) {
        LOGGER.info("getTemplate");
        model.addAttribute("courses", List.of(
            Course.builder().id("1").name("Spring Boot").description("10 Steps").build(),
            Course.builder().id("2").name("Spring MVC").description("10 Steps").build(),
            Course.builder().id("3").name("Spring Security").description("10 Steps").build()
        ));

        return "template";
    }

    @GetMapping("/vue/app")
    String getVue(Model model) {
        model.addAttribute("data", VueData.builder().applicationTitle("Test").build());
        return "/vue/app";
    }

    @GetMapping("/react/app")
    String getReact(Model model) {
        return "/react/react";
    }

    @Builder
    static class Course {
        public String id, name, description;
    }

    @Builder
    static class VueData {
        public String user, menu, error, message, applicationTitle;
    }
}