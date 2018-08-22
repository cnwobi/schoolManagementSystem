package com.chukanwobi.schoolmanagementsystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)

public class SpringConfig extends WebSecurityConfigurerAdapter {

    private static final String[] PUBLIC_MATCHERS = {
            "/webjars/**",
            "/css/**",
            "/js/**",
            "/images/**",
            "/login",
            "/about/**",
            "/contact/**",
            "/error/**/*",
            "/h2-console/**",
            "/signup"
    };

    @Override

    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(PUBLIC_MATCHERS).permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/student/**").hasAnyRole("STUDENT","ADMIN")
                .antMatchers("/lecturer/**").hasAnyRole("LECTURER", "ADMIN").anyRequest().authenticated()

                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/default")
                .and()
                .exceptionHandling().accessDeniedPage("/denied")

                .and()
                .csrf().ignoringAntMatchers("/h2-console/**")

                .and()
                .cors().disable();

        http.headers().frameOptions().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user").password("{noop}password").roles("ADMIN").and()
                .withUser("denco").password("{noop}password").roles("LECTURER").and()
                .withUser("fabian").password("{noop}password").roles("LECTURER")
                .and()
                .withUser("dtrump").password("{noop}password").roles("STUDENT")
                .and()
                .withUser("jibori").password("{noop}password").roles("STUDENT")
                .and()
                .withUser("bshorten").password("{noop}password").roles("LECTURER");
    }
}
