package com.nopainnocode.gtest.user.controller;

import com.nopainnocode.gtest.user.domain.User;
import com.nopainnocode.gtest.user.domain.dto.UserDto;
import com.nopainnocode.gtest.user.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by no.pain.no.code@gmail.com
 * nopainnocode.tistory.com
 */
public class UserControllerTest {

    private MockMvc mockMvc;
    private UserService userService;

    @Before
    public void setUp() {
        userService = mock(UserService.class);
        UserController userController = new UserController(userService);
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();
    }

    @Test
    public void 회원목록_get_mapping_test() throws Exception {
        // given
        Page<User> page = new PageImpl<>(
                Arrays.asList(
                        new User("young il park", LocalDate.of(1985,1,24))
                        , new User("so hyun park",LocalDate.of(1985,1,10))
                )
        );

        when(userService.getUsers(any(Pageable.class)))
                .thenReturn(page);

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("users"));
    }

    @Test
    public void 회원등록_페이지_getmapping() throws Exception {
        // given
        Page<User> page = new PageImpl<>(
                Arrays.asList(
                        new User("young il park", LocalDate.of(1985,1,24))
                        , new User("so hyun park",LocalDate.of(1985,1,10))
                )
        );

        when(userService.getUsers(any(Pageable.class)))
                .thenReturn(page);

        mockMvc.perform(get("/users/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("userRegister"));
    }

    @Test
    public void 회원등록_postmapping() throws Exception {

        UserDto userDto = new UserDto("young il park", LocalDate.now());
        mockMvc.perform(
                        post("/users").flashAttr("userDto", userDto)
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users"))
                .andDo(print());

        verify(userService,times(1)).register(userDto);
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void 회원등록_with_empty_name() throws Exception {
        // given
        UserDto userDto = new UserDto("", LocalDate.now());
        // when then
        mockMvc.perform(
                        post("/users").flashAttr("userDto", userDto)
                )
                .andExpect(model().attributeExists("nameError"))
                .andExpect(view().name("userRegister"));
    }

    @Test
    public void 회원등록_with_50자_넘는_이름() throws Exception {
        // given
        String fullName = "suhanmugubukiwadurumi samcheongabjadongbangsak kimm";
        UserDto userDto = new UserDto(fullName, LocalDate.now());

        // when then
        mockMvc.perform(
                post("/users").flashAttr("userDto", userDto)
        )
                .andExpect(model().attributeExists("nameError"))
                .andExpect(view().name("userRegister"));
    }

    @Test
    public void 회원등록_with_null_birthday() throws Exception {
        // given
        UserDto userDto = new UserDto("young il park", null);
        // when then
        mockMvc.perform(
                post("/users").flashAttr("userDto", userDto)
        )
                .andExpect(model().attributeExists("birthdayError"))
                .andExpect(view().name("userRegister"));
    }
}