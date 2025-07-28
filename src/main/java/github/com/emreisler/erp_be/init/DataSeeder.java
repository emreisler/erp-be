package github.com.emreisler.erp_be.init;

import github.com.emreisler.erp_be.persistence.entity.User;
import github.com.emreisler.erp_be.persistence.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataSeeder(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.findByEmail("admin@example.com").isEmpty()) {
            User admin = new User();
            admin.setName("Admin");
            admin.setEmail("admin@example.com");
            admin.setPassword(passwordEncoder.encode("admin123")); // Secure password
            admin.setAdmin(true);
            userRepository.save(admin);
            System.out.println("Admin user created: admin@example.com / admin123");
        } else {
            System.out.println("Admin user already exists.");
        }
    }
}

