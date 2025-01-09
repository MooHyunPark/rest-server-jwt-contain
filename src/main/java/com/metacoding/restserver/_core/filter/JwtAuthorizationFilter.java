package com.metacoding.restserver._core.filter;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.metacoding.restserver._core.auth.JwtEnum;
import com.metacoding.restserver._core.auth.LoginUser;
import com.metacoding.restserver._core.util.JwtUtil;
import com.metacoding.restserver._core.util.Resp;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.io.PrintWriter;

@RequiredArgsConstructor
public class JwtAuthorizationFilter implements Filter {

    private final JwtUtil jwtUtil;

    ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String jwt = request.getHeader("Authorization");

        if (jwt == null) {
            onError(response, "토큰 없음");
            return;
        }

        if (!jwt.startsWith("Bearer ")) {
            onError(response, "토큰이 공백이거나 프로토콜 잘못됨");
            return;
        }


        try {
            LoginUser sessionUser = jwtUtil.verify(jwt);

            HttpSession session = request.getSession();
            session.setAttribute("sessionUser", sessionUser);

            chain.doFilter(request, response);
        } catch (SignatureVerificationException | JWTDecodeException e1) {
            onError(response, String.valueOf(JwtEnum.ACCESS_TOKEN_INVALID));
        } catch (TokenExpiredException e2){
            onError(response, String.valueOf(JwtEnum.ACCESS_TOKEN_TIMEOUT));
        }

    }

    private void onError(HttpServletResponse response, String message) {
        try {
            String responseBody = objectMapper.writeValueAsString(Resp.fail(message)); // Resp 객체를 json으로 변경

            response.setStatus(401);
            response.setContentType("application/json; charset=utf-8");
            PrintWriter out = response.getWriter();
            out.println(responseBody);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
