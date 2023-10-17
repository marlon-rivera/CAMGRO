package com.project.software.camgro.Camgro.auth;

import com.project.software.camgro.Camgro.domain.ErrorMesage;
import com.project.software.camgro.Camgro.services.AccountService;
import com.project.software.camgro.Camgro.services.EmailService;
import com.project.software.camgro.Camgro.services.RecoverPasswordService;
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
    private final EmailService emailService;
    private final RecoverPasswordService recoverPasswordService;

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
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest registerRequest) throws Exception {
        return ResponseEntity.ok(authService.register(registerRequest));
    }

    @PostMapping(value = "generate-code")
    public ResponseEntity<?> recoverPassword(@RequestBody RecoverPasswordRequest recoverPasswordRequest) {
        try{
            emailService.sendEmailRecoverPassword(recoverPasswordRequest.data(), recoverPasswordService.generateCode(recoverPasswordRequest.data()));
        }catch(UsernameNotFoundException ex){
            return ResponseEntity.badRequest().body(new ErrorMesage(ex.getMessage()));
        }
        return ResponseEntity.ok(new AuthResponse("ok"));
    }

    @PostMapping(value = "validate-code")
    public ResponseEntity<?> validateCode(@RequestBody RecoverPasswordRequest recoverPasswordRequest){
        if(recoverPasswordService.validateExpirationDate()) {
            if (recoverPasswordService.validateCode(recoverPasswordRequest.data()))
                return ResponseEntity.ok(new AuthResponse("ok."));
            return ResponseEntity.badRequest().body(new ErrorMesage("El codigo ingresado no es el generado."));

        }
        return ResponseEntity.badRequest().body(new ErrorMesage("Ups!, ya pasó el tiempo limite."));

    }

    @PostMapping(value = "change-password")
    public ResponseEntity<?> changePassword(@RequestBody RecoverPasswordRequest recoverPasswordRequest){
        recoverPasswordService.changePassword(recoverPasswordRequest.data());
        return ResponseEntity.ok(new AuthResponse("Contraseña cambiada correctamente."));
    }
}
