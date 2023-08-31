package com.job4j.accidents.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.job4j.accidents.Job4jAccidentsApplication;
import com.job4j.accidents.model.User;
import com.job4j.accidents.service.UserService;
import org.junit.jupiter.api.Test;

import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(classes = Job4jAccidentsApplication.class)
@AutoConfigureMockMvc
public class RegControlTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService users;

    @Test
    @WithMockUser
    public void shouldReturnDefaultMessageRegPage() throws Exception {
        this.mockMvc.perform(get("/reg"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("reg"));
    }

  /*  @Test
    @WithMockUser
    public void testPostRegSave() throws Exception {
        this.mockMvc.perform(post("/regSave")
                        .param("username","user1")
                        .param("password","123456"))
                .andDo(print())
                .andExpect(status().isOk());
        ArgumentCaptor<User> argument = ArgumentCaptor.forClass(User.class);
        verify(users).save(argument.capture());
        assertThat(argument.getValue().getUsername()).isEqualTo("user123");
    }*/
}