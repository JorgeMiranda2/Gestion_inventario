package com.control_inventario.security.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {



    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedHeaders("*", "Authorization")
                        .allowedOrigins("http://localhost:8080","http://localhost:4200", "http://172.16.77.34:3000","http://localhost:5173","http://localhost:19006", "http://localhost",
                                "http://172.16.77.34:19006", "http://172.16.77.34")
                        .allowedMethods("*")
                        .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials")
                        .allowCredentials(true)
                        .maxAge(3600);
            }

        };
    }
}
