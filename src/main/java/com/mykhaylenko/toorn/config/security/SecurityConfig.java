package com.mykhaylenko.toorn.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.CharacterEncodingFilter;

/**
 * Created by pavlo.mykhaylenko on 8/20/2015.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MessengerUserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);

        http
            .addFilterBefore(filter, CsrfFilter.class)
            .authorizeRequests()
//                .antMatchers("/messages").hasAuthority(UserRole.ROLE_ADMIN.name()) //==.antMatchers("/messages").access("hasRole('ROLE_SPITTER')")
                .antMatchers("/toorn/registration").denyAll() //==.antMatchers("/messages").access("hasRole('ROLE_SPITTER')")
                .anyRequest().permitAll()
            .and()
                .formLogin()
                    .loginPage("/login")
                    .permitAll()
            .and()
                .rememberMe()
                    .tokenValiditySeconds(2419200) //4 weeks
                    .key("messenger")
            .and()
                .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                    .permitAll()
                    .logoutSuccessUrl("/")
            .and()
                .csrf().csrfTokenRepository(csrfTokenRepository())
            .and()
                .httpBasic();
//            .and()
//                  .requiresChannel()
//                  .antMatchers("/toorn/registration").requiresSecure()
//                .and()
//                .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(userService)
            .passwordEncoder(passwordEncoder());
    }

    @Bean
    public ShaPasswordEncoder passwordEncoder() {
        ShaPasswordEncoder encoder = new ShaPasswordEncoder(256);

        return  encoder;
    }

    private CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setSessionAttributeName("_csrf");
        return repository;
    }
}
