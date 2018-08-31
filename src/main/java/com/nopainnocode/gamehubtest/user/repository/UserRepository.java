package com.nopainnocode.gamehubtest.user.repository;

import com.nopainnocode.gamehubtest.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by no.pain.no.code@gmail.com
 * nopainnocode.tistory.com
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findByFirstNameAndMiddleName(String firstName, String middleName);

    List<User> findByFirstNameAndLastName(String firstName, String lastName);

    List<User> findByMiddleNameAndLastName(String middleName, String lastName);
}
