package com.chukanwobi.schoolmanagementsystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")

                .and()
                .csrf().ignoringAntMatchers("/h2-console/**")
                .and()
                .cors().disable();

        http.headers().frameOptions().disable();
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user").password(passwordEncoder().encode("password")).roles("ADMIN").and()
                .withUser("denco").password(passwordEncoder().encode("password")).roles("LECTURER").and()
                .withUser("fabian").password(passwordEncoder().encode("password")).roles("LECTURER")
                .and()
                .withUser("dtrump").password(passwordEncoder().encode("password")).roles("STUDENT")
                .and()
                .withUser("jibori").password(passwordEncoder().encode("password")).roles("STUDENT")
                .and()
                .withUser("bshorten").password(passwordEncoder().encode("password")).roles("STUDENT")
                .and()
                .withUser("cnwobi").password(passwordEncoder().encode("password")).roles("STUDENT");
    }

}
