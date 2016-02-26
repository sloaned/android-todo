package com.catalystdevworks.todo.webservices;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
public class SpringSecurityConfigurer extends WebSecurityConfigurerAdapter{

    @Autowired
    private DataSource dataSource;

    public void setDatasource(DataSource datasource){
        this.dataSource = datasource;
    }

    /*@Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery(
                        "SELECT user_email, user_password FROM users WHERE user_email=?")
                .authoritiesByUsernameQuery(
                        "SELECT user_email, 'USER' FROM users WHERE user_email=?");

    } */

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception{

        auth.inMemoryAuthentication()
                .withUser("user")
                .password("root")
                .authorities("USER");
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                //.passwordEncoder(encoder())
                .usersByUsernameQuery("SELECT user_email, user_password, TRUE FROM users WHERE user_email=?")
                .authoritiesByUsernameQuery("SELECT user_email, 'USER' FROM users WHERE user_email=?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        super.configure(http);
       /* http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").failureUrl("/login?error")
                .usernameParameter("Email").passwordParameter("Password")
                .and()
                .logout().logoutSuccessUrl("/login?logout")
                .and()
                .exceptionHandling().accessDeniedPage("/403");
        */
        http.csrf().disable();
    }
    /*
    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    } */

}