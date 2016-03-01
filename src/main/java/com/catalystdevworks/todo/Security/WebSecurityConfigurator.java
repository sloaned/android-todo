package com.catalystdevworks.todo.Security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 * Created by g on 2/24/16.
 */
//http://docs.spring.io/spring-security/site/docs/3.2.x/guides/form.html#setting-up-the-sample
//https://github.com/eugenp/tutorials/blob/master/spring-security-login-and-registration/src/main/java/org/baeldung/spring/SecSecurityConfig.java
@Configuration
@EnableWebSecurity
public class WebSecurityConfigurator extends WebSecurityConfigurerAdapter {
    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Autowired
    private TokenAuthenticationService tokenAuthenticationService;

    public WebSecurityConfigurator() {
        super(true);
    }

    // http://stackoverflow.com/a/28272347 for the differences
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .exceptionHandling().and()
            .anonymous().and()
            .servletApi().and()
            .headers().cacheControl().and()
            .authorizeRequests()

            //allow anonymous resource requests
            .antMatchers("/").permitAll()
            .antMatchers("/favicon.ico").permitAll()
            .antMatchers("/resources/**").permitAll()

            //allow anonymous POSTs to login
            .antMatchers(HttpMethod.POST, "/login.json").permitAll()

            //defined Admin only API area
            .antMatchers("/task/**").hasRole("USER")

            //all other request need to be authenticated
            .anyRequest().hasRole("USER").and()

            // custom JSON based authentication by POST of {"username":"<name>","password":"<password>"} which sets the token header upon authentication
            .addFilterBefore(new StatelessLoginFilter(tokenAuthenticationService, authenticationManager()), UsernamePasswordAuthenticationFilter.class)

            // custom Token based authentication based on the header previously given to the client
            .addFilterBefore(new StatelessAuthenticationFilter(tokenAuthenticationService), UsernamePasswordAuthenticationFilter.class);
    }
}
