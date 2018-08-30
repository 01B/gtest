package com.nopainnocode.gamehubtest.user.domain.exception;

/**
 * Created by no.pain.no.code@gmail.com
 * nopainnocode.tistory.com
 */
public class InvalidNameException extends RuntimeException {
    public InvalidNameException() {
        super("사용할 수 없는 이름입니다.");
    }
}
