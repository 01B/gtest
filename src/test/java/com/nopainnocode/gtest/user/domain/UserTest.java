package com.nopainnocode.gtest.user.domain;

import com.nopainnocode.gtest.user.domain.exception.InvalidNameException;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

/**
 * Created by no.pain.no.code@gmail.com
 * nopainnocode.tistory.com
 */
public class UserTest {

    @Test
    public void user_초기화_테스트_정상() {

        // given
        String fullName = "young il park";
        LocalDate birthday = LocalDate.of(1985, 1, 24);

        // when
        User user = new User(fullName, birthday);

        // then
        assertEquals(user.getFirstName(), "young");
        assertEquals(user.getMiddleName(), "il");
        assertEquals(user.getLastName(), "park");
        assertEquals(user.getBirthday(), birthday);
    }

    @Test(expected = InvalidNameException.class)
    public void user_초기화_이름_한글자() {
        // given
        String fullName = "young";
        LocalDate birthday = LocalDate.of(1985, 1, 24);

        // when
        User user = new User(fullName, birthday);
    }

    @Test(expected = InvalidNameException.class)
    public void user_초기화_이름_두글자() {
        // given
        String fullName = "young il";
        LocalDate birthday = LocalDate.of(1985, 1, 24);

        // when
        User user = new User(fullName, birthday);
    }

    @Test(expected = InvalidNameException.class)
    public void user_초기화_이름_두글자_앞뒤공백() {
        // given
        String fullName = "   young il    ";
        LocalDate birthday = LocalDate.of(1985, 1, 24);

        // when
        User user = new User(fullName, birthday);
    }
}