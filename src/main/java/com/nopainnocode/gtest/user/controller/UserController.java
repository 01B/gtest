package com.nopainnocode.gtest.user.controller;

import com.nopainnocode.gtest.user.domain.dto.UserDto;
import com.nopainnocode.gtest.user.domain.exception.InvalidNameException;
import com.nopainnocode.gtest.user.service.UserService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.data.domain.Sort.Direction.DESC;

/**
 * Created by no.pain.no.code@gmail.com
 * nopainnocode.tistory.com
 */
@Controller
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getUsers(Model model
            , @PageableDefault(sort = { "id" }, direction = DESC, size = 3) Pageable pageable) {
        model.addAttribute("users", userService.getUsers(pageable));
        return "home";
    }

    @GetMapping(value = "/register")
    public String register() {
        return "userRegister";
    }

    @PostMapping
    public String registerUser(Model model
            , @Valid @ModelAttribute UserDto userDto
            , Errors errors) {

        // dto binding errors
        if(errors.hasErrors()) {
            for (FieldError error : errors.getFieldErrors()) {
                if (error.getField().equals("name"))
                    model.addAttribute("nameError", error.getDefaultMessage());
                if (error.getField().equals("birthday"))
                    model.addAttribute("birthdayError", error.getDefaultMessage());
            }
            return "userRegister";
        }

        userService.register(userDto);
        return "redirect:/users";
    }

    @ExceptionHandler(value = InvalidNameException.class)
    public String invalidNameExceptionHandler(Model model, Exception exception) {
        model.addAttribute("nameError", exception.getMessage());
        return "userRegister";
    }
}
