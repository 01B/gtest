package com.nopainnocode.gamehubtest.user.service;

import com.nopainnocode.gamehubtest.user.domain.User;
import com.nopainnocode.gamehubtest.user.domain.dto.UserDto;
import com.nopainnocode.gamehubtest.user.domain.exception.InvalidNameException;
import com.nopainnocode.gamehubtest.user.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.Arrays;

import static org.mockito.Mockito.*;

/**
 * Created by no.pain.no.code@gmail.com
 * nopainnocode.tistory.com
 */
public class UserServiceTest {

    private UserRepository userRepository;
    private UserService userService;

    @Before
    public void setUp() {
        userRepository = mock(UserRepository.class);
        userService = new UserService(userRepository);
    }

    @Test
    public void repository_get_users_call_test() {

        // given
        Pageable pageable = PageRequest.of(0, 5, Sort.Direction.DESC, "id");

        // when
        userService.getUsers(pageable);

        // then
        verify(userRepository, times(1)).findAll(pageable);
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    public void register_user_with_right_argument() {

        // given
        UserDto userDto = new UserDto("young il park", LocalDate.now());

        // when
        userService.register(userDto);

        // then
        verify(userRepository, times(1)).findAll();
        verify(userRepository,times(1)).save(any(User.class));
        verifyNoMoreInteractions(userRepository);
    }

    @Test(expected = InvalidNameException.class)
    public void 회원등록시에_중복된_이름일_경우() {

        LocalDate birthday = LocalDate.of(1985, 1, 24);
        when(userRepository.findAll())
                .thenReturn(
                        Arrays.asList(
                                new User("young il kim", birthday)
                                , new User("young mi kim", birthday)
                        )
                );

        userService.register(new UserDto("young il park", birthday));
    }
}