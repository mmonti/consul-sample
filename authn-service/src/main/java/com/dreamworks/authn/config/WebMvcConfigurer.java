package com.dreamworks.authn.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * Created by mmonti on 3/30/16.
 */
@Configuration
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {

    private static final String CORS_MAPPING = "/**";
    private static final String CORS_ALLOWED_METHODS = "*";
    private static final String CORS_ALLOWED_HEADERS = "*";
    private static final String CORS_ALLOWED_ORIGINS = "*";

    @Autowired
    private MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter;

    @Override
    public void configureMessageConverters(final List<HttpMessageConverter<?>> converters) {
        converters.add(mappingJackson2HttpMessageConverter);
        super.configureMessageConverters(converters);
    }

    @Override
    public void addCorsMappings(final CorsRegistry registry) {
        registry
                .addMapping(CORS_MAPPING)
                .allowedMethods(CORS_ALLOWED_METHODS)
                .allowedHeaders(CORS_ALLOWED_HEADERS)
                .allowedOrigins(CORS_ALLOWED_ORIGINS);
    }
}
