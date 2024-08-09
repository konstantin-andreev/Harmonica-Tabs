package org.harmonicatabs.config;

import org.harmonicatabs.repository.UserRepository;
import org.harmonicatabs.service.session.HarmonicaTabsUserDetailsService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final HarmonicaTabsUserDetailsService detailsService;

    public SecurityConfiguration(HarmonicaTabsUserDetailsService detailsService) {
        this.detailsService = detailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(
                        authorizeRequests ->
                                authorizeRequests
                                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                                        .requestMatchers("/", "/user/login", "/user/register", "/user/login-error").permitAll()
                                        .anyRequest().authenticated()
                )
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/user/login")
                                .usernameParameter("username")
                                .passwordParameter("password")
                                .defaultSuccessUrl("/home",true)
                                .failureUrl("/user/login-error")
                )
                .logout(logout ->
                        logout
                                .logoutUrl("/user/logout")
                                .logoutSuccessUrl("/")
                                .invalidateHttpSession(true)
                )
                .build();
    }
}
