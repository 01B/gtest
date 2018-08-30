package com.nopainnocode.gamehubtest.user.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * Created by no.pain.no.code@gmail.com
 * nopainnocode.tistory.com
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

//    @NotNull(message = "이름은 필수정보입니다.")
    @NotEmpty(message = "이름은 필수정보입니다.")
    @Size(max = 50, message = "이름의 길이는 50자를 넘지 못합니다.")
    private String name;

    @NotNull(message = "생일은 필수정보입니다.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
}
