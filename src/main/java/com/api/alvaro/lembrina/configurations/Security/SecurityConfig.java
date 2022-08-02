package com.api.alvaro.lembrina.configurations.security;
/*
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Override
    public void configure(HttpSecurity httpSec) throws Exception{
        httpSec.csrf().disable()
                    .authorizeHttpRequests()
                    .antMatchers(HttpMethod.GET, "/Usuarios")
                    .permitAll()
                    .anyRequest().authenticated().and().cors();
        httpSec.addFilterBefore(new SecurityFilter(), UsernamePasswordAuthenticationFilter.class);
    }
    
}
*/