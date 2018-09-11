package com.lenovo.tripnote.webchat.interceptor;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class JwtTokenCfg {

    @Bean
    public FilterRegistrationBean jwtFilter() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new JwtTokenFilter());
        registrationBean.addUrlPatterns("/token/*");

        return registrationBean;
    }

}
