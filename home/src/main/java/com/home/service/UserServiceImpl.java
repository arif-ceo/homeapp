package com.home.service;

import com.home.entity.User;
import com.home.exception.ResourceNotFoundException;
import com.home.payload.LoginDto;
import com.home.payload.UserDto;
import com.home.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    private JWTService jwtService;

    public UserServiceImpl(UserRepository userRepository, JWTService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    @Override
    public UserDto createUser(User user) {
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        User savedUser = userRepository.save(user);
        return entityToDto(savedUser);
    }

    @Override
    public String verifyLogin(LoginDto loginDto) {

        Optional<User> opName = userRepository.findByName(loginDto.getName());
        User user = opName.orElseThrow(
                () -> new ResourceNotFoundException("User not exists..")
        );
        if (BCrypt.checkpw(loginDto.getPassword(), user.getPassword())){
            return jwtService.generateToken(user);
        }
        return null;
    }

    public UserDto entityToDto(User user){
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        return dto;
    }
}
