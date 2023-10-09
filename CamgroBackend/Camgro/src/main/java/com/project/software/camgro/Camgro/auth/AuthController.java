package com.project.software.camgro.Camgro.auth;

import com.project.software.camgro.Camgro.auth.AuthResponse;
import com.project.software.camgro.Camgro.auth.AuthService;
import com.project.software.camgro.Camgro.auth.LoginRequest;
import com.project.software.camgro.Camgro.auth.RegisterRequest;
import com.project.software.camgro.Camgro.staticdomain.AccountCount;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
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
    private final AccountCount accountCount;

    @PostMapping(value ="login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        AuthResponse response = null;
        register(new RegisterRequest("pilar", "32133333", "as", "Bogota", "Bogota", "pilar@gmail.com", "123"));
        System.out.println(AccountCount.getInstance().getLastRecord());
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
