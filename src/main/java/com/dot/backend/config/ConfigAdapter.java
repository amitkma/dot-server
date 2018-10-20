package com.dot.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class ConfigAdapter extends WebMvcConfigurerAdapter {
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public FilterRegistrationBean myFilterRegistrationBean() {
        FilterRegistrationBean regBean = new FilterRegistrationBean();
        regBean.setFilter(jwtAuthenticationFilter);
        regBean.addUrlPatterns("/dot/*");

        return regBean;
    }
}
