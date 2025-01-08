package com.metacoding.restserver.user;

import com.metacoding.restserver._core.auth.LoginUser;
import com.metacoding.restserver._core.error.exception.Exception401;
import com.metacoding.restserver._core.util.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public LoginUser 로그인(UserRequest.@Valid LoginDTO reqDTO) {
        User user = userRepository.findByUsername(reqDTO.getUsername()).orElseThrow(() -> new Exception401("유저네임 혹은 패스워드가 틀렸습니다."));

        if (!user.getPassword().equals((reqDTO.getPassword()))) {
            throw new Exception401("유저네임 혹은 패스워드가 틀렸습니다.");
        }

        String jwt = jwtUtil.create(user);
        return new LoginUser(user.getId(), user.getUsername(), jwt);
    }
}