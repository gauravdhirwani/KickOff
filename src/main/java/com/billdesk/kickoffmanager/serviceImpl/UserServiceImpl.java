package com.billdesk.kickoffmanager.serviceImpl;

import com.billdesk.kickoffmanager.dto.*;
import com.billdesk.kickoffmanager.entity.User;
import com.billdesk.kickoffmanager.jwt.JwtUtil;
import com.billdesk.kickoffmanager.mapper.UserMapper;
import com.billdesk.kickoffmanager.repository.UserRepository;
import com.billdesk.kickoffmanager.service.UserService;
import lombok.RequiredArgsConstructor;
import java.util.List;


import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final UserMapper userMapper;

    @Override
    public LoginResponseDto register(RegisterRequestDto requestDto) {

        if(userRepository.findByEmail(requestDto.getEmail()).isPresent()){
            throw new RuntimeException("User is already registered!");
        }


        User user = new User();
        user.setName(requestDto.getName());
        user.setEmail(requestDto.getEmail());
        user.setJerseyNumber(requestDto.getJerseyNumber());
        user.setPosition(requestDto.getPosition());
        user.setRole(requestDto.getRole());
        String hashedPassword =
                BCrypt.hashpw(
                        requestDto.getPassword(),
                        BCrypt.gensalt()
                );
        user.setPassword(hashedPassword);

        userRepository.save(user);

        return login(requestDto.getEmail(),requestDto.getPassword());
    }

    @Override
    public LoginResponseDto login(String email, String password) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(
                        () -> new RuntimeException("user not found! pls register!")
                );

        boolean passwordMatch = BCrypt.checkpw(password,user.getPassword());

        if(!passwordMatch){
            throw new RuntimeException("Wrong password!");
        }

        String token = jwtUtil.generateToken(email);
        return new LoginResponseDto(token);

    }
    @Override
    public UserResponseDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found!"));
        return userMapper.toDto(user);
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return userMapper.toDtoList(userRepository.findAll());
    }
}
