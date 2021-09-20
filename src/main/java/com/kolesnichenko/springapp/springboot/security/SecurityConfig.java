package com.kolesnichenko.springapp.springboot.security;

import com.kolesnichenko.springapp.springboot.service.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;



@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private MyUserDetailService myUserDetailService;
    private LoginSuccessHandler loginSuccessHandler;
    @Autowired
    public void setMyUserDetailService(MyUserDetailService myUserDetailService) {
        this.myUserDetailService = myUserDetailService;
    }
    @Autowired
    public void setLoginSuccessHandler(LoginSuccessHandler loginSuccessHandler) {
        this.loginSuccessHandler = loginSuccessHandler;
    }



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
//                .loginPage("/login") //указываем страницу с формой логина
                .successHandler(loginSuccessHandler) //логика обработки при вводе логина
//                .loginProcessingUrl("/login") //указываем action с формы логина
//                .usernameParameter("j_username")
//                .passwordParameter("j_password")
                .permitAll(); //доступ к форме логина всем
        http.logout()
                .permitAll() //разрешаем делать логаут всем
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) //указываем URL логаута
                .logoutSuccessUrl("/login") // указывам URL при удачном логауте
                .and().csrf().disable();
        http
                // делаем страницу регистрации недоступной для авторизированных пользователей
                .authorizeRequests()
                //страницы аутентификаци доступна всем
                .antMatchers("/login").anonymous()
                // защищенные URL
                .antMatchers("/user/**").hasAnyAuthority("USER", "ADMIN");
        http.authorizeRequests()
                .antMatchers("/admin/**").hasAnyAuthority("ADMIN")
                .anyRequest().denyAll();
    }
}
