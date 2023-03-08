package com.example.microgrowth.Security;

import com.example.microgrowth.Filtre.CustomAuthenticationFilter;
import com.example.microgrowth.Filtre.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import static org.springframework.security.config.http.SessionCreationPolicy.*;
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/login");
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(STATELESS);
        http.authorizeHttpRequests().antMatchers("/login/**","/token/refresh/**").permitAll();
//        //juste pour le test
//        http.authorizeHttpRequests().antMatchers(HttpMethod.POST,"/**").permitAll();
//        http.authorizeHttpRequests().antMatchers(HttpMethod.GET,"/**").permitAll();

        http.authorizeHttpRequests().antMatchers(HttpMethod.GET,"/user/**").hasAnyAuthority("[ROLE_USER]");
        http.authorizeHttpRequests().antMatchers(HttpMethod.POST,"/user/**").hasAnyAuthority("[ROLE_USER]");

        http.authorizeHttpRequests().antMatchers(HttpMethod.GET,"/admin/**").hasAnyAuthority("[ROLE_ADMIN]");
        http.authorizeHttpRequests().antMatchers(HttpMethod.POST,"/admin/**").hasAnyAuthority("[ROLE_ADMIN]");
        http.authorizeHttpRequests().anyRequest().authenticated();
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }
}