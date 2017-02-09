package edu.hanoi.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;

/**
 * Created by trungdovan on 12/5/16.
 */
@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppSecurityConfigurer extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(customAuthenticationProvider());
//		auth.inMemoryAuthentication().withUser("nguoidung").password("123456").roles("USER")
//				.and().withUser("quantri").password("654321").roles("ADMIN", "USER")
//				.and().withUser("admin").password("123").roles("ADMIN");
	}

	@Bean
	protected AuthenticationProvider customAuthenticationProvider() {
		AuthenticationProvider provider = new HnUserAuthProvider();
		return provider;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.csrf().disable()
				.authorizeRequests().antMatchers("/").hasRole("USER").anyRequest().authenticated().and().httpBasic();
		//http.authorizeRequests().antMatchers("/").hasRole("USER").anyRequest().authenticated().and().formLogin();
	}
}
