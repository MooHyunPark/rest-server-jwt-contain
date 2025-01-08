package com.metacoding.restserver.user;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;


public class UserRequest {
    @Data
    public static class LoginDTO {
        @NotEmpty
        private String username;
        @NotEmpty
        private String password;
    }
}
