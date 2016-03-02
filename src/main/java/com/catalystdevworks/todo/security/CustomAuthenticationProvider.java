package com.catalystdevworks.todo.security;

import com.catalystdevworks.todo.services.impl.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

/**
 * Authenticates a User via checking username and password matches whats in the database
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserDetailService userService;

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserDetails user = userService.loadUserByUsername(username);

        if (!user.getUsername().equalsIgnoreCase(username))
            throw new BadCredentialsException("Username not found.");

        if (!password.equals(user.getPassword()))
            throw new BadCredentialsException("Wrong password.");

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        return new UsernamePasswordAuthenticationToken(username, password, authorities);
    }

    public boolean supports(Class<?> arg0) {
        return true;
    }

}