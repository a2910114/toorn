package com.mykhaylenko.toorn.controller;

import junit.framework.TestCase;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Created by pavlo.mykhaylenko on 8/13/2015.
 */
public class HomeControllerTest extends TestCase {

    private HomeController homeController;

    @Override
    public void setUp() throws Exception {
        homeController = new HomeController();
    }

    @Test
    public void testHome() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(homeController).build();
        mockMvc.perform(get("/")).andExpect(view().name("home"));
    }

    @Test
    public void testHomeLink() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(homeController).build();
        mockMvc.perform(get("/home")).andExpect(view().name("home"));
    }
}