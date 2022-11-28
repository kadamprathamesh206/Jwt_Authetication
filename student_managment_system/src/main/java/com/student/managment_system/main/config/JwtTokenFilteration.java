package com.student.managment_system.main.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtTokenFilteration extends OncePerRequestFilter {
	@Autowired
	JwtUtil jwtUtil;
	
	@Autowired
	MyUserDetailService userDetails;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
	     
		
		String header=request.getHeader(HttpHeaders.AUTHORIZATION);
		if(!StringUtils.hasText(header) || !header.startsWith("Bearer")){
			filterChain.doFilter(request, response);
			return;
			
			
		}
		
		String token=  header.split(" ")[1];
		        if(!jwtUtil.validate(token)) {
		        	filterChain.doFilter(request, response);
		        	return;
		        }
		
		        
     /*
      * get user identity and set into spring security context
      */
		    UserDetails user=userDetails.loadUserByUsername(jwtUtil.getUsername(token));
		    UsernamePasswordAuthenticationToken authetication =new UsernamePasswordAuthenticationToken(user,null, user.getAuthorities());
		    authetication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		    SecurityContextHolder.getContext().setAuthentication(authetication);
		    filterChain.doFilter(request, response);
	}

}
