package com.catalystdevworks.todo.Security;

import com.catalystdevworks.todo.services.impl.UsersServiceImpl;
import com.google.common.base.Preconditions;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;

/**
 * Created by g on 2/29/16.
 */
public final class TokenHandler {

    private final String secret;

    @Autowired
    private CustomUserDetailsService userService;

    public TokenHandler() {
        this.secret = "tooManySecrets";
    }

    public User parseUserFromToken(String token) {
        String username = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        System.out.println(username);
        assert userService != null;
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
