package github.com.emreisler.erp_be.auth;

import github.com.emreisler.erp_be.domain.service.auth.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    private AuthService authService;

    // Define whitelisted paths (can also use regex or Ant patterns)
    private static final String[] WHITELIST = {
            "/v1/auth/login",
            "/v1/auth/register",
            "/actuator/health"
    };

    private boolean isWhitelisted(String path) {
        for (String allowed : WHITELIST) {
            if (path.startsWith(allowed)) {
                return true;
            }
        }
        return false;
    }

    public AuthInterceptor(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path = request.getRequestURI();

        if (isWhitelisted(path)) {
            return true;
        }

        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write("Missing or invalid Authorization header");
            return false;
        }

        String token = authHeader.replace("Bearer ", "");

        if (!this.authService.validateToken(token)) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write("Invalid or expired token");
            return false;
        }
        return true;
    }
}