package it.cgmconsulting.auth.controller;

import it.cgmconsulting.auth.dto.JwtAuthenticationDto;
import it.cgmconsulting.auth.dto.SignInDto;
import it.cgmconsulting.auth.dto.SignUpDto;
import it.cgmconsulting.auth.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/R0/signup")
    public ResponseEntity<String> signup(@RequestBody @Valid SignUpDto request){
        return new ResponseEntity<String>(userService.signup(request), HttpStatus.CREATED);
    }

    @PostMapping("/R0/signin")
    public ResponseEntity<JwtAuthenticationDto> signin(@RequestBody @Valid SignInDto request){
        return ResponseEntity.ok(userService.signin(request));
    }
}
