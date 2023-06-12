package dev.dex.fixerprodockerpostgres.security;

import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.provisioning.*;
import org.springframework.security.web.*;

@Configuration
public class SecurityConfigTest {
    @Bean
    public UserDetailsManager userDetailsManagerTest() {
        UserDetails dexter = User.withUsername("dexter")
                .password("{noop}test123")
                .authorities("ROLE_CLIENT", "ROLE_ADMIN")
                .build();
        UserDetails john = User.withUsername("john")
                .password("{noop}test123")
                .authorities("ROLE_CLIENT")
                .build();

        return new InMemoryUserDetailsManager(dexter, john);
    }

    @Bean
    public SecurityFilterChain filterChainTest(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(configurer ->
                        configurer
                                .requestMatchers("/fixerpro/admin/**").hasRole("ADMIN")
                                .requestMatchers("/fixerpro/checkout/**").hasRole("CLIENT")
                                .anyRequest().permitAll()
                )
                .csrf(csrf -> csrf.disable())
                .build();
    }
}
