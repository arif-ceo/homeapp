package com.home.controller;

import com.home.entity.User;
import com.home.payload.JWTTokenDto;
import com.home.payload.LoginDto;
import com.home.payload.UserDto;
import com.home.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> createUser(
            @Valid
            @RequestBody User user,
            BindingResult bindingResult){
        if (bindingResult.hasErrors()){
return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(),HttpStatus.OK);
        }
        UserDto savedUser = userService.createUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?>verifyLogin(@RequestBody LoginDto loginDto){
        String token = userService.verifyLogin(loginDto);
        if (token != null){
            JWTTokenDto jwtToken = new JWTTokenDto();
            jwtToken.setType("JWT Token");
            jwtToken.setToken(token);
            return new ResponseEntity<>(jwtToken, HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Invalid Token!", HttpStatus.BAD_REQUEST);
        }
    }
}
