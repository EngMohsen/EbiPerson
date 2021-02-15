package com.ebi.person.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class EbiBasicAuthSecurity extends WebSecurityConfigurerAdapter {
	/*
	 * Disable csrf 
	 * allow URIs from authentication 
	 * enabled basic authentication configuration
	 * */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		// Disable csrf
		http.csrf().disable();
		http.headers().frameOptions().disable();
		http.authorizeRequests((requests) -> {
			// Allow any request with Options method for front end application
			requests.antMatchers("/h2-console/**",
					 "/v2/api-docs",
	                 "/configuration/ui",
	                 "/swagger-resources/**",
	                 "/configuration/security",
	                 "/swagger-ui.html",
	                 "/webjars/**").permitAll();
			requests.antMatchers(HttpMethod.OPTIONS, "/**").permitAll();
			requests.anyRequest().authenticated();
		});
		// Disable formLogin and enable basic auth
		// http.formLogin();
		http.httpBasic();
	}

}
