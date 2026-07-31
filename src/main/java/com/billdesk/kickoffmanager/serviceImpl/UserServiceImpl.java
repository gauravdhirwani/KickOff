package com.billdesk.kickoffmanager.serviceImpl;

import com.billdesk.kickoffmanager.dto.*;
import com.billdesk.kickoffmanager.entity.Team;
import com.billdesk.kickoffmanager.entity.User;
import com.billdesk.kickoffmanager.enums.Role;
import com.billdesk.kickoffmanager.jwt.JwtUtil;
import com.billdesk.kickoffmanager.mapper.UserMapper;
import com.billdesk.kickoffmanager.repository.GoalRepository;
import com.billdesk.kickoffmanager.repository.TeamRepository;
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
    private final GoalRepository goalRepository;
    private final TeamRepository teamRepository;

    @Override
    public LoginResponseDto register(UserRequestDto requestDto) {

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

    @Override
    public PlayerStatsDto getPlayerStats(Long playerId) {

        User user = userRepository.findById(playerId)
                .orElseThrow(() -> new RuntimeException("Player not found!"));

        long goals = goalRepository.countByScorerId(playerId);
        long assists = goalRepository.countByAssisterId(playerId);
        String teamName = (user.getTeam() != null) ? user.getTeam().getName() : null;

        return new PlayerStatsDto(user.getId(), user.getName(), teamName, goals, assists);
    }

    @Override
    public List<UserResponseDto> getUnassignedPlayers() {
        List<User> players = userRepository.findByTeamIsNullAndRole(Role.PLAYER);
        return userMapper.toDtoList(players);
    }

    @Override
    public UserResponseDto assignPlayerToTeam(Long playerId, Long teamId) {

        User player = userRepository.findById(playerId)
                .orElseThrow(() -> new RuntimeException("Player not found!"));

        if (player.getRole() != Role.PLAYER) {
            throw new RuntimeException("Only players can be assigned to a team!");
        }

        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new RuntimeException("Team not found!"));

        player.setTeam(team);
        return userMapper.toDto(userRepository.save(player));
    }
}
