package github.com.emreisler.erp_be.application.controllers;

import github.com.emreisler.erp_be.application.dto.TokenResponseDTO;
import github.com.emreisler.erp_be.application.dto.UserLoginDTO;
import github.com.emreisler.erp_be.application.dto.UserRegisterDTO;
import github.com.emreisler.erp_be.domain.service.auth.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<TokenResponseDTO> register(
            @RequestBody UserRegisterDTO userRegisterDTO,
            @RequestHeader("Authorization") String token
    ) {
        String newToken = authService.register(userRegisterDTO, token.replace("Bearer ", ""));
        return ResponseEntity.ok(new TokenResponseDTO(newToken));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> login(@RequestBody UserLoginDTO userLoginDTO) {
        String token = authService.login(userLoginDTO);
        return ResponseEntity.ok(new TokenResponseDTO(token));
    }

    @GetMapping("/validate")
    public ResponseEntity<Void> validate(@RequestHeader("Authorization") String token) {
        if (authService.validateToken(token.replace("Bearer ", ""))) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(401).build();
    }
}
