package com.project.software.camgro.Camgro.auth;

import com.project.software.camgro.Camgro.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final AccountService accountService;

    @PostMapping(value ="login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        AuthResponse response = null;
        try {
            response = authService.login(loginRequest);
        }catch (UsernameNotFoundException e){
            return ResponseEntity.status(403).body(e.getMessage());
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest registerRequest){
        return ResponseEntity.ok(authService.register(registerRequest));
    }
}
