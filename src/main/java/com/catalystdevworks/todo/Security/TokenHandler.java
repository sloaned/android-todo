package com.catalystdevworks.todo.Security;

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
public final class TokenHandler {

    private final String secret  = "tooManySecrets";

    @Autowired
    private CustomUserDetailsService userService;


    public User parseUserFromToken(String token) {
        String username = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        System.out.println(username);

        return userService.loadUserByUsername(username);
    }

    public String createTokenForUser(User user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public void setUserService(CustomUserDetailsService userService) {
        this.userService = userService;
    }
}
