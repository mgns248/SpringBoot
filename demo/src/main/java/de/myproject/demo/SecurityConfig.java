package de.myproject.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    SecurityFilterChain filters(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authz -> authz
                .requestMatchers("/test").permitAll()
                .requestMatchers("/result").permitAll()
                .requestMatchers("/error").permitAll()
                .anyRequest().authenticated());

        http.formLogin(withDefaults());
        
        return http.build();
    }

    @Bean
    UserDetailsService UserDetailsService() throws Exception{
        UserDetails userDetails = User.builder()
                .username("user")
                .password("password")
                .authorities("ROLE_USER")
                .build();
        return new InMemoryUserDetailsManager(userDetails);
    }
}
