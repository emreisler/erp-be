package github.com.emreisler.erp_be.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    private static final String AUTH_SERVICE_VALIDATE_URL = "http://localhost:8081/v1/auth/validate";

    private final RestTemplate restTemplate;

    public AuthInterceptor(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write("Missing or invalid Authorization header");
            return false;
        }

        String token = authHeader.replace("Bearer ", "");

        // Validate token with auth service
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set(HttpHeaders.AUTHORIZATION, token);

            HttpEntity<String> entity = new HttpEntity<>("body", headers);

            restTemplate.exchange(AUTH_SERVICE_VALIDATE_URL, HttpMethod.GET, entity, String.class);
            return true; // Token is valid, continue to the handler
        } catch (Exception e) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write("Invalid or expired token");
            return false;
        }
    }
}
