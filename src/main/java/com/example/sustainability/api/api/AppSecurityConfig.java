package com.example.sustainability.api.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppSecurityConfig implements WebMvcConfigurer {



    @Autowired
    UserSecurityInterceptor userSecurityInterceptor;

    /**
     * Add Spring MVC lifecycle interceptors for pre- and post-processing of
     * controller method invocations and resource handler requests.
     * Interceptors can be registered to apply to all requests or be limited
     * to a subset of URL patterns.
     */
    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(userSecurityInterceptor)
                .excludePathPatterns("/**");
//                .excludePathPatterns("/reset/**");
    }

}
