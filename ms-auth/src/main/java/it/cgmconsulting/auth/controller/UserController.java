package it.cgmconsulting.auth.controller;

import it.cgmconsulting.auth.dto.JwtAuthenticationDto;
import it.cgmconsulting.auth.dto.SignInDto;
import it.cgmconsulting.auth.dto.SignUpDto;
import it.cgmconsulting.auth.entity.Role;
import it.cgmconsulting.auth.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/R99/{id}/{role}")
    public ResponseEntity<String> changeRole(
            @PathVariable int id, @PathVariable String role, @RequestHeader("userId") int userId){
        return ResponseEntity.ok(userService.changeRole(id, role, userId));
        // il super admin (alias SAURON) che effettua la richiesta non può cambiare il ruolo a se stesso
        // Messaggio restituito: Lo user <username> ha cambiato ruolo da <old_role> a <new_role>
    }
}
