package com.catalystdevworks.todo.controllers;

import com.catalystdevworks.todo.Security.CustomAuthenticationProvider;
import com.catalystdevworks.todo.Security.CustomUserDetailsService;
import com.catalystdevworks.todo.Security.TokenHandler;
import com.catalystdevworks.todo.entities.LoginRequest;
import com.catalystdevworks.todo.entities.Token;
import com.catalystdevworks.todo.services.impl.UsersServiceImpl;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by g on 2/26/16.
 */
/*@RestController
public class LoginController {
    @Autowired
    CustomAuthenticationProvider authenticationManager;

    @Autowired
    CustomUserDetailsService usersService;
    @Autowired
    private TokenHandler th;


    @RequestMapping(value="/login.json", method = RequestMethod.POST)
    public @ResponseBody Token mosLogin(@RequestBody LoginRequest loginRequest, HttpServletRequest request) {
        Token tk = null;
////        System.out.println(String.format("%s  %s",loginRequest.getUsername(),loginRequest.getPassword()));
//
        //create an authentication token from username and password from the client
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());

        token.setDetails(new WebAuthenticationDetails(request));

        //use the authManager to attempt authenticate
        Authentication auth = authenticationManager.authenticate(token);

        //set the SecurityContext to to the auth state
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(auth);

        //if authenticated
        if(auth.isAuthenticated()){
            HttpSession session = request.getSession(true);
            //add the context to the cookie
            session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);

            //or generate a token
            tk = new Token(th.createTokenForUser(usersService.loadUserByUsername(loginRequest.getUsername())));
            System.out.println("Auth");
        }else{
            SecurityContextHolder.getContext().setAuthentication(null);

            System.out.println("cred not right");

        }
       //return the token
        return tk;
    }
}*/

