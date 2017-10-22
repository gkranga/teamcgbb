package com.microsoft.azure.gbb;

import com.microsoft.azure.gbb.security.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoRestTemplateCustomizer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableOAuth2Sso
public class SocialApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialApplication.class, args);
	}

    @Bean
	public UserInfoRestTemplateCustomizer azureAdUserInfoRestTemplate() {
		return new AzureADUserInfoRestTemplateCustomizer();
	}
}