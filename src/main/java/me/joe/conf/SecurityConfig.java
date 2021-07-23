package me.joe.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import me.joe.conf.service.AuthService;

/**
 * The Class SecurityConfig.
 * 
 * <pre>
 * 
 * </pre>
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private AuthService authService;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(authService)
        .passwordEncoder(passwordEncoder());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http.formLogin()
        .and()
        .csrf()
        .and()
        .authorizeRequests()
        // 無限制
        .antMatchers(HttpMethod.GET, "/actuator")
        .permitAll()
        // 限 ADMIN
        .antMatchers(HttpMethod.GET, "/actuator/health")
        .hasRole("ADMIN")
        // 限 TEST
        .antMatchers(HttpMethod.GET, "/actuator/env")
        .hasRole("TEST")
        .anyRequest()
        .authenticated();
  }
}
