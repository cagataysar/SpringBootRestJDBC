package com.garanti.SpringBootRestJDBC.config;

import com.garanti.SpringBootRestJDBC.security.JWTAuthenticationFilter;
import com.garanti.SpringBootRestJDBC.security.JWTAuthorizationFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        // sadece post işlemleri authenticated yapılabilir
        // http.authorizeRequests().antMatchers(HttpMethod.POST).authenticated();
        // geri kalan bütün endpoint 'ler permit durumunda
        // http.authorizeHttpRequests().anyRequest().permitAll();
        // save işlemini sadece admin rolündekiler yapsın gibi
        // http.authorizeRequests().antMatchers("proposal/save").hasRole("USER");
        // belki de burada belirtmek yerine controller 'da @Secured kullanınılabilir
        // http.authorizeRequests().antMatchers(HttpMethod.DELETE).hasRole("ADMIN");
        // http.authorizeRequests().antMatchers("/error").permitAll();
        // http.csrf().disable().authorizeRequests().anyRequest().authenticated();
        http.csrf().disable();
        http.addFilter(new JWTAuthenticationFilter(authenticationManager()));
        http.addFilter(new JWTAuthorizationFilter(authenticationManager()));
    }
}
