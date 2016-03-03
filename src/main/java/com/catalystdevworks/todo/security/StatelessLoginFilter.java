package com.catalystdevworks.todo.security;

import com.catalystdevworks.todo.entities.LoginRequest;
import com.catalystdevworks.todo.entities.UserAuthentication;
import com.catalystdevworks.todo.services.impl.TokenAuthenticationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Login Filter intercepts request to /login and looks for a LoginRequest object
 * and attempts authentication
 */
class StatelessLoginFilter extends AbstractAuthenticationProcessingFilter {

	private final TokenAuthenticationService tokenAuthenticationService;

	protected StatelessLoginFilter(TokenAuthenticationService tokenAuthenticationService, AuthenticationManager authManager) {

		super(new AntPathRequestMatcher("/login"));

		this.tokenAuthenticationService = tokenAuthenticationService;
		setAuthenticationManager(authManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		System.out.println("here's the request " + request.getHeaderNames());

		//Parse request
		final LoginRequest user = new ObjectMapper().readValue(request.getInputStream(), LoginRequest.class);

		System.out.println("user: " + user.getUsername() + " " + user.getPassword());

		//create a Token with the username and password from the client
		final UsernamePasswordAuthenticationToken loginToken = new UsernamePasswordAuthenticationToken
				(user.getUsername(), user.getPassword());
		//attempt authentication and return the result
		return getAuthenticationManager().authenticate(loginToken);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
											FilterChain chain, Authentication authentication) throws IOException, ServletException {

		// Lookup the complete User object from the database and create an Authentication for it
		final User authenticatedUser = (User) authentication.getPrincipal();//userDetailService.loadUserByUsername(authentication.getName());

		final UserAuthentication userAuthentication = new UserAuthentication(authenticatedUser);

		// Add the custom token as HTTP header to the response
		tokenAuthenticationService.addAuthentication(response, userAuthentication);

		// Add the authentication to the security context
		SecurityContextHolder.getContext().setAuthentication(userAuthentication);


		System.out.println("Successful authentication!");


	}
}