package com.metacoding.restserver.post;

import com.metacoding.restserver._core.auth.LoginUser;
import com.metacoding.restserver._core.auth.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PostController {
    private final PostService postService;

    @GetMapping("/api")
    public ResponseEntity<?> api(@SessionUser LoginUser loginUser) {
        return ResponseEntity.ok("통과함" + loginUser.getUsername());
    }
}