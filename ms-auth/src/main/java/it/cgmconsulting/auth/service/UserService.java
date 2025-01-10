package it.cgmconsulting.auth.service;

import it.cgmconsulting.auth.dto.JwtAuthenticationDto;
import it.cgmconsulting.auth.dto.SignInDto;
import it.cgmconsulting.auth.dto.SignUpDto;
import it.cgmconsulting.auth.entity.Role;
import it.cgmconsulting.auth.entity.User;
import it.cgmconsulting.auth.exception.BadRequestException;
import it.cgmconsulting.auth.exception.ConflictException;
import it.cgmconsulting.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public String signup(SignUpDto request) {
        if( userRepository.existsByUsernameOrEmail(request.username(), request.email()) )
            throw new ConflictException("Username or email already in use");
        User user = User.builder()
                .username(request.username())
                .email(request.email())
                .createdAt(LocalDateTime.now())
                .password(passwordEncoder.encode(request.password()))
                .role(Role.MEMBER)
                .enabled(true)
                .build();
        userRepository.save(user);
        return "User successfully registered";
    }

    public JwtAuthenticationDto signin(SignInDto request) {
        User user = userRepository.findByUsername(request.username())
                .orElseThrow(() -> new BadRequestException("Wrong credentials"));
        if(!passwordEncoder.matches(request.password(), user.getPassword()))
            throw new BadRequestException("Wrong credentials");

        JwtAuthenticationDto loggedUser= JwtAuthenticationDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole().name())
                .token("token")
                .build();
        return loggedUser;
    }
}
