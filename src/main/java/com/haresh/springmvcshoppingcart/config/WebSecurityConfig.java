/**
 * Author: Haresh Shaha
 * Date: 21-Nov-2020 11:49:24 am
 */

package com.haresh.springmvcshoppingcart.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	MyDBAuthenticationService myDBAauthenticationService;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// For User in database.
		auth.userDetailsService(myDBAauthenticationService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();

		// The pages requires login as EMPLOYEE or MANAGER.
		// If no login, it will redirect to /login page.
		http.authorizeRequests().antMatchers("/orderList", "/order", "/accountInfo")
				.access("hasAnyRole('ROLE_EMPLYEE', 'ROLE_MANAGER')");

		// For MANAGER only.
		http.authorizeRequests().antMatchers("/product").access("hasRole('ROLE_MANAGER')");

		// When the user has logged in as XX.
		// But access a page that requires role YY,
		// AccessDeniedException will throw.
		http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");

		// Config for Login Form
		http.authorizeRequests().and().formLogin().loginProcessingUrl("/j_spring_security_check").loginPage("/login")
				.defaultSuccessUrl("/accountInfo").failureUrl("/login?error=true").usernameParameter("userName")
				.passwordParameter("password").and().logout().logoutUrl("/logout").logoutSuccessUrl("/");

	}

}
