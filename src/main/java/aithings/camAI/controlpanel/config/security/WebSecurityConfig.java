package aithings.camAI.controlpanel.config.security;

import aithings.camAI.controlpanel.config.security.jwt.AuthEntryPointJwt;
import aithings.camAI.controlpanel.config.security.jwt.TokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig {

    final private TokenFilter tokenFilter;
    private final AuthEntryPointJwt authEntryPointJwt;

    public WebSecurityConfig(TokenFilter tokenFilter,AuthEntryPointJwt authEntryPointJwt) {
        this.tokenFilter = tokenFilter;
        this.authEntryPointJwt = authEntryPointJwt;
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity)throws Exception{
        httpSecurity.headers().frameOptions().disable().cacheControl().disable()
               .and()
                .exceptionHandling(exception->exception.authenticationEntryPoint(authEntryPointJwt))
               .authorizeHttpRequests()
               .antMatchers("/api/v1/auth/token").permitAll()
               .anyRequest().authenticated()
               .and()
               .csrf().disable()
               .addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }






}
