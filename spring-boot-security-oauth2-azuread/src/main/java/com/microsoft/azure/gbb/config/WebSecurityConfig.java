package com.microsoft.azure.gbb.config;

import com.microsoft.azure.gbb.security.*;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoRestTemplateCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableOAuth2Sso // enabling login from OAuth2 (AzureAD)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
	public UserInfoRestTemplateCustomizer azureAdUserInfoRestTemplate() {
		return new AzureADUserInfoRestTemplateCustomizer();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
	  http
		.antMatcher("/**")
		.authorizeRequests()
		  .antMatchers("/", "/login**", "/webjars/**")
		  .permitAll()
		.anyRequest()
		  .authenticated()
		.and()
		  .logout().logoutSuccessUrl("/").permitAll()
		.and()
		  .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
	}
}