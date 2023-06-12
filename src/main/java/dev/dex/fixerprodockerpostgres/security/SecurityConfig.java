package dev.dex.fixerprodockerpostgres.security;

import dev.dex.fixerprodockerpostgres.oauth2.*;
import dev.dex.fixerprodockerpostgres.service.*;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.crypto.password.*;
import org.springframework.security.provisioning.*;
import org.springframework.security.web.*;
import org.springframework.security.web.authentication.*;

import javax.sql.*;

@Configuration
public class SecurityConfig {
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "select username, password, enabled from accounts where username=?");
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("select username, authority from authorities where username=?");
        return jdbcUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, CustomOAuth2UserService oauthUserService,
                                           AccountService accountService, RecaptchaService recaptchaService) throws Exception {
        return http
                .authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers("/fixerpro/admin/**").hasRole("ADMIN")
                        .requestMatchers("/fixerpro/checkout/**").hasRole("CLIENT")
                        .anyRequest().permitAll()
                )
                .formLogin(form ->
                        form
                                .loginPage("/fixerpro/my-login")
                                .loginProcessingUrl("/authenticate-client")
                                .permitAll()
                )
                .oauth2Login(oauth2 ->
                        oauth2
                                .loginPage("/fixerpro/my-login")
                                .userInfoEndpoint(userInfoEndpointConfig ->
                                        userInfoEndpointConfig
                                                .userService(oauthUserService))
                                .successHandler((request, response, authentication) -> {
                                    CustomOAuth2User oauthUser = (CustomOAuth2User) authentication.getPrincipal();
                                    accountService.processOAuthPostLogin(oauthUser.getUsername(), oauthUser.getEmail());
                                    response.sendRedirect("/fixerpro/engine-oil");
                                })
                )
                .logout(logout ->
                        logout.permitAll()
                )
                .csrf(csrf -> csrf.disable())
                .addFilterBefore(new RecaptchaFilter(recaptchaService), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

}
