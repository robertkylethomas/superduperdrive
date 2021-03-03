package com.udacity.jwdnd.course1.cloudstorage.config;

import com.udacity.jwdnd.course1.cloudstorage.services.AuthenticationService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  private final AuthenticationService authenticationService;

  public SecurityConfig(AuthenticationService authenticationService) {
    this.authenticationService = authenticationService;
  }

  // Set up the authenticationService as the auth provider
  @Override
  protected void configure(AuthenticationManagerBuilder auth) {
    auth.authenticationProvider(this.authenticationService);
  }

  // Set up the authenticationService as the auth provider
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // Permit all requests all from signup, login pages and CSS and JS files
    http
      .authorizeRequests()
      .antMatchers("/signup/**", "/css/**", "/js/**").permitAll()
      .anyRequest().authenticated();

    // set the login form
    http
      .formLogin()
      .loginPage("/login")
      .defaultSuccessUrl("/home", true);

    http
      .logout()
      .invalidateHttpSession(true)
      .clearAuthentication(true)
      .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
      .logoutSuccessUrl("/login").permitAll();

  }
}
