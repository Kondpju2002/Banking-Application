package com.cts.hcbanking.securityconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.cts.hcbanking.service.CustomerDetailsService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	@Autowired
	private CustomerDetailsService customerDetailsService;

	@Bean
	public AuthenticationProvider authProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

		provider.setUserDetailsService(customerDetailsService);
		provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
//		provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());

		return provider;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.csrf(customizer -> customizer.disable());
		http.authorizeHttpRequests(request -> request.requestMatchers("/signin").permitAll()
				.anyRequest().authenticated());
//		http.formLogin(Customizer.withDefaults());
		http.httpBasic(Customizer.withDefaults());
		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		return http.build();
	}

//	@Bean
//	public UserDetailsService userDetailsService() {  ////This only works with static values.
//		
//		UserDetails user = User
//				.withDefaultPasswordEncoder()
//				.username("vikas")
//				.password("v@123")
//				.roles("USER")
//				.build();
//		
//	
//	UserDetails admin = User
//			.withDefaultPasswordEncoder()
//			.username("admin")
//			.password("admin@123")
//			.roles("ADMIN")
//			.build();
//	
//	
//return new InMemoryUserDetailsManager(user,admin);
//
//}
}
