package com.nopainnocode.gtest.user.service;

import com.nopainnocode.gtest.user.domain.User;
import com.nopainnocode.gtest.user.domain.dto.UserDto;
import com.nopainnocode.gtest.user.domain.exception.InvalidNameException;
import com.nopainnocode.gtest.user.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.Collections;

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
        verify(userRepository, times(1)).findByFirstNameAndMiddleName("young", "il");
        verify(userRepository, times(1)).findByFirstNameAndLastName("young", "park");
        verify(userRepository, times(1)).findByMiddleNameAndLastName("il", "park");
        verify(userRepository,times(1)).save(any(User.class));
        verifyNoMoreInteractions(userRepository);
    }

    @Test(expected = InvalidNameException.class)
    public void 회원등록시에_first_middle_중복된_이름일_경우() {

        LocalDate birthday = LocalDate.of(1985, 1, 24);
        UserDto userDto = new UserDto("young il park", birthday);
        User user = new User(userDto.getName(), userDto.getBirthday());

        when(userRepository.findByFirstNameAndMiddleName(user.getFirstName(), user.getMiddleName()))
                .thenReturn(
                        Collections.singletonList(
                                new User("young il kim", birthday)
                        )
                );

        userService.register(userDto);
        verify(userRepository, times(1)).findByFirstNameAndMiddleName("young", "il");
        verifyNoMoreInteractions(userRepository);
    }

    @Test(expected = InvalidNameException.class)
    public void 회원등록시에_first_last_중복된_이름일_경우() {

        LocalDate birthday = LocalDate.of(1985, 1, 24);
        UserDto userDto = new UserDto("young il park", birthday);
        User user = new User(userDto.getName(), userDto.getBirthday());

        when(userRepository.findByFirstNameAndMiddleName(user.getFirstName(), user.getMiddleName()))
                .thenReturn(Collections.EMPTY_LIST);

        when(userRepository.findByFirstNameAndLastName(user.getFirstName(), user.getLastName()))
                .thenReturn(
                        Collections.singletonList(
                                new User("young mi park", birthday)
                        )
                );

        userService.register(userDto);
        verify(userRepository, times(1)).findByFirstNameAndMiddleName("young", "il");
        verify(userRepository, times(1)).findByFirstNameAndLastName("young", "park");
        verifyNoMoreInteractions(userRepository);
    }

    @Test(expected = InvalidNameException.class)
    public void 회원등록시에_middle_last_중복된_이름일_경우() {

        LocalDate birthday = LocalDate.of(1985, 1, 24);
        UserDto userDto = new UserDto("young il park", birthday);
        User user = new User(userDto.getName(), userDto.getBirthday());

        when(userRepository.findByFirstNameAndMiddleName(user.getFirstName(), user.getMiddleName()))
                .thenReturn(Collections.EMPTY_LIST);

        when(userRepository.findByFirstNameAndLastName(user.getFirstName(), user.getLastName()))
                .thenReturn(Collections.EMPTY_LIST);

        when(userRepository.findByMiddleNameAndLastName(user.getMiddleName(), user.getLastName()))
                .thenReturn(
                        Collections.singletonList(
                                new User("jae il park", birthday)
                        )
                );

        userService.register(userDto);
        verify(userRepository, times(1)).findByFirstNameAndMiddleName("young", "il");
        verify(userRepository, times(1)).findByFirstNameAndLastName("young", "park");
        verify(userRepository, times(1)).findByMiddleNameAndLastName("il", "park");
        verifyNoMoreInteractions(userRepository);
    }
}