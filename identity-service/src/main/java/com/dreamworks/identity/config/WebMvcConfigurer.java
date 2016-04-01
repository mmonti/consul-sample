package com.dreamworks.identity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * Created by mmonti on 3/30/16.
 */
@Configuration
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {

    private static final String CORS_MAPPING = "/**";
    private static final String CORS_ALLOWED_METHODS = "*";
    private static final String CORS_ALLOWED_HEADERS = "*";
    private static final String CORS_ALLOWED_ORIGINS = "*";

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        return new MappingJackson2HttpMessageConverter();
    }

    @Bean
    public Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder() {
        return new Jackson2ObjectMapperBuilder();
    }

    @Override
    public void configureMessageConverters(final List<HttpMessageConverter<?>> converters) {
        converters.add(mappingJackson2HttpMessageConverter());
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
