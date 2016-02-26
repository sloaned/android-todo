package com.catalystdevworks.todo.Security;


import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


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
            .authorizeRequests()
                .antMatchers("/resources/**","/registration*").permitAll() //allow resources and registration
                .anyRequest().permitAll()
                .and().userDetailsService(customUserDetailsService)
            .formLogin()
                .loginPage("/login").defaultSuccessUrl("/homepage.html")
                .failureUrl("/login?error").permitAll()
            .and().csrf().disable();
    }

}
