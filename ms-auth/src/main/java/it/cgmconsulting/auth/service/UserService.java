package it.cgmconsulting.auth.service;

import it.cgmconsulting.auth.dto.JwtAuthenticationDto;
import it.cgmconsulting.auth.dto.SignInDto;
import it.cgmconsulting.auth.dto.SignUpDto;
import it.cgmconsulting.auth.entity.Role;
import it.cgmconsulting.auth.entity.User;
import it.cgmconsulting.auth.exception.BadRequestException;
import it.cgmconsulting.auth.exception.ConflictException;
import it.cgmconsulting.auth.exception.UnauthorizedException;
import it.cgmconsulting.auth.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public String signup(SignUpDto request){
        if(userRepository.existsByUsernameOrEmail(request.username(), request.email()))
            throw new ConflictException("Username or email already in use");

        User user = User.builder()
                .username(request.username())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .enabled(true)
                .createdAt(LocalDateTime.now())
                .role(Role.MEMBER)
                .build();
        userRepository.save(user);
        return "User successfully registered";
    }

    public JwtAuthenticationDto signin(SignInDto request){
        User user = userRepository.getUserByUsername(request.username())
                .orElseThrow(()-> new BadRequestException("Wrong credentials"));
        if(!passwordEncoder.matches(request.password(), user.getPassword()))
            throw new BadRequestException("Wrong credentials");

        JwtAuthenticationDto loggedUser = JwtAuthenticationDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole().name())
                .token(jwtService.generateToken(user))
                .build();
        return loggedUser;
    }

    @Transactional
    public String changeRole(int id, String newRole, int userId){
        try{
            Role role = Role.valueOf(newRole);
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new BadRequestException("User not found"));
            if (user.getId() == userId) {
                throw new UnauthorizedException("You cannot change your own role");
            }
            Role oldRole = user.getRole();
            if(oldRole.equals(role))
                throw new BadRequestException("Old role and new role are the same");
            user.setRole(role);
            user.setUpdatedAt(LocalDateTime.now());
            return "User " + user.getUsername() + " has changed role from " + oldRole + " to " + role.name();
        } catch(IllegalArgumentException e){
            throw new IllegalArgumentException("Role not present");
        }
    }
}
