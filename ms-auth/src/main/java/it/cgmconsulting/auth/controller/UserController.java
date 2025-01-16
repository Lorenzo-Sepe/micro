package it.cgmconsulting.auth.controller;

import it.cgmconsulting.auth.dto.JwtAuthenticationDto;
import it.cgmconsulting.auth.dto.SignInDto;
import it.cgmconsulting.auth.dto.SignUpDto;
import it.cgmconsulting.auth.dto.UserProfileDto;
import it.cgmconsulting.auth.entity.Role;
import it.cgmconsulting.auth.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Validated
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

    @PatchMapping("/R99/{id}/{role}")
    public ResponseEntity<String> changeRole(
            @PathVariable int id, @PathVariable String role, @RequestHeader("userId") int userId){
        return ResponseEntity.ok(userService.changeRole(id, role, userId));
        // il super admin (alias SAURON) che effettua la richiesta non può cambiare il ruolo a se stesso
        // Messaggio restituito: Lo user <username> ha cambiato ruolo da <old_role> a <new_role>
    }

    @PatchMapping("/RA/")
    public ResponseEntity<UserProfileDto> changeUsername(
            @RequestParam @NotBlank(message = "Username cannot be null or blank") @Size(max = 20, min = 3, message = "Username must be between 3 and 20 characters") String username,
            @RequestHeader("userId") int userId){
        return ResponseEntity.ok(userService.changeUsername(userId, username));
    }

    @GetMapping("/RI/enabled/{userId}")
    public Boolean isEnabled(@PathVariable int userId){
        return userService.isEnabled(userId);
    }

}
