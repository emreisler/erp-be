package github.com.emreisler.erp_be.application.dto;

import lombok.Data;

@Data
public class UserLoginDTO {
    private String email;
    private String password;
}

