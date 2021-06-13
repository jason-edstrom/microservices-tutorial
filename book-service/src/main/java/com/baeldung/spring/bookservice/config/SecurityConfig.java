package com.baeldung.spring.bookservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.httpBasic().disable().authorizeRequests().antMatchers(HttpMethod.GET, "/books").permitAll()
        .antMatchers(HttpMethod.POST, "/books/*").hasRole("ADMIN")
        .antMatchers(HttpMethod.GET, "/books/*").permitAll()
        .antMatchers(HttpMethod.DELETE, "/books/*").hasRole("ADMIN")
        .antMatchers(HttpMethod.PUT, "/books/*").permitAll()
        .antMatchers(HttpMethod.PATCH, "/books/*").hasRole("ADMIN")
        .anyRequest().authenticated()
        .and()
        .csrf()
        .disable();
  }
}
