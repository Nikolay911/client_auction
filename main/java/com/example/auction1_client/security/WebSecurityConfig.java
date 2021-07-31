package com.example.auction1_client.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.context.WebApplicationContext;

@Configuration
@EnableConfigurationProperties
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    OurUserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http

                .authorizeRequests().antMatchers("/client/**").authenticated()
                .antMatchers("/user").permitAll()
                .and().httpBasic()
                .and().csrf().disable();
    }

    @Override
    public void configure(AuthenticationManagerBuilder builder)
            throws Exception {

        builder.userDetailsService(userDetailsService);
        //builder.authenticationProvider(userDetailsService);
    }
}
