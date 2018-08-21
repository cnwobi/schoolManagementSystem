package com.chukanwobi.schoolmanagementsystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SpringConfig extends WebSecurityConfigurerAdapter {



    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	 auth.inMemoryAuthentication().withUser("user").password("{noop}password").roles("USER");
    }
}
