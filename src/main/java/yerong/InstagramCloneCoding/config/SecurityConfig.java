package yerong.InstagramCloneCoding.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfig{
    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception{
        return http
                .csrf(csrfConfig ->
                        csrfConfig.disable())
                .authorizeHttpRequests(authorizationRequest ->
                        authorizationRequest
                                .requestMatchers(
                                        AntPathRequestMatcher.antMatcher("/"),
                                        AntPathRequestMatcher.antMatcher("/user/**"),
                                        AntPathRequestMatcher.antMatcher("/image/**"),
                                        AntPathRequestMatcher.antMatcher("/subscribe/**"),
                                        AntPathRequestMatcher.antMatcher("/comment/**")
                                ).authenticated()
                                .anyRequest().permitAll()
                )
                .formLogin(formConfig ->
                        formConfig.loginPage("/auth/signin")
                                .defaultSuccessUrl("/"))
                .build();
    }
}
