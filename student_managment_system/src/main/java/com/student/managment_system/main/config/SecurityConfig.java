package com.student.managment_system.main.config;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity


public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
    MyUserDetailService myUserDetailssevice;
	
	@Autowired
	JwtTokenFilteration jwtTokenFilteration;
	
	
     @SuppressWarnings("unchecked")
	@Override
    protected void configure(HttpSecurity http) throws Exception {
    	 http=  http.csrf().disable();
    	  http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    	  http.authorizeRequests().antMatchers(HttpMethod.POST, "/student/**").permitAll()
    	  .antMatchers("/swagger-ui.html").permitAll()
    	  .anyRequest()
    	  .authenticated();
    	  http.addFilterBefore(jwtTokenFilteration, UsernamePasswordAuthenticationFilter.class);
    }
     
     
     
     @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.userDetailsService(myUserDetailssevice);
    }
     
     
     
     @Bean
     public PasswordEncoder passwordEncoder() {
    	 
    	 return new BCryptPasswordEncoder();
    	 
    	 
    	 
     }
     
   
     @Bean
     public AuthenticationManager authenticManagerBean() throws Exception {
    	 return super.authenticationManagerBean();
     }
}
