package com.godigit.taskAppivation.config;

import com.godigit.taskAppivation.util.TokenUtility;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Bean
    public TokenUtility tokenUtility(){
        return new TokenUtility();
    }
}
