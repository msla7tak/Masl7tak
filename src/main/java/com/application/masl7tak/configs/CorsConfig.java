package com.application.masl7tak.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//
//import io.micrometer.common.lang.NonNullApi;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import org.springframework.web.filter.CorsFilter;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//
@Configuration
public class CorsConfig  {
        @Bean
        public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedMethods("GET", "PUT", "POST", "DELETE", "PATCH", "OPTIONS")
                        .exposedHeaders("Content-Disposition").allowCredentials(true).allowedOriginPatterns("*");
            }
        };

    }
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins("http://127.0.0.1:5500") // Allow requests from this origin
//                .allowedMethods("GET", "POST", "PUT", "DELETE")
//                .allowedHeaders("*");
//    }
}

//@Configuration
//@EnableWebMvc
//@NonNullApi
//public class CorsConfig  implements WebMvcConfigurer {
//
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/api/**")  // Adjust the mapping to match your API endpoints
//                .allowedOrigins("http://127.0.0.1:5500")
//                .allowedMethods("GET", "POST", "PUT", "DELETE")
//                .allowedHeaders("*");
//    }
//        @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/api/**").allowedMethods("GET", "PUT", "POST", "DELETE", "PATCH", "OPTIONS")
//                        .exposedHeaders("Content-Disposition").allowCredentials(true).allowedOriginPatterns("*");
//            }
//        };
//
//    }
//}
//
////    }
