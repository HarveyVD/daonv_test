package com.example.daonv_onemoung.controller;

import com.example.daonv_onemoung.common.UserDTO;
import com.example.daonv_onemoung.service.facade.UserService;
import com.example.daonv_onemoung.store.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void givenUserObject_whenCreateUser_thenReturnSavedUser() throws Exception{
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("Ramesh");
        userDTO.setId(Long.valueOf(1));
        BDDMockito.given(userService.saveUser(userDTO)).willReturn(userDTO);

        // given - precondition or setup
        mockMvc.perform( MockMvcRequestBuilders
                .post("/user/create")
                .content(asJsonString(userDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());

    }

    @Test
    public void givenUserId_whenGetUserById_thenReturnUserObject() throws Exception{
        // given - precondition or setup
        long userId = 1L;
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("Ramesh");

        BDDMockito.given(userService.getUser(userId)).willReturn(userDTO);

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/{id}", userId));

        mockMvc.perform( MockMvcRequestBuilders
                .get("/user/{id}", userId)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("Ramesh"));

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
