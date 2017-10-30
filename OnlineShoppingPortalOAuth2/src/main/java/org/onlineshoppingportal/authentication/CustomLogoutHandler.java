package org.onlineshoppingportal.authentication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;

public class CustomLogoutHandler implements LogoutHandler {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

        this.logger.info("[DEBUG] CustomLogoutHandler called");
        // business logic here
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){    
           new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        SecurityContextHolder.getContext().setAuthentication(null);        
    }
}