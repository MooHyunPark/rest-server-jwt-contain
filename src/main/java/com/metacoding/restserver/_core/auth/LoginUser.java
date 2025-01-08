package com.metacoding.restserver._core.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.metacoding.restserver.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class LoginUser {
    private Integer id;
    private String username;
    @JsonIgnore
    private String jwt;
}
