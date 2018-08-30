package com.nopainnocode.gamehubtest.user.repository;

import com.nopainnocode.gamehubtest.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by no.pain.no.code@gmail.com
 * nopainnocode.tistory.com
 */
public interface UserRepository extends JpaRepository<User, Integer> {
}
