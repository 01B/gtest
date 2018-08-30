package com.nopainnocode.gamehubtest.user.service;

import com.nopainnocode.gamehubtest.user.domain.User;
import com.nopainnocode.gamehubtest.user.domain.dto.UserDto;
import com.nopainnocode.gamehubtest.user.domain.exception.InvalidNameException;
import com.nopainnocode.gamehubtest.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by no.pain.no.code@gmail.com
 * nopainnocode.tistory.com
 */
@Service
@Transactional
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
        for (User userTemp : userRepository.findAll()) {
            if (userTemp.isNameDuplicated(user)) {
                throw new InvalidNameException();
            }
        }
    }
}