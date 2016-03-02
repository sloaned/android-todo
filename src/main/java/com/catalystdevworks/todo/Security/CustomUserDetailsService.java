package com.catalystdevworks.todo.Security;

import com.catalystdevworks.todo.dao.impl.UserDaoImpl;
import com.catalystdevworks.todo.entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
/**
 * Loads a USERS from the database and transforms it to USER
 */
@Service
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    private UserDaoImpl dao;

    @Override
    public User loadUserByUsername(String username){
        Users user = dao.getByUsername(username);
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        return new User(user.getUserEmail(),user.getPassword(),true,true,true,true,authorities);
    }

    public UserDaoImpl getDao() {
        return dao;
    }

    public void setDao(UserDaoImpl dao) {
        this.dao = dao;
    }
}
