package ru.pre.project.JM.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService; // сервис, с помощью которого тащим пользователя
    private final SuccessUserHandler successUserHandler; // класс, в котором описана логика перенаправления пользователей по ролям

    public SecurityConfig(UserDetailsService userDetailsService, SuccessUserHandler successUserHandler) {
        this.userDetailsService = userDetailsService;
        this.successUserHandler = successUserHandler;
    }

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder()); // конфигурация для прохождения аутентификации
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        secondSeting(http);
    }

    private void secondSeting (HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .antMatchers("/user").access("hasAnyRole('ROLE_USER')")
                .antMatchers("/admin").access("hasAnyRole('ROLE_ADMIN')")
                .antMatchers("/admin/*").hasAuthority("ADMIN")
                .and().formLogin()
                .successHandler(successUserHandler);
    }

    private void firstSeting (HttpSecurity http) throws Exception{
        http.formLogin()
                .loginPage("/login")
                .successHandler(new SuccessUserHandler())
                .loginProcessingUrl("/login")
                .usernameParameter("j_username")
                .passwordParameter("j_password")
                .permitAll();

        http.logout()
                .permitAll()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout");

        http
                .authorizeRequests()
                .antMatchers("/login").anonymous()
                .antMatchers("/test").permitAll()
                .antMatchers("/new").permitAll()
                .antMatchers("/admin/*").hasAuthority("ADMIN")
                .antMatchers("/user/*").hasAnyAuthority("ADMIN","USER")
                .anyRequest().authenticated();
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }
}
