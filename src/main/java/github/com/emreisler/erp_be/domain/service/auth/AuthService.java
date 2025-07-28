package github.com.emreisler.erp_be.domain.service.auth;


import github.com.emreisler.erp_be.application.dto.UserLoginDTO;
import github.com.emreisler.erp_be.application.dto.UserRegisterDTO;
import github.com.emreisler.erp_be.persistence.entity.User;
import github.com.emreisler.erp_be.persistence.repository.UserRepository;
import github.com.emreisler.erp_be.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    public String register(UserRegisterDTO userRegisterDTO, String token) {
        String email = jwtUtils.extractEmail(token);
        Optional<User> adminUser = userRepository.findByEmail(email);

        if (adminUser.isEmpty() || !adminUser.get().isAdmin()) {
            throw new RuntimeException("Unauthorized to register users.");
        }

        if (userRepository.findByEmail(userRegisterDTO.getEmail()).isPresent()) {
            throw new RuntimeException("Email already in use");
        }

        User user = new User();
        user.setName(userRegisterDTO.getName());
        user.setEmail(userRegisterDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
        userRepository.save(user);
        return jwtUtils.generateToken(user.getEmail());
    }

    public String login(UserLoginDTO userLoginDTO) {
        Optional<User> userOptional = userRepository.findByEmail(userLoginDTO.getEmail());
        if (userOptional.isEmpty() ||
                !passwordEncoder.matches(userLoginDTO.getPassword(), userOptional.get().getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        return jwtUtils.generateToken(userLoginDTO.getEmail());
    }

    public boolean validateToken(String token) {
        String email = jwtUtils.extractEmail(token);
        return email != null && jwtUtils.isTokenValid(token, email);
    }
}
