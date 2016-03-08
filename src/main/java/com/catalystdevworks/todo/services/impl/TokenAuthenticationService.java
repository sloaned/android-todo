package com.catalystdevworks.todo.services.impl;

import com.catalystdevworks.todo.entities.User;
import com.catalystdevworks.todo.security.TokenHandler;
import com.catalystdevworks.todo.entities.UserAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/**
 * Gets a token from the header OR adds it to the header if this is the first time
 */
@Service
public class TokenAuthenticationService {

    private static final String AUTH_HEADER_NAME = "X-AUTH-TOKEN";

    public void setTokenHandler(TokenHandler tokenHandler) {
        this.tokenHandler = tokenHandler;
    }

    @Autowired
    private TokenHandler tokenHandler;

    public void addAuthentication(HttpServletResponse response, UserAuthentication authentication) {
        final User user = authentication.getDetails();
        response.addHeader(AUTH_HEADER_NAME, tokenHandler.createTokenForUser(user));
    }

    public Authentication getAuthentication(HttpServletRequest request) {
        System.out.println("here we are in the tokenAuthenticationService");
        Enumeration<String> headerNames = request.getHeaderNames();
        System.out.println("here are the headers: ");
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            System.out.println(headerName + ": " + request.getHeader(headerName));

        }
        final String token = request.getHeader(AUTH_HEADER_NAME);
        System.out.println("The token = " + token);

        if (token != null) {
            final User user = tokenHandler.parseUserFromToken(token);
            if (user != null)
                return new UserAuthentication(user);
        }
        return null;
    }
}