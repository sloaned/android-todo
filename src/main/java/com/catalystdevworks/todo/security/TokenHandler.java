package com.catalystdevworks.todo.security;

import com.catalystdevworks.todo.services.impl.UserDetailService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

/**
 * Created by g on 2/29/16.
 */
@Component
@Scope(BeanDefinition.SCOPE_SINGLETON)
/**
 * Creates or parses a token to get the encrypted username
 * called By the TokenService
 */
public final class TokenHandler {

    private final String secret  = "tooManySecrets";
    @Autowired
    private UserDetailService userService;

    public User parseUserFromToken(String token) {
        String username = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        return userService.loadUserByUsername(username);
    }

    public String createTokenForUser(User user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public void setUserService(UserDetailService userService) {
        this.userService = userService;
    }
}
