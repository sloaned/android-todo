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
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan(basePackageClasses = CustomUserDetailsService.class)
public class WebSecurityConfigurator extends WebSecurityConfigurerAdapter {
//    @Autowired
//    private DataSource datasource;
    @Autowired
    CustomUserDetailsService customUserDetailsService;
    @Autowired
    SuccessHandler successHandler;
    private final TokenAuthenticationService tokenAuthenticationService;
    @Autowired
    private CustomUserDetailsService userService;

    public WebSecurityConfigurator() {
        super(true);
        tokenAuthenticationService = new TokenAuthenticationService("tooManySecrets", userService);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       // JdbcUserDetailsManager customUserDetailsService = new JdbcUserDetailsManager();
        auth.userDetailsService(customUserDetailsService);

     //   customUserDetailsService.setDataSource(datasource);
      //  customUserDetailsService.setAuthenticationManager(null);
//        PasswordEncoder encoder = new BCryptPasswordEncoder();
//        auth.jdbcAuthentication().dataSource(datasource).passwordEncoder(encoder)
//        .usersByUsernameQuery("select user_email, user_password, true from users where user_email=?")
//        .authoritiesByUsernameQuery("SELECT user_email, 'USER' FROM users WHERE user_email=?");

    }

    // http://stackoverflow.com/a/28272347 for the differences
    @Override
    public void configure(WebSecurity web) throws Exception {

        super.configure(web);
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

                //allow anonymous GETs to API
                .antMatchers(HttpMethod.GET, "/api/**").permitAll()

                //defined Admin only API area
                .antMatchers("/admin/**").hasRole("ADMIN")

                //all other request need to be authenticated
                .anyRequest().hasRole("USER").and()

                // custom JSON based authentication by POST of {"username":"<name>","password":"<password>"} which sets the token header upon authentication
                .addFilterBefore(new StatelessLoginFilter("/login.json", tokenAuthenticationService, userService, authenticationManager()), UsernamePasswordAuthenticationFilter.class)

                // custom Token based authentication based on the header previously given to the client
                .addFilterBefore(new StatelessAuthenticationFilter(tokenAuthenticationService), UsernamePasswordAuthenticationFilter.class);

//            .authorizeRequests()
//                .antMatchers("/resources/**","/registration*").permitAll() //allow resources and registration
//                .anyRequest().permitAll()
//                .and().addFilterBefore(new StatelessAuthenticationFilter(tokenAuthenticationService), UsernamePasswordAuthenticationFilter.class);
//                .userDetailsService(customUserDetailsService)
//            .formLogin().successHandler(successHandler)
//                .loginPage("/login").defaultSuccessUrl("/homepage.html")
//                .failureUrl("/login?error").permitAll()
     //       ;

//        .exceptionHandling().and()
//                .anonymous().and()
//                .servletApi().and()
//                .headers().cacheControl().and()
//                .authorizeRequests()
//
//                // Allow anonymous resource requests
//                .antMatchers("/").permitAll()
//                .antMatchers("/favicon.ico").permitAll()
//                .antMatchers("/**/*.html").permitAll()
//                .antMatchers("/**/*.css").permitAll()
//                .antMatchers("/**/*.js").permitAll()
//
//                // Allow anonymous logins
//                .antMatchers("/auth/**").permitAll()
//
//                // All other request need to be authenticated
//                .anyRequest().authenticated().and()
//
//                // Custom Token based authentication based on the header previously given to the client
//                .addFilterBefore(new StatelessAuthenticationFilter(tokenAuthenticationService), UsernamePasswordAuthenticationFilter.class);

    }

}
