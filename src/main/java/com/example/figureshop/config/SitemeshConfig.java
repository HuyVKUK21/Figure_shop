package com.example.figureshop.config;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import jakarta.servlet.Filter;

@Configuration
public class SitemeshConfig {

	  @Bean
	    public FilterRegistrationBean<Filter> siteMeshFilter() {
	        FilterRegistrationBean<Filter> filter = new FilterRegistrationBean<>();
	        filter.setFilter(new ConfigurableSiteMeshFilter() {
	            @Override
	            protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
	                builder.setMimeTypes("text/html") 
	                       .addDecoratorPath("/user/*", "web.jsp")
//	                       .addDecoratorPath("/*", "/WEB-INF/decorators/web.jsp")
	                       .addExcludedPath("/api/*"); 
	            }
	        });
	        filter.addUrlPatterns("/*");
	        filter.setName("sitemesh");
	        return filter;
	    }
}
