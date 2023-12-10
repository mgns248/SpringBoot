package de.myproject.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;  
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.boot.CommandLineRunner;

@Configuration
@EnableWebSecurity
class SecurityConfig {
    
    @Bean
    SecurityFilterChain filters(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authz -> authz
                .requestMatchers("/").permitAll()
                .requestMatchers("/result").permitAll()
                .requestMatchers("/index.css").permitAll()
                .requestMatchers("/error").permitAll()
                .anyRequest().denyAll());

        http.formLogin(form -> form
                .loginPage("/login")
                .permitAll());

        http.csrf(csrf -> csrf.disable());
        
        return http.build();
    }

    @Bean
    UserDetailsService userDetailsService(UserAccountRepository userAccountRepo){
        return new UserAccountDetailsService(userAccountRepo);
    }

    @Bean
    CommandLineRunner createDefaultUsers(UserAccountRepository userAccountRepo) {
        return args -> {
            boolean userExists = userAccountRepo.findByUsername("user").isPresent();
            if(userExists){
                return;
            }
            var newUser = new UserAccount("user", passwordEncoder().encode("password"), "ROLE_USER");
            userAccountRepo.save(newUser);
        };
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

   
    
}

