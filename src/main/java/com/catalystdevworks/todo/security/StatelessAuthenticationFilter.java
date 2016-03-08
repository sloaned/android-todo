package com.catalystdevworks.todo.security;

import com.catalystdevworks.todo.services.impl.TokenAuthenticationService;
import com.google.common.base.Preconditions;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * grabs a token from the header parses it and then sets the SecurityContext to the curent user
 */
public class StatelessAuthenticationFilter extends GenericFilterBean {

    private final TokenAuthenticationService tokenAuthenticationService;

    public StatelessAuthenticationFilter(TokenAuthenticationService tokenAuthenticationService) {

        System.out.println("well here we are in StatelessAuthenticationFilter");
        this.tokenAuthenticationService = Preconditions.checkNotNull(tokenAuthenticationService);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        System.out.println("in the authentication filter");
        System.out.println("the request type = " + request.getContentType());
        SecurityContextHolder.getContext().setAuthentication(
                tokenAuthenticationService.getAuthentication((HttpServletRequest) request)
        );
        SecurityContextHolder.getContext().getAuthentication();
        System.out.println("authentication gotted");
        //continue chain
        filterChain.doFilter(request, response);
        //after the chain clear the context
        SecurityContextHolder.getContext().setAuthentication(null);
        System.out.println("authentication cleared");
    }
}