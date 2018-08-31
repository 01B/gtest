package com.nopainnocode.gamehubtest.user.service;

import com.nopainnocode.gamehubtest.user.domain.User;
import com.nopainnocode.gamehubtest.user.domain.dto.UserDto;
import com.nopainnocode.gamehubtest.user.domain.exception.InvalidNameException;
import com.nopainnocode.gamehubtest.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by no.pain.no.code@gmail.com
 * nopainnocode.tistory.com
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Page<User> getUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public User register(UserDto userDto) {

        User user = new User(userDto.getName(), userDto.getBirthday());
        ifUserNameDuplicatedThrowException(user);

        return userRepository.save(user);
    }

    /**
     * 저장된 회원들과 저장하려고 하는 회원의 이름 중복 검사
     */
    private void ifUserNameDuplicatedThrowException(User user) {
        if (
                userRepository.findByFirstNameAndMiddleName(user.getFirstName(), user.getMiddleName()).size() > 0
                || userRepository.findByFirstNameAndLastName(user.getFirstName(), user.getLastName()).size() > 0
                || userRepository.findByMiddleNameAndLastName(user.getMiddleName(), user.getLastName()).size() > 0) {
            throw new InvalidNameException();
        }
    }
}
