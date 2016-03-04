package com.catalystdevworks.todo.entities;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Created by g on 3/4/16.
 */
public class User extends org.springframework.security.core.userdetails.User {
    private final Integer id;

    public User(Integer id, String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
