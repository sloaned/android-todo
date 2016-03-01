package com.catalystdevworks.todo.Security;

import com.catalystdevworks.todo.entities.Users;
import com.catalystdevworks.todo.services.impl.UsersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by g on 2/26/16.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService{

    public UsersServiceImpl getUsersServiceImpl() {
        return usersServiceImpl;
    }

    public void setUsersServiceImpl(UsersServiceImpl usersServiceImpl) {
        this.usersServiceImpl = usersServiceImpl;
    }

    @Autowired
    private UsersServiceImpl usersServiceImpl;


    @Override
    public User loadUserByUsername(String username){
        Users user = usersServiceImpl.getByUsername(username);
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        return new User(user.getUserEmail(),user.getPassword(),true,true,true,true,authorities);
    }

}
