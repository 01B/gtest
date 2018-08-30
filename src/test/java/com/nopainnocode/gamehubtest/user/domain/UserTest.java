package com.nopainnocode.gamehubtest.user.domain;

import com.nopainnocode.gamehubtest.user.domain.exception.InvalidNameException;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

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

    @Test
    public void 이름_중복_검사() {
        // given
        LocalDate birthday = LocalDate.of(1985, 1, 24);
        User user1 = new User("young il park", birthday);
        // 이름 완전 일치
        User user2 = new User("young il park", birthday);
        // when then
        assertTrue(user1.isNameDuplicated(user2));

        // given
        // first name, middle name 일치
        user2 = new User("young il kim", birthday);
        // when then
        assertTrue(user1.isNameDuplicated(user2));

        // given
        // first name, last name 일치
        user2 = new User("young mi park", birthday);
        // when then
        assertTrue(user1.isNameDuplicated(user2));

        // given
        // middle name, last name 일치
        user2 = new User("joo il park", birthday);
        // when then
        assertTrue(user1.isNameDuplicated(user2));

        // given
        // first name 만 일치
        user2 = new User("young bin kim", birthday);
        // when then
        assertFalse(user1.isNameDuplicated(user2));

        // given
        // middle name 만 일치
        user2 = new User("sung il kim", birthday);
        // when then
        assertFalse(user1.isNameDuplicated(user2));

        // given
        // last name 만 일치
        user2 = new User("sung kuk park", birthday);
        // when then
        assertFalse(user1.isNameDuplicated(user2));
    }
}