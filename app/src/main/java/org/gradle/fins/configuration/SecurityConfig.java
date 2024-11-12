package org.gradle.fins.configuration;

import com.sun.org.apache.xpath.internal.operations.String;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig  {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
        return security.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(new AntPathRequestMatcher("/api/voucher/**")).hasAnyRole("USER")
                        .requestMatchers(new AntPathRequestMatcher("/api/user/**")).hasAnyRole("ADMIN")
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService()
    {
        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder().encode("user@123"))
                .roles("USER")
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin@123"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user,admin);
    }
}
