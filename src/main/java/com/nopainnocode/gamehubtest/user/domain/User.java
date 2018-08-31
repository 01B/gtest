package com.nopainnocode.gamehubtest.user.domain;

import com.nopainnocode.gamehubtest.user.domain.exception.InvalidNameException;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by no.pain.no.code@gmail.com
 * nopainnocode.tistory.com
 */

@Entity
@Getter
@NoArgsConstructor
public class User implements Serializable {
    public User (
            String fullName
            , LocalDate birthday
    ) {

        if(!fullName.contains(" "))
            throw new InvalidNameException();

        String[] names = fullName.trim().split(" ");
        if(names.length != 3)
            throw new InvalidNameException();

        this.firstName = names[0];
        this.middleName = names[1];
        this.lastName = names[2];
        this.birthday = birthday;
        this.joinDate = LocalDateTime.now();
    }

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String middleName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private LocalDate birthday;

    @Column(updatable = false)
    private LocalDateTime joinDate;
}
