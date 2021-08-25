package ru.russeva.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import ru.russeva.web.config.handler.LoginSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService; // сервис, с помощью которого тащим пользователя
    private final LoginSuccessHandler loginSuccessHandler; // класс, в котором описана логика перенаправления пользователей по ролям

    public SecurityConfig(@Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService, LoginSuccessHandler loginSuccessHandler) {
        this.userDetailsService = userDetailsService;
        this.loginSuccessHandler = loginSuccessHandler;
    }

    // конфигурация для прохождения аутентификации
    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable(); // попробуйте выяснить сами, что это даёт
        http.authorizeRequests()
                .antMatchers("/").permitAll() // доступность всем
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')") // разрешаем входить на /admin пользователям с ролью admin
                .antMatchers("/user/{id}").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')") // разрешаем входить на /user пользователям с ролью User
                .and().formLogin()  // Spring сам подставит свою логин форму
                .successHandler(loginSuccessHandler) // подключаем наш SuccessHandler для перенеправления по ролям
                .and().logout();
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("ADMIN")
//                .password("ADMIN")
//                .roles("ROLE_ADMIN")
//                .and()
//                .withUser("USER")
//                .password("USER")
//                .roles("ROLE_USER");
//    }

    // Необходимо для шифрования паролей
    // В данном примере не используется, отключен
    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }
}
