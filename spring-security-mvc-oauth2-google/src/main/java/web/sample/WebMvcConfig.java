package web.sample;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Configuration
@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// Here, we provide a view that can be accessed by anyone (even not authenticated)
		registry.addViewController("/").setViewName("welcome");
		// Then, we provide a view that can *only* be accessed by an authenticated user
		registry.addViewController("/secured").setViewName("secured");
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/views/", ".jsp");
	}

	@Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {     
        converters.add(new MappingJackson2HttpMessageConverter());
        super.configureMessageConverters(converters);
    }
}
