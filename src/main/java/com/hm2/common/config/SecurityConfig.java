package com.hm2.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.hm2.ums.services.UserDetailsServiceCustom;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsServiceCustom userDetailsServiceCustom;

	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {

		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		daoAuthenticationProvider.setUserDetailsService(userDetailsServiceCustom);
		return daoAuthenticationProvider;
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(daoAuthenticationProvider());
	}

	private static final String[] AUTH_WHITELIST = {

			//Bill
			"/bill/**",

			// -- Upload File
			"/api/upload",
			"/api/uploadMultipartFile",
			"/api/download/**",

			// -- Swagger UI
			"/swagger-resources/**", 
			"/swagger-ui.html",
			"/v2/api-docs", 
			"/webjars/**",
			// -- Ums
			"/api/ums/user/generate-password/**",
			"/api/ums/user/generate-user",
			"/api/ums/user/upload",
			"/api/ums/user/register",
			"/api/ums/user/forgot-password",
			"/api/ums/user/find-forgot-password-key/**",
			"/api/ums/user/change-password",
			"/api/ums/user/mail",
			//-- Other
			"/monitoring",
			"/system/**",
			"/api/lookup/**",
			"/authenticate",
			"/rest-ws/",
			"/rest-ws/covid-19",
			"/soap-ws",
			"/login",
			"/",
			"/test-email",
			"/js/**",
            "/css/**",
            "/img/**",
			"/fonts/**",
            "/webjars/**",
	};

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
//				.antMatchers("/api/ums/user/").hasRole("ADMIN")
//				.antMatchers("/test/user").hasRole("USER")
//				.antMatchers("/test/all").permitAll()
//				.antMatchers("/api/user/test").permitAll()
		   		.antMatchers(AUTH_WHITELIST).permitAll()
				.anyRequest().authenticated()
				.and()
				.exceptionHandling().accessDeniedPage("/api/user/403")
				.and()
				.formLogin().loginPage("/login").permitAll()
				.and()
				.logout().permitAll()
				.and()
				.exceptionHandling()
				.authenticationEntryPoint(jwtAuthenticationEntryPoint)
				.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.csrf().disable();
		http.headers().frameOptions().disable();
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		http.cors().and();

	}

}
