package com.peaksoft.spring_boot.controller;

import com.peaksoft.spring_boot.dto.*;
import com.peaksoft.spring_boot.dto.validation.ValidationType;
import com.peaksoft.spring_boot.entity.User;
import com.peaksoft.spring_boot.repository.UserRepository;
import com.peaksoft.spring_boot.security.jwt.JwtTokenUtil;
import com.peaksoft.spring_boot.service.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/jwt")
@Tag(name = "Auth API", description = "User with role admin, editor can login and register")
public class AuthController {

    private final UserServiceImpl userService;
    private final UserRepository repository;
    private final JwtTokenUtil jwtTokenUtil;
    private final LoginMapper loginMapper;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    @Operation(summary = "login", description = "user can login")
    public ResponseEntity<LoginResponse> getLogin(@RequestBody LoginRequest request) {
        try {
            UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
            User user = repository.findByEmail(token.getName());
            return ResponseEntity.ok().body(loginMapper.loginView(jwtTokenUtil.generateToken(user), ValidationType.SUCCESSFUL, user));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(loginMapper.loginView("", ValidationType.LOGIN_FAILED, null));
        }
    }

    @PostMapping("/registration")
    @Operation(summary = "registration", description = "user can register")
    public StudentResponse create(@RequestBody StudentRequest request) {
        return userService.register(request);
    }
}
